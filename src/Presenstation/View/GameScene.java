package Presenstation.View;

import Presenstation.Controller.GameController;
import Presenstation.JImagePanel;
import Presenstation.View.Table.TableBotigaGenerators;
import Presenstation.View.Table.TableBotigaMillores;
import Presenstation.View.Table.TableGeneradorsDisponibles;
import Presenstation.View.WriteText.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.Timer;


public class GameScene extends Scene {
    private GameController gameController;
    public final static String GAME_MANAGEMENT = "GAME_MANAGEMENT";

    private Text name;
    private Integer n_cafes = 0;
    private JLabel numCafesLabel;

    private TableBotigaGenerators tableBotigaGenerators;
    private TableBotigaMillores tableBotigaMillores;
    private TableGeneradorsDisponibles tableGeneradorsDisponibles;

    private Timer updateTimer;

    public void initialitzate() {

        jPanel = new JImagePanel("data/game.jpg");
        jPanel.setLayout(new BorderLayout(50, 20));
        //jPanel.setBackground(imagePanel);

        jPanel.add(topPanel(), BorderLayout.NORTH);

        jPanel.add(centerPanel(), BorderLayout.CENTER);
        jPanel.add(addVacio(), BorderLayout.EAST);
        jPanel.add(addVacio(), BorderLayout.WEST);
        jPanel.add(addAccesButton(), BorderLayout.SOUTH);


    }
    public void apply(JFrame mainFrame) {
        initialitzate();
        super.apply(mainFrame);
        mainFrame.setTitle("Game");

        // 🕒 Inicia un timer para actualizar numCafesLabel cada 1 segundo
        updateTimer = new Timer(1000, e -> {
            if (gameController != null && gameController.getManageGame().getGame() != null) {
                int cafesActuales = gameController.getManageGame().getGame().getNumCafes();
                addCoffe(cafesActuales);
            }
        });
        updateTimer.start();
    }

    public void setController(GameController gameController) {
        this.gameController = gameController;

        initialitzate();
    }
    public JPanel topPanel() {
        JPanel topPanel = new JPanel(new GridLayout(1, 5));
        topPanel.add(addVacio());
        topPanel.add(addVacio());
        topPanel.add(addTitle("GAME"));
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
        delAcc_but.setActionCommand("DELETE");
        logout_but.setActionCommand("LOGOUT");
        delAcc_but.addActionListener(gameController);
        logout_but.addActionListener(gameController);

        botonesArribaDerecha.setOpaque(false);
        return botonesArribaDerecha;
    }
    public JPanel centerPanel() {
        JPanel center = new JPanel(new GridLayout(1, 2));


        center.add(panelIzquierdo());
        center.add(panelDerecho());


        center.setOpaque(false);
        return center;
    }
    public JPanel panelDerecho() {
        JPanel panelDerecho = new JPanel(new GridLayout(3, 1, 30, 30));
        tableBotigaGenerators = new TableBotigaGenerators(gameController);
        tableBotigaMillores = new TableBotigaMillores(gameController);
        tableGeneradorsDisponibles = new TableGeneradorsDisponibles(gameController);

        panelDerecho.add(tableBotigaGenerators);
        panelDerecho.add(tableBotigaMillores);
        panelDerecho.add(tableGeneradorsDisponibles);

        panelDerecho.setOpaque(false);
        return panelDerecho;
    }


    public JPanel panelIzquierdo() {
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setOpaque(false);

        JPanel cafesDisponibles = new JPanel(new GridLayout(6, 1, 5, 0));
        cafesDisponibles.setOpaque(false);

        JLabel nameLabel = new JLabel("CAFES DISPONIBLES", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        nameLabel.setForeground(Color.WHITE);

        numCafesLabel = new JLabel(String.valueOf(n_cafes), SwingConstants.CENTER);
        numCafesLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        numCafesLabel.setForeground(Color.WHITE);

        cafesDisponibles.add(nameLabel);
        cafesDisponibles.add(numCafesLabel);
        cafesDisponibles.add(addVacio());
        cafesDisponibles.add(addVacio());
        cafesDisponibles.add(addVacio());
        cafesDisponibles.add(addVacio());

        panelIzquierdo.add(cafesDisponibles);
        panelIzquierdo.add(panelParaImagen());

        return panelIzquierdo;
    }

    public JPanel panelParaImagen(){
        JPanel panel = new JPanel(new GridLayout(1, 3));

        JButton imagenCafe = new JButton();
        JImagePanel imagePanel = new JImagePanel("data/cafe_clickar.png");
        imagePanel.setPreferredSize(new Dimension(200, 200));
        imagePanel.setOpaque(false);
        imagenCafe.add(imagePanel);
        imagenCafe.addActionListener(gameController);
        imagenCafe.setActionCommand("MORE_COFFE");
        imagenCafe.setOpaque(false);
        imagenCafe.setContentAreaFilled(false);
        imagenCafe.setBorderPainted(false);
        imagenCafe.setFocusPainted(false);

        panel.add(addVacio());
        panel.add(imagenCafe);
        panel.add(addVacio());
        panel.setOpaque(false);
        return panel;
    }
    public JPanel addAccesButton(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        JButton exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Apple casual", Font.BOLD, 20));
        exitButton.setPreferredSize(new Dimension(150, 50));
        exitButton.setOpaque(false);

        exitButton.setActionCommand(GAME_MANAGEMENT);
        exitButton.addActionListener(gameController);

        buttonPanel.add(exitButton);
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
    public void addCoffe(int numCafes){

        numCafesLabel.setText(String.valueOf(numCafes));
        jPanel.revalidate();
        jPanel.repaint();
    }
    public void updateTablas(ArrayList<Integer> quantitats, ArrayList<String> proudccioUnitat, ArrayList<Integer> precioBase, ArrayList<Integer> costMultiplicadores, ArrayList<Integer> multiplicadores) {
        tableGeneradorsDisponibles.setUpdateValores(quantitats,proudccioUnitat, multiplicadores);
        tableBotigaGenerators.setUpdateValores(precioBase, proudccioUnitat);
        tableBotigaMillores.setUpdateValores(costMultiplicadores, multiplicadores);

        jPanel.revalidate();
        jPanel.repaint();
    }

}