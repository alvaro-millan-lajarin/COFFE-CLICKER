package Presenstation.View.Scenes;

import Presenstation.Controller.StaticsController;
import Presenstation.View.Grafica.Grafica;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Escena de estadísticas del juego.
 * Muestra información visual como la evolución de cafés producidos.
 */
public class StaticsScene extends Scene {
    private StaticsController staticsController;
    private final static String BACK = "BACK";
    private Grafica grafica;

    /**
     * Asigna el controlador de estadísticas y reinicializa la escena.
     *
     * @param staticsController Controlador encargado de manejar eventos en la escena.
     */
    public void setController(StaticsController staticsController) {
        this.staticsController = staticsController;
        initialitzate();
    }

    /**
     * Inicializa la estructura gráfica de la escena con disposición de paneles.
     */
    public void initialitzate() {
        jPanel.setLayout(new BorderLayout(50, 20));
        jPanel.setBackground(new Color(210, 180, 140));
        jPanel.add(topPanel(), BorderLayout.NORTH);
        jPanel.add(centerPanel(), BorderLayout.CENTER);
        jPanel.add(addVacio(), BorderLayout.EAST);
        jPanel.add(addVacio(), BorderLayout.WEST);
        jPanel.add(addAccesButton(), BorderLayout.SOUTH);
    }

    /**
     * Aplica la escena al JFrame principal y actualiza el título de la ventana.
     *
     * @param mainFrame Ventana principal de la aplicación.
     */
    public void apply(JFrame mainFrame) {
        initialitzate();
        super.apply(mainFrame);
        mainFrame.setTitle("Login");
    }

    /**
     * Crea el panel superior con título y botones de cuenta.
     *
     * @return JPanel con la parte superior de la escena.
     */
    public JPanel topPanel() {
        JPanel topPanel = new JPanel(new GridLayout(1, 5));
        topPanel.add(addVacio());
        topPanel.add(addVacio());
        topPanel.add(addTitle("STATICS"));
        topPanel.add(addVacio());
        topPanel.add(addBotonesArribaDerecha());
        topPanel.setOpaque(false);
        return topPanel;
    }

    /**
     * Crea los botones de la parte superior derecha (eliminar cuenta y logout).
     *
     * @return JPanel con los botones de la cuenta de usuario.
     */
    public JPanel addBotonesArribaDerecha(){
        JPanel botonesArribaDerecha = new JPanel(new GridLayout(2, 1));
        JButton delAcc_but = new JButton("Delete Account");
        JButton logout_but = new JButton("Logout");
        botonesArribaDerecha.add(delAcc_but);
        botonesArribaDerecha.add(logout_but);
        delAcc_but.setActionCommand("DELETE");
        logout_but.setActionCommand("LOGOUT");
        delAcc_but.addActionListener(staticsController);
        logout_but.addActionListener(staticsController);

        botonesArribaDerecha.setOpaque(false);
        return botonesArribaDerecha;
    }

    /**
     * Crea el panel central. Se puede personalizar para mostrar gráficos u otras estadísticas.
     *
     * @return JPanel central vacío por defecto.
     */
    public JPanel centerPanel() {
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
        center.setOpaque(false);
        return center;
    }

    /**
     * Crea un botón de acción con la etiqueta "PLAY", utilizado para volver a jugar u otra función.
     *
     * @return JPanel con el botón de acción principal.
     */
    public JPanel addAccesButton(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        JButton accesButton = new JButton("PLAY");
        accesButton.setFont(new Font("Apple casual", Font.BOLD, 20));
        accesButton.setPreferredSize(new Dimension(150, 50));
        accesButton.setOpaque(false);

        accesButton.setActionCommand("PLAY");
        accesButton.addActionListener(staticsController);

        buttonPanel.add(accesButton);
        return buttonPanel;
    }

    /**
     * Devuelve un panel vacío utilizado como espaciador visual.
     *
     * @return JPanel transparente sin contenido.
     */
    public JPanel addVacio() {
        JPanel panel = new JPanel(new FlowLayout());

        panel.setOpaque(false);
        return panel;
    }

    /**
     * Crea un panel con un título estilizado.
     *
     * @param message Texto del título.
     * @return JPanel con el título.
     */
    public JPanel addTitle(String message) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);
        label.setFont(new Font("Sans Serif", Font.BOLD, 15));
        label.setForeground(Color.WHITE);
        panel.add(label);
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(20, 0, 0, 0));
        return panel;
    }
}
