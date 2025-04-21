package Presenstation.View;

import com.mysql.cj.conf.ConnectionUrlParser;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Grafica extends JPanel {
    private final List<ConnectionUrlParser.Pair<LocalDateTime, Integer>> historico;
    private final LocalDateTime startTime;

    public Grafica(List<ConnectionUrlParser.Pair<LocalDateTime, Integer>> historico) {
        this.historico = historico;
        this.startTime = historico.get(0).getKey();
        setPreferredSize(new Dimension(600, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (historico.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());

        int padding = 40;
        int width = getWidth() - 2 * padding;
        int height = getHeight() - 2 * padding;

        // Ejes
        g2.setColor(Color.BLACK);
        g2.drawLine(padding, getHeight() - padding, getWidth() - padding, getHeight() - padding); // eje X
        g2.drawLine(padding, padding, padding, getHeight() - padding); // eje Y

        // Escalas
        int maxCafe = historico.stream().mapToInt(Pair::getValue).max().orElse(1);
        long totalSeconds = Duration.between(startTime, historico.get(historico.size() - 1).getKey()).getSeconds();
        totalSeconds = totalSeconds == 0 ? 1 : totalSeconds;

        // Dibujar línea
        g2.setColor(Color.BLUE);
        for (int i = 1; i < historico.size(); i++) {
            ConnectionUrlParser.Pair<LocalDateTime, Integer> prev = historico.get(i - 1);
            ConnectionUrlParser.Pair<LocalDateTime, Integer> curr = historico.get(i);

            long t1 = Duration.between(startTime, prev.getKey()).getSeconds();
            long t2 = Duration.between(startTime, curr.getKey()).getSeconds();

            int x1 = padding + (int) ((t1 / (double) totalSeconds) * width);
            int y1 = getHeight() - padding - (int) ((prev.getValue() / (double) maxCafe) * height);

            int x2 = padding + (int) ((t2 / (double) totalSeconds) * width);
            int y2 = getHeight() - padding - (int) ((curr.getValue() / (double) maxCafe) * height);

            g2.drawLine(x1, y1, x2, y2);
        }

        // Etiquetas (opcional)
        g2.drawString("Tiempo", getWidth() / 2, getHeight() - 10);
        g2.drawString("Cafés", 5, getHeight() / 2);
    }
}
