package Presenstation.View.Scenes;
import Presenstation.View.Image.JImagePanel;

import javax.swing.*;

/**
 * Clase base para todas las escenas del juego.
 * Encapsula la lógica común de inicialización y aplicación de paneles en el JFrame principal.
 */
public class Scene {
    protected JImagePanel jPanel;
    private JFrame mainFrame;

    private final static int WIDTH = 1100;
    private final static int HEIGHT = 550;

    /**
     * Constructor que configura la ventana principal y el panel de fondo.
     */
    public Scene() {
        mainFrame = new JFrame();
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        jPanel = new JImagePanel("data/fondoGlobal4.jpg");
    }

    /**
     * Método que puede ser sobreescrito por las subclases para definir la estructura de la escena.
     */
    public void initialitzate() {

    }

    /**
     * Aplica el panel de la escena al JFrame proporcionado.
     *
     * @param mainFrame Ventana principal donde se insertará la escena.
     */
    public void apply(JFrame mainFrame) {
        mainFrame.getContentPane().removeAll();
        if(mainFrame.isUndecorated()) {
            mainFrame.setUndecorated(false);
        }
        mainFrame.setExtendedState(JFrame.NORMAL);

        mainFrame.getContentPane().add(jPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    /**
     * Muestra la ventana principal en pantalla.
     */
    public void showVisible() {
        mainFrame.setVisible(true);
    }

    /**
     * Devuelve el JFrame principal de la aplicación.
     *
     * @return JFrame principal.
     */
    public JFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * Limpia el contenido del JFrame.
     */
    public void clean() {

        mainFrame.getContentPane().removeAll();
    }
}
