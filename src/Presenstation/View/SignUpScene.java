package Presenstation.View;

import javax.swing.*;
import java.awt.*;

public class SignUpScene extends Scene{
    public void initialitzate() {
        jPanel.setLayout(new BorderLayout(50, 20));
        jPanel.setBackground(new Color(210, 180, 140));


        jPanel.setBackground(new Color(210, 180, 140));


    }
    public void apply(JFrame mainFrame) {
        initialitzate();
        super.apply(mainFrame);
        mainFrame.setTitle("Sign Up");
    }
}
