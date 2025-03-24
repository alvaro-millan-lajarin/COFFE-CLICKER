package Presenstation.View;
import javax.swing.*;

public class Scene {
    protected JPanel jPanel;

    protected Scene() {
        jPanel = new JPanel();
    }

    public void initialitzate() {

    }
    public void apply(JFrame mainFrame) {
        if(mainFrame.isUndecorated()) {
            mainFrame.setUndecorated(false);
        }
        mainFrame.setExtendedState(JFrame.NORMAL);
        //mainFrame.setSize(MainView.WIDTH, MainView.HEIGHT);
        mainFrame.getContentPane().add(jPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }


    public void reload() {
        jPanel.removeAll();
        initialitzate();
    }
}
