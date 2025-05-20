package Presenstation.View.WriteText;

import javax.swing.*;
import java.awt.*;


/**
 * Clase que representa un componente gráfico reutilizable de entrada de datos.
 * Puede comportarse como un campo de texto (JTextField) o como un campo de contraseña (JPasswordField),
 * acompañado de una etiqueta descriptiva (JLabel).
 */
public class Text {
    private JLabel label;
    private JPasswordField fieldPassword;
    private JTextField field;
    private JPanel panelText;

    /**
     * Constructor que inicializa el componente con una etiqueta y un campo de texto o contraseña.
     *
     * @param text             Texto de la etiqueta (label) que acompaña al campo.
     * @param passwordOrText   Si es "text", se usa un JTextField; cualquier otro valor genera un JPasswordField.
     */
    public Text(String text, String passwordOrText) {
        panelText = new JPanel(new GridLayout(2,1));
        label = new JLabel(text);
        label.setOpaque(false);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Apple casual", Font.BOLD, 20));
        if(passwordOrText.equals("text")) {
            field = new JTextField(20);
            createText();
        }else{
            fieldPassword = new JPasswordField(passwordOrText.length());
            createPassword();
        }


    }


    /**
     * Inicializa y configura un JTextField como campo de entrada,
     * lo añade al panel junto a su etiqueta.
     */
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


    /**
     * Inicializa y configura un JPasswordField como campo de entrada de contraseña,
     * lo añade al panel junto a su etiqueta.
     */
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

    /**
     * Devuelve el panel que contiene tanto la etiqueta como el campo de entrada.
     *
     * @return JPanel con los componentes organizados.
     */
    public JPanel getPanelText() {
        return panelText;
    }

    /**
     * Cambia el texto de la etiqueta asociada al campo de entrada.
     *
     * @param message Nuevo texto para la etiqueta.
     */
    public void setText(String message) {
        label.setText(message);
    }

    /**
     * Obtiene el contenido actual del campo de entrada.
     *
     * @param message Si es "text", devuelve el contenido del JTextField;
     *                en caso contrario, el contenido del JPasswordField.
     * @return El texto introducido por el usuario.
     */
    public String getText(String message) {
        if(message.equals("text")){
            return field.getText();
        }else{
            return fieldPassword.getText();
        }

    }

    /**
     * Establece el contenido del campo de contraseña.
     *
     * @param password Contraseña que se establecerá en el campo.
     */
    public void setPassword(String password) {
        fieldPassword.setText(password);

    }

    /**
     * Establece el contenido del campo de texto plano.
     *
     * @param message Texto que se establecerá en el campo de texto.
     */
    public void setField(String message) {
        field.setText(message);
    }
}
