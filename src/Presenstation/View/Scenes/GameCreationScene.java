package Presenstation.View.Scenes;

import Presenstation.Controller.GameCreationController;
import Presenstation.View.Image.JImagePanel;
import Presenstation.View.WriteText.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameCreationScene extends Scene {
    private GameCreationController gameCreationController;
    public final static String GAME_MANAGEMENT = "GAME_MANAGEMENT";

    private Text email;
    private Text password;
    private Text name;
    private Text passwordAgain;

    public void initialitzate() {
        jPanel.setLayout(new BorderLayout(50, 20));
        jPanel.setBackground(new Color(210, 180, 140));

        jPanel.add(topPanel(), BorderLayout.NORTH);

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
    public void setController(GameCreationController gameCreationController) {
        this.gameCreationController = gameCreationController;
        initialitzate();
    }
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


    public JPanel preguntasYRespuestas() {
        JPanel preguntasYRespuestas = new JPanel(new GridLayout(4,1));
        name = new Text("Name of the new game", "text");

        preguntasYRespuestas.add(name.getPanelText());

        preguntasYRespuestas.setOpaque(false);
        return preguntasYRespuestas;
    }
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
    public JPanel addVacio() {
        JPanel panel = new JPanel(new FlowLayout());

        panel.setOpaque(false);
        return panel;
    }
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
    public String getEmail() {
        return email.getText("text");
    }
    public String getPassword() {
        return password.getText("password");
    }
    public String getPasswordAgain() {
        return passwordAgain.getText("password");
    }
    public String getName(){
        return name.getText("text");
    }
}
