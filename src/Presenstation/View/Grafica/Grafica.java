package Presenstation.View.Grafica;



import Business.Entidades.Pair;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Grafica extends JPanel {
    private final List<Pair<LocalDateTime, Integer>> historico;
    private final LocalDateTime startTime;

    public Grafica(List<Pair<LocalDateTime, Integer>> historico) {
        this.historico = historico;
        this.startTime = historico.isEmpty() ? LocalDateTime.now() : historico.get(0).getKey();
        setPreferredSize(new Dimension(600, 400));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (historico.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());

        int padding = 60;
        int width = getWidth() - 2 * padding;
        int height = getHeight() - 2 * padding;


        g2.setColor(Color.BLACK);
        g2.drawLine(padding, getHeight() - padding, getWidth() - padding, getHeight() - padding); // eje X
        g2.drawLine(padding, padding, padding, getHeight() - padding); // eje Y


        int maxCafe = historico.stream().mapToInt(Pair::getValue).max().orElse(1);
        long totalSeconds = Duration.between(startTime, historico.get(historico.size() - 1).getKey()).getSeconds();
        totalSeconds = totalSeconds == 0 ? 1 : totalSeconds;


        g2.setColor(Color.BLUE);
        for (int i = 1; i < historico.size(); i++) {
            Pair<LocalDateTime, Integer> prev = historico.get(i - 1);
            Pair<LocalDateTime, Integer> curr = historico.get(i);

            long t1 = Duration.between(startTime, prev.getKey()).toMinutes();
            long t2 = Duration.between(startTime, curr.getKey()).toMinutes();

            int x1 = padding + (int) ((t1 / (double) totalSeconds) * width);
            int y1 = getHeight() - padding - (int) ((prev.getValue() / (double) maxCafe) * height);

            int x2 = padding + (int) ((t2 / (double) totalSeconds) * width);
            int y2 = getHeight() - padding - (int) ((curr.getValue() / (double) maxCafe) * height);

            g2.drawLine(x1, y1, x2, y2);
        }


        g2.setColor(Color.DARK_GRAY);
        int yTicks = 5;
        for (int i = 0; i <= yTicks; i++) {
            int value = i * maxCafe / yTicks;
            int y = getHeight() - padding - (int) ((value / (double) maxCafe) * height);
            g2.drawLine(padding - 5, y, padding + 5, y); // tick
            g2.drawString(String.valueOf(value), padding - 40, y + 5); // label
        }


        int xTicks = 6;
        for (int i = 0; i <= xTicks; i++) {
            long seconds = i * totalSeconds / xTicks;
            int x = padding + (int) ((seconds / (double) totalSeconds) * width);
            g2.drawLine(x, getHeight() - padding - 5, x, getHeight() - padding + 5); // tick

            String label = seconds >= 60 ? (seconds / 60) + "m" : seconds + "s";
            g2.drawString(label, x - 10, getHeight() - padding + 20); // label
        }


        g2.drawString("Tiempo", getWidth() / 2, getHeight() - 10);
        g2.drawString("Número de cafés", 10, padding - 10);


        g2.drawString("Máx cafés: " + maxCafe, getWidth() - 120, padding - 10);
        String tiempoStr = totalSeconds > 60
                ? String.format("Tiempo aprox: %.1f min", totalSeconds / 60.0)
                : String.format("Tiempo aprox: %d seg", totalSeconds);
        g2.drawString(tiempoStr, getWidth() - 160, getHeight() - padding + 40);
    }
    public List<Pair<LocalDateTime, Integer>> getHistorico() {
        return historico;
    }


}
