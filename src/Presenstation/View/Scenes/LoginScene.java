package Presenstation.View.Scenes;

import Presenstation.Controller.LoginController;
import Presenstation.View.Image.JImagePanel;
import Presenstation.View.WriteText.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Escena de inicio de sesión del usuario.
 * Permite introducir nombre o email y contraseña para acceder al juego.
 */
public class LoginScene extends Scene {
    private LoginController loginController;
    private final static String GAME_MANAGEMENT = "GAME_MANAGEMENT";
    private Text password;
    private Text name;

    /**
     * Inicializa la estructura de la escena de login, incluyendo formulario y botones.
     */
    public void initialitzate() {
        jPanel.setLayout(new BorderLayout(50, 20));
        jPanel.setBackground(new Color(210, 180, 140));
        jPanel.add(addTitle("LOGIN"), BorderLayout.NORTH);
        jPanel.add(centerPanel(), BorderLayout.CENTER);
        jPanel.add(addVacio(), BorderLayout.EAST);
        jPanel.add(addVacio(), BorderLayout.WEST);
        jPanel.add(addAccesButton(), BorderLayout.SOUTH);
    }

    /**
     * Aplica la escena al JFrame principal y define el título de la ventana.
     *
     * @param mainFrame Ventana principal de la aplicación.
     */
    public void apply(JFrame mainFrame) {
        initialitzate();
        super.apply(mainFrame);
        mainFrame.setTitle("Login");
    }

    /**
     * Asigna el controlador de login y reinicia la interfaz.
     *
     * @param loginController Controlador de la escena de login.
     */
    public void setController(LoginController loginController) {
        this.loginController = loginController;
        initialitzate();
    }

    /**
     * Crea el panel central con los campos de texto y la imagen decorativa.
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

        JImagePanel imagePanel = new JImagePanel("data/Cafe.jpg");
        imagePanel.setOpaque(false);
        imagePanel.setPreferredSize(new Dimension(300, 300));

        center.add(leftPanel);
        center.add(Box.createRigidArea(new Dimension(20, 0)));
        center.add(imagePanel);

        center.setOpaque(false);
        return center;
    }

    /**
     * Crea el panel con los campos de texto para usuario/email y contraseña.
     *
     * @return JPanel con los campos del formulario.
     */
    public JPanel preguntasYRespuestas() {
        JPanel preguntasYRespuestas = new JPanel(new GridLayout(4,1));
        if (name == null) {
            name = new Text("Name or email", "text");
            password = new Text("Password", "password");
        }

        preguntasYRespuestas.add(name.getPanelText());
        preguntasYRespuestas.add(password.getPanelText());
        preguntasYRespuestas.setOpaque(false);
        return preguntasYRespuestas;
    }

    /**
     * Crea el botón de acceso que envía la solicitud de login.
     *
     * @return JPanel con el botón "ACCES".
     */
    public JPanel addAccesButton(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        JButton accesButton = new JButton("ACCESS");
        accesButton.setFont(new Font("Apple casual", Font.BOLD, 20));
        accesButton.setPreferredSize(new Dimension(150, 50));
        accesButton.setOpaque(false);

        accesButton.setActionCommand(GAME_MANAGEMENT);
        accesButton.addActionListener(loginController);

        buttonPanel.add(accesButton);
        return buttonPanel;
    }

    /**
     * Crea un panel vacío transparente utilizado como espaciador.
     *
     * @return JPanel vacío.
     */
    public JPanel addVacio() {
        JPanel panel = new JPanel(new FlowLayout());

        panel.setOpaque(false);
        return panel;
    }

    /**
     * Crea un panel con el título estilizado en la parte superior.
     *
     * @param message Texto del título.
     * @return JPanel con el título.
     */
    public JPanel addTitle(String message) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);
        label.setFont(new Font("Sans Serif", Font.BOLD, 30));
        label.setForeground(Color.WHITE);
        panel.add(label);
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(20, 0, 0, 0));
        return panel;
    }

    /**
     * Devuelve el texto del campo de nombre o email introducido por el usuario.
     *
     * @return Texto del campo.
     */
    public String getEmail() {
        return name.getText("text");
    }

    /**
     * Devuelve el texto del campo de contraseña.
     *
     * @return Texto de la contraseña.
     */
    public String getPassword() {
        return password.getText("password");
    }

    /**
     * Limpia los datos introducidos en los campos de texto del formulario de login.
     */
    public void clearUserData() {
        this.name.setField("");
        this.password.setPassword("");
    }
}
