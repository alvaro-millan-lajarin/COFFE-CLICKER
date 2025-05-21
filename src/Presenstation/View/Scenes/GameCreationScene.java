package Presenstation.View.Scenes;

import Presenstation.Controller.GameCreationController;
import Presenstation.View.Image.JImagePanel;
import Presenstation.View.WriteText.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Escena encargada de gestionar la creación de nuevas partidas.
 * Muestra el formulario para introducir el nombre de la partida y ofrece botones de acción.
 */
public class GameCreationScene extends Scene {
    private GameCreationController gameCreationController;
    private Text name;

    /**
     * Inicializa el diseño principal del panel con los distintos bloques (título, centro, botones, etc.).
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
     * Aplica esta escena al JFrame principal y establece su título.
     *
     * @param mainFrame Ventana principal de la aplicación.
     */
    public void apply(JFrame mainFrame) {
        initialitzate();
        super.apply(mainFrame);
        mainFrame.setTitle("Login");
    }

    /**
     * Asigna el controlador que manejará las acciones dentro de esta escena.
     *
     * @param gameCreationController Controlador de creación de partidas.
     */
    public void setController(GameCreationController gameCreationController) {
        this.gameCreationController = gameCreationController;
        initialitzate();
    }

    /**
     * Crea el panel superior con el título y botones de usuario (logout y eliminar cuenta).
     *
     * @return JPanel superior.
     */
    public JPanel topPanel() {
        JPanel topPanel = new JPanel(new GridLayout(1, 5));
        topPanel.add(addVacio());
        topPanel.add(addVacio());
        topPanel.add(addTitle("GAME CREATION"));
        topPanel.add(addVacio());
        topPanel.add(addBotonesArribaDerecha());
        topPanel.setOpaque(false);
        return topPanel;
    }

    /**
     * Crea el panel con los botones "Delete Account" y "Logout" en la esquina superior derecha.
     *
     * @return JPanel con botones.
     */
    public JPanel addBotonesArribaDerecha(){
        JPanel botonesArribaDerecha = new JPanel(new GridLayout(2, 1));
        JButton delAcc_but = new JButton("Delete Account");
        JButton logout_but = new JButton("Logout");
        botonesArribaDerecha.add(delAcc_but);
        botonesArribaDerecha.add(logout_but);
        delAcc_but.setActionCommand("DELETE");
        logout_but.setActionCommand("LOGOUT");
        delAcc_but.addActionListener(gameCreationController);
        logout_but.addActionListener(gameCreationController);

        botonesArribaDerecha.setOpaque(false);
        return botonesArribaDerecha;
    }

    /**
     * Crea el panel central con el formulario de entrada y la imagen decorativa.
     *
     * @return JPanel central.
     */
    public JPanel centerPanel() {
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));


        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(preguntasYRespuestas());
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(300, 300));


        JImagePanel imagePanel = new JImagePanel("data/imagenCafe.jpg");
        imagePanel.setOpaque(false);
        imagePanel.setPreferredSize(new Dimension(300, 300));


        center.add(leftPanel);
        center.add(Box.createRigidArea(new Dimension(20, 0)));
        center.add(imagePanel);

        center.setOpaque(false);
        return center;
    }

    /**
     * Crea el subpanel con el campo para introducir el nombre de la partida.
     *
     * @return JPanel con las preguntas y entradas de texto.
     */
    public JPanel preguntasYRespuestas() {
        JPanel preguntasYRespuestas = new JPanel(new GridLayout(4,1));
        name = new Text("Name of the new game", "text");
        preguntasYRespuestas.add(name.getPanelText());
        preguntasYRespuestas.setOpaque(false);

        return preguntasYRespuestas;
    }

    /**
     * Crea el panel inferior con el botón "PLAY".
     *
     * @return JPanel con el botón de acceso.
     */
    public JPanel addAccesButton(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        JButton accesButton = new JButton("PLAY");
        accesButton.setFont(new Font("Apple casual", Font.BOLD, 20));
        accesButton.setPreferredSize(new Dimension(150, 50));
        accesButton.setOpaque(false);
        accesButton.setActionCommand("PLAY");
        accesButton.addActionListener(gameCreationController);
        buttonPanel.add(accesButton);

        return buttonPanel;
    }

    /**
     * Crea un panel vacío transparente, utilizado como espaciador.
     *
     * @return JPanel vacío.
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

    /**
     * Devuelve el nombre de la nueva partida introducido por el usuario.
     *
     * @return Texto del campo nombre.
     */
    public String getName(){
        return name.getText("text");
    }
}
