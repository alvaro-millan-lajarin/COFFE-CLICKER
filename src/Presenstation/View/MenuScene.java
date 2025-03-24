package Presenstation.View;

import javax.swing.*;
import java.awt.*;

public class MenuScene extends Scene {
    public void initialitzate() {
        jPanel.setLayout(new BorderLayout());
        jPanel.setBackground(Color.BLACK);
        JButton exitButton = new JButton("Exit");
        jPanel.add(exitButton, BorderLayout.EAST);
    }
    public void apply(JFrame mainFrame) {
        initialitzate();
        super.apply(mainFrame);
        mainFrame.setTitle("Menu");
    }
}
