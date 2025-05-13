package Presenstation.View.Scenes;
import Presenstation.View.Image.JImagePanel;

import javax.swing.*;

public class Scene {
    protected JImagePanel jPanel;
    private JFrame mainFrame;

    private final static int WIDTH = 1100;
    private final static int HEIGHT = 550;

    public Scene() {
        mainFrame = new JFrame();
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        jPanel = new JImagePanel("data/fondoGlobal4.jpg");
    }

    public JPanel getPanel() {
        return jPanel;
    }
    public void initialitzate() {

    }
    public void apply(JFrame mainFrame) {
        mainFrame.getContentPane().removeAll();
        if(mainFrame.isUndecorated()) {
            mainFrame.setUndecorated(false);
        }
        mainFrame.setExtendedState(JFrame.NORMAL);

        mainFrame.getContentPane().add(jPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void showVisible() {
        mainFrame.setVisible(true);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
    public void clean() {
        mainFrame.getContentPane().removeAll();
    }
}
