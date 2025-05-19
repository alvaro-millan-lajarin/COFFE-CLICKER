package Presenstation.View.Grafica;



import Business.Entidades.Pair;
import Business.ManageStatics;
import Persistence.sql.SQLStatisticDAO;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Grafica extends JPanel {
    private final List<Pair<LocalDateTime, Integer>> historico;


    public Grafica(List<Pair<LocalDateTime, Integer>> historico) {
        this.historico = historico;
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
        long totalMinutes = historico.size() - 1; // ya no fijas 20 minutos




        g2.setColor(Color.BLUE);
        for (int i = 1; i < historico.size(); i++) {
            Pair<LocalDateTime, Integer> prev = historico.get(i - 1);
            Pair<LocalDateTime, Integer> curr = historico.get(i);

            long t1 = i - 1; // minuto i-1
            long t2 = i;     // minuto i



            int x1 = padding + (int) ((t1 / (double) totalMinutes) * width);
            int y1 = getHeight() - padding - (int) ((prev.getValue() / (double) maxCafe) * height);

            int x2 = padding + (int) ((t2 / (double) totalMinutes) * width);
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


        // Mostrar ticks cada 1 minuto
        int maxMinutes = (int) totalMinutes;
        for (int i = 0; i <= maxMinutes; i++) {
            int x = padding + (int) ((i / (double) totalMinutes) * width);
            g2.drawLine(x, getHeight() - padding - 5, x, getHeight() - padding + 5);
            g2.drawString(i + "m", x - 10, getHeight() - padding + 20);
        }




        g2.drawString("Tiempo", getWidth() / 2, getHeight() - 10);
        g2.drawString("Número de cafés", 10, padding - 10);


        g2.drawString("Máx cafés: " + maxCafe, getWidth() - 120, padding - 10);
        String tiempoStr = totalMinutes > 60
                ? String.format("Tiempo aprox: %.1f min", totalMinutes / 60.0)
                : String.format("Tiempo aprox: %d seg", totalMinutes);
        g2.drawString(tiempoStr, getWidth() - 160, getHeight() - padding + 40);
    }
    public List<Pair<LocalDateTime, Integer>> getHistorico() {
        return historico;
    }





}
