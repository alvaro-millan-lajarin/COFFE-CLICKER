package Presenstation.View.Scenes;

import Presenstation.Controller.MenuController;
import Presenstation.View.Image.JImagePanel;

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

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 50, 20));

        jPanel.add(addTitle("COFFE CLICKER"), BorderLayout.NORTH);
        jPanel.add(makeCenterPanel(centerPanel), BorderLayout.CENTER);
        jPanel.add(addVacio(), BorderLayout.EAST);
        jPanel.add(addVacio(), BorderLayout.WEST);
        jPanel.add(addVacio(), BorderLayout.SOUTH);


    }
    public JPanel makeCenterPanel(JPanel centerPanel) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        JPanel panelIzquierdo = new JPanel(new GridBagLayout());
        GridBagConstraints innerGbc = new GridBagConstraints();
        innerGbc.gridx = 0;
        innerGbc.gridy = 0;
        innerGbc.insets = new Insets(10, 10, 10, 10);

        JButton signUp = new JButton("SIGN UP");
        signUp.setPreferredSize(new Dimension(200, 120));
        signUp.setActionCommand(SIGNUP);
        signUp.addActionListener(menuController);
        panelIzquierdo.add(signUp, innerGbc);

        innerGbc.gridy++;
        JButton login = new JButton("LOGIN");
        login.setPreferredSize(new Dimension(200, 120));
        login.setActionCommand(LOGIN);
        login.addActionListener(menuController);
        panelIzquierdo.add(login, innerGbc);

        panelIzquierdo.setOpaque(false);


        gbc.gridx = 0;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(panelIzquierdo, gbc);


        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JImagePanel image = new JImagePanel("data/fondo3.jpg");
        image.setOpaque(false);
        image.setLayout(new BorderLayout());
        panel.add(image, gbc);

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
