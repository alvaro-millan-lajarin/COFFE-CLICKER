package Presenstation.View.Scenes;

import Presenstation.Controller.SignUpController;
import Presenstation.View.Image.JImagePanel;
import Presenstation.View.WriteText.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


/**
 * Escena de registro de nuevos usuarios.
 * Permite introducir nombre, email y contraseña para crear una cuenta.
 */
public class SignUpScene extends Scene{
    private SignUpController signUpController;
    public final static String SIGNUP = "SIGNUP";
    private Text email;
    private Text password;
    private Text name;
    private Text passwordAgain;

    /**
     * Inicializa la estructura visual de la escena, incluyendo campos de entrada y botones.
     */
    public void initialitzate() {
        jPanel.setLayout(new BorderLayout(50, 20));
        jPanel.setBackground(new Color(210, 180, 140));

        jPanel.add(addTitle("SIGNUP"), BorderLayout.NORTH);

        jPanel.add(centerPanel(), BorderLayout.CENTER);
        jPanel.add(addVacio(), BorderLayout.EAST);
        jPanel.add(addVacio(), BorderLayout.WEST);
        jPanel.add(addAccesButton(), BorderLayout.SOUTH);


    }

    /**
     * Aplica esta escena al JFrame principal y configura el título de la ventana.
     *
     * @param mainFrame Ventana principal de la aplicación.
     */
    public void apply(JFrame mainFrame) {
        initialitzate();
        super.apply(mainFrame);
        mainFrame.setTitle("Sign Up");
    }

    /**
     * Asigna el controlador de registro y reinicializa la escena.
     *
     * @param signUpController Controlador que gestiona las acciones del usuario en esta escena.
     */
    public void setController(SignUpController signUpController) {
        this.signUpController = signUpController;
        initialitzate();
    }

    /**
     * Crea el panel central con campos de texto y una imagen decorativa.
     *
     * @return JPanel central de la escena.
     */
    public JPanel centerPanel() {
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(preguntasYRespuestas());
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(300, 300));

        JImagePanel imagePanel = new JImagePanel("data/imagenCafe2.jpeg");
        imagePanel.setOpaque(false);
        imagePanel.setPreferredSize(new Dimension(300, 300));

        center.add(leftPanel);
        center.add(Box.createRigidArea(new Dimension(20, 0)));
        center.add(imagePanel);

        center.setOpaque(false);
        return center;
    }

    /**
     * Crea el panel que contiene los campos de texto para nombre, email y contraseña.
     *
     * @return JPanel con los campos de entrada.
     */
    public JPanel preguntasYRespuestas() {
        JPanel preguntasYRespuestas = new JPanel(new GridLayout(4,1));

        if (name == null) {
            name = new Text("Name", "text");
            email = new Text("Email", "text");
            password = new Text("Password", "password");
            passwordAgain = new Text("Password Again", "password");
        }

        preguntasYRespuestas.add(name.getPanelText());
        preguntasYRespuestas.add(email.getPanelText());
        preguntasYRespuestas.add(password.getPanelText());
        preguntasYRespuestas.add(passwordAgain.getPanelText());
        preguntasYRespuestas.setOpaque(false);
        return preguntasYRespuestas;
    }

    /**
     * Crea el botón de acceso que envía la solicitud de registro.
     *
     * @return JPanel con el botón "ACCES".
     */
    public JPanel addAccesButton(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        JButton accesButton = new JButton("ACCES");
        accesButton.setFont(new Font("Apple casual", Font.BOLD, 20));
        accesButton.setPreferredSize(new Dimension(150, 50));
        accesButton.setOpaque(false);

        accesButton.setActionCommand(SIGNUP);
        accesButton.addActionListener(signUpController);

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
     * @param message Texto a mostrar como título.
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
     * Devuelve el texto introducido en el campo de nombre.
     *
     * @return Nombre del usuario.
     */
    public String getName(){
        return name.getText("text");
    }

    /**
     * Devuelve el texto introducido en el campo de email.
     *
     * @return Email del usuario.
     */
    public String getEmail() {
        return email.getText("text");
    }

    /**
     * Devuelve el texto introducido en el campo de contraseña.
     *
     * @return Contraseña del usuario.
     */
    public String getPassword() {
        return password.getText("password");
    }

    /**
     * Devuelve el texto introducido en el campo de confirmación de contraseña.
     *
     * @return Repetición de la contraseña.
     */
    public String getPasswordAgain() {
        return passwordAgain.getText("password");
    }
}
