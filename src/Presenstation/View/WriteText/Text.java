package Presenstation.View.WriteText;

import javax.swing.*;
import java.awt.*;

public class Text {
    private JLabel label;
    private JTextField field;
    private JPanel panelText;

    public Text(String text) {
        panelText = new JPanel(new GridLayout(2,1));
        label = new JLabel(text);
        label.setOpaque(false);
        field = new JTextField(20);
        createText();

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
    public JPanel getPanelText() {
        return panelText;
    }

    public void setText(String message) {
        label.setText(message);
    }
    public String getText(){
        return field.getText();
    }
}
