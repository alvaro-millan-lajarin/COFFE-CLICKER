package Presenstation.View.Scenes;

import Presenstation.Controller.LoginController;
import Presenstation.View.Image.JImagePanel;
import Presenstation.View.WriteText.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginScene extends Scene {
    private LoginController loginController;
    public final static String GAME_MANAGEMENT = "GAME_MANAGEMENT";

    private Text email;
    private Text password;
    private Text name;
    private Text passwordAgain;


    public void initialitzate() {
        jPanel.setLayout(new BorderLayout(50, 20));
        jPanel.setBackground(new Color(210, 180, 140));

        jPanel.add(addTitle("LOGIN"), BorderLayout.NORTH);

        jPanel.add(centerPanel(), BorderLayout.CENTER);
        jPanel.add(addVacio(), BorderLayout.EAST);
        jPanel.add(addVacio(), BorderLayout.WEST);
        jPanel.add(addAccesButton(), BorderLayout.SOUTH);


    }
    public void apply(JFrame mainFrame) {

        initialitzate();

        super.apply(mainFrame);

        mainFrame.setTitle("Login");
    }
    public void setController(LoginController loginController) {
        this.loginController = loginController;
        initialitzate();
    }
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
    public JPanel addAccesButton(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        JButton accesButton = new JButton("ACCES");
        accesButton.setFont(new Font("Apple casual", Font.BOLD, 20));
        accesButton.setPreferredSize(new Dimension(150, 50));
        accesButton.setOpaque(false);

        accesButton.setActionCommand(GAME_MANAGEMENT);
        accesButton.addActionListener(loginController);

        buttonPanel.add(accesButton);
        return buttonPanel;
    }
    public JPanel addVacio() {
        JPanel panel = new JPanel(new FlowLayout());

        panel.setOpaque(false);
        return panel;
    }
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
    public String getEmail() {
        return name.getText("text");
    }
    public String getPassword() {
        return password.getText("password");
    }
    public void clearUserData() {
        this.name.setField("");
        this.password.setPassword("");

    }


}
