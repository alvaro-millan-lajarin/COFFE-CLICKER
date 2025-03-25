package Presenstation.View;

import Presenstation.Controller.MenuController;
import Presenstation.JImagePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuScene extends Scene {
    public final static String SIGNUP = "SIGNUP";
    public final static String LOGIN = "LOGIN";
    private MenuController menuController;

    public void setController(MenuController menuController) {
        this.menuController = menuController;
        initialitzate();
    }

    public void initialitzate() {
        jPanel.setLayout(new BorderLayout(50, 20));
        jPanel.setBackground(new Color(210, 180, 140));

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));

        jPanel.add(addTitle("COFFE CLICKER"), BorderLayout.NORTH);
        jPanel.add(makeCenterPanel(centerPanel), BorderLayout.CENTER);
        jPanel.add(addVacio(), BorderLayout.EAST);
        jPanel.add(addVacio(), BorderLayout.WEST);
        jPanel.add(addVacio(), BorderLayout.SOUTH);


    }
    public JPanel makeCenterPanel(JPanel centerPanel) {
        JPanel panelIzquierdo = new JPanel(new GridBagLayout()); // Usamos GridBagLayout para centrar
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre los botones

        JButton signUp = new JButton("SIGN UP");
        signUp.setPreferredSize(new Dimension(200, 120));
        signUp.setActionCommand(SIGNUP);
        signUp.addActionListener(menuController);

        JButton login = new JButton("LOGIN");
        login.setPreferredSize(new Dimension(200, 120));
        login.setActionCommand(LOGIN);
        login.addActionListener(menuController);
        panelIzquierdo.add(signUp, gbc);
        gbc.gridy++;
        panelIzquierdo.add(login, gbc);

        panelIzquierdo.setOpaque(false);
        centerPanel.add(panelIzquierdo);

        JImagePanel image = new JImagePanel("data/imagenCafe.jpg");
        image.setOpaque(false);
        centerPanel.add(image);
        centerPanel.setOpaque(false);

        return centerPanel;
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
    public void apply(JFrame mainFrame) {
        initialitzate();
        super.apply(mainFrame);
        mainFrame.setTitle("Menu");
    }
    public JPanel addVacio() {
        JPanel panel = new JPanel(new FlowLayout());

        panel.setOpaque(false);
        return panel;
    }
}
