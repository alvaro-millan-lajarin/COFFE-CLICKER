package Presenstation.View.WriteText;

import javax.swing.*;
import java.awt.*;

public class Text {
    private JLabel label;
    private JPasswordField fieldPassword;
    private JTextField field;
    private JPanel panelText;

    public Text(String text, String passwordOrText) {
        panelText = new JPanel(new GridLayout(2,1));
        label = new JLabel(text);
        label.setOpaque(false);
        if(passwordOrText.equals("text")) {
            field = new JTextField(20);
            createText();
        }else{
            fieldPassword = new JPasswordField(passwordOrText.length());
            createPassword();
        }


    }
    public void createText() {


        field.setBackground(Color.WHITE);
        field.setForeground(Color.BLACK);
        field.setPreferredSize(new Dimension(Integer.MAX_VALUE, 25));
        field.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        field.setMaximumSize(field.getPreferredSize());
        panelText.add(label);
        panelText.add(field);
        panelText.setOpaque(false);
    }
    public void createPassword() {


        fieldPassword.setBackground(Color.WHITE);
        fieldPassword.setForeground(Color.BLACK);
        fieldPassword.setPreferredSize(new Dimension(Integer.MAX_VALUE, 25));
        fieldPassword.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        fieldPassword.setMaximumSize(fieldPassword.getPreferredSize());
        panelText.add(label);
        panelText.add(fieldPassword);
        panelText.setOpaque(false);
    }
    public JPanel getPanelText() {
        return panelText;
    }

    public void setText(String message) {
        label.setText(message);
    }
    public String getText(String message) {
        if(message.equals("text")){
            return field.getText();
        }else{
            return fieldPassword.getText();
        }

    }
}
