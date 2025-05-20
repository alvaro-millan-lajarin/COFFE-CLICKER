package Presenstation.View.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Panel personalizado que muestra una imagen como fondo.
 * Extiende {@link JPanel} y permite renderizar una imagen escalada al tamaño del componente.
 */
public class JImagePanel extends JPanel {
    private BufferedImage image;

    /**
     * Constructor del panel de imagen.
     *
     * @param path Ruta del archivo de imagen a cargar.
     */
    public JImagePanel(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            // Not properly managed, sorry!
            e.printStackTrace();
        }

    }

    /**
     * Calcula el tamaño preferido del panel para mantener la proporción de la imagen.
     *
     * @return Dimensiones preferidas del panel.
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension preferred = super.getPreferredSize();

        float width = image.getWidth();
        float height = image.getHeight();

        // Calculate the height needed to mantain aspect ratio
        preferred.height = Math.round(getWidth()*height/width);

        return preferred;
    }

    /**
     * Pinta la imagen en el fondo del panel, escalándola al tamaño actual del componente.
     *
     * @param g Contexto gráfico para dibujar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}