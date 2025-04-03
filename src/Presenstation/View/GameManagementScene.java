package Presenstation.View;

import Presenstation.Controller.GameManagementController;
import Presenstation.Controller.MenuController;
import Presenstation.Controller.SignUpController;
import Presenstation.JImagePanel;
import Presenstation.View.WriteText.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameManagementScene extends Scene {
    private GameManagementController gameManagementController;
    public final static String DELETE = "DELETE";
    public final static String LOGOUT = "LOGOUT";

    public void initialitzate() {
        jPanel.setLayout(new BorderLayout(50, 20));
        jPanel.setBackground(new Color(210, 180, 140));

        jPanel.add(topPanel(), BorderLayout.NORTH);

        jPanel.add(centerPanel(), BorderLayout.CENTER);
        jPanel.add(addVacio(), BorderLayout.EAST);
        jPanel.add(addVacio(), BorderLayout.WEST);
        jPanel.add(addAccesButton(), BorderLayout.SOUTH);


    }
    public void apply(JFrame mainFrame) {
        initialitzate();
        super.apply(mainFrame);
        mainFrame.setTitle("Game management");
    }
    public void setController(GameManagementController gameManagementController) {
        this.gameManagementController = gameManagementController;
        initialitzate();
    }
    public JPanel topPanel() {
        JPanel topPanel = new JPanel(new GridLayout(1, 5));
        topPanel.add(addVacio());
        topPanel.add(addVacio());
        topPanel.add(addTitle("GAME MANAGEMENT"));
        topPanel.add(addVacio());
        topPanel.add(addBotonesArribaDerecha());
        topPanel.setOpaque(false);
        return topPanel;
    }
    public JPanel addBotonesArribaDerecha(){
        JPanel botonesArribaDerecha = new JPanel(new GridLayout(2, 1));
        JButton delAcc_but = new JButton("Delete Account");
        JButton logout_but = new JButton("Logout");
        botonesArribaDerecha.add(delAcc_but);
        botonesArribaDerecha.add(logout_but);
        delAcc_but.setActionCommand(LOGOUT);
        logout_but.setActionCommand(DELETE);
        delAcc_but.addActionListener(gameManagementController);
        logout_but.addActionListener(gameManagementController);

        botonesArribaDerecha.setOpaque(false);
        return botonesArribaDerecha;
    }
    public JPanel centerPanel() {
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));


        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(botonesCentrales());
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(300, 300));


        JImagePanel imagePanel = new JImagePanel("/Users/alvaro/IdeaProjects/S2dpoCoffeclicker/data/Cafe.jpg");
        imagePanel.setOpaque(false);
        imagePanel.setPreferredSize(new Dimension(300, 300));


        center.add(leftPanel);
        center.add(Box.createRigidArea(new Dimension(20, 0)));
        center.add(imagePanel);

        center.setOpaque(false);
        return center;
    }


    public JPanel botonesCentrales() {
        JPanel preguntasYRespuestas = new JPanel(new GridLayout(4,1));
        JButton but_resume = new JButton("RESUME");
        JButton but_del = new JButton("DELETE");
        preguntasYRespuestas.add(but_resume);
        preguntasYRespuestas.add(but_del);

        preguntasYRespuestas.setOpaque(false);
        return preguntasYRespuestas;
    }
    public JPanel addAccesButton(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        JButton butNewGame = new JButton("CREATE NEW GAME");
        butNewGame.setFont(new Font("Apple casual", Font.BOLD, 20));
        butNewGame.setPreferredSize(new Dimension(300, 50));
        butNewGame.setOpaque(false);

        butNewGame.setActionCommand("CREATE_GAME");
        butNewGame.addActionListener(gameManagementController);

        buttonPanel.add(butNewGame);
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
        label.setFont(new Font("Sans Serif", Font.BOLD, 15));
        label.setForeground(Color.WHITE);
        panel.add(label);
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(20, 0, 0, 0));
        return panel;
    }

}
