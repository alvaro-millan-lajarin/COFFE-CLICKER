package Presenstation.View;

import Presenstation.Controller.MenuController;
import Presenstation.Controller.SignUpController;
import Presenstation.JImagePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SignUpScene extends Scene{
    private SignUpController signUpController;
    public void initialitzate() {
        jPanel.setLayout(new BorderLayout(50, 20));
        jPanel.setBackground(new Color(210, 180, 140));

        jPanel.add(addTitle("COFFE CLICKER"), BorderLayout.NORTH);



        jPanel.add(centerPanel(), BorderLayout.CENTER);
        jPanel.add(addVacio(), BorderLayout.EAST);
        jPanel.add(addVacio(), BorderLayout.WEST);
        jPanel.add(addAccesButton(), BorderLayout.SOUTH);


    }
    public void apply(JFrame mainFrame) {
        initialitzate();
        super.apply(mainFrame);
        mainFrame.setTitle("Sign Up");
    }
    public void setController(SignUpController signUpController) {
        this.signUpController = signUpController;
        initialitzate();
    }
    public JPanel centerPanel() {
        JPanel center = new JPanel(new GridLayout(1,2));
        center.add(addVacio());

        JImagePanel image = new JImagePanel("data/imagenCafe2.jpeg");
        image.setOpaque(false);
        center.add(image);
        center.setOpaque(false);
        return center;
    }
    public JPanel addAccesButton(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        JButton accesButton = new JButton("ACCES");
        accesButton.setFont(new Font("Apple casual", Font.BOLD, 20));
        accesButton.setPreferredSize(new Dimension(150, 50));
        accesButton.setOpaque(false);

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
}
