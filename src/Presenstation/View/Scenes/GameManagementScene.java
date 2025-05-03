package Presenstation.View.Scenes;

import Business.Entidades.Game;
import Business.Entidades.User;
import Persistence.sql.SQLGameDAO;
import Persistence.sql.SQLUserDAO;
import Presenstation.Controller.GameManagementController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameManagementScene extends Scene {
    private GameManagementController gameManagementController;
    private Game selectedGame;
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

    public JPanel addBotonesArribaDerecha() {
        JPanel botonesArribaDerecha = new JPanel(new GridLayout(2, 1));
        JButton delAcc_but = new JButton("Delete Account");
        JButton logout_but = new JButton("Logout");
        botonesArribaDerecha.add(delAcc_but);
        botonesArribaDerecha.add(logout_but);
        delAcc_but.setActionCommand(DELETE);
        logout_but.setActionCommand(LOGOUT);
        delAcc_but.addActionListener(gameManagementController);
        logout_but.addActionListener(gameManagementController);
        botonesArribaDerecha.setOpaque(false);
        return botonesArribaDerecha;
    }

    public JPanel centerPanel() {
        List<Game> games = gameManagementController.getAllGames();
        User user = new User();
        user = gameManagementController.getUser();

        ArrayList<Game> gamesUser = new ArrayList<>();

        if (user != null) {
            for (Game game : games) {
                if (game.getIdUser() == user.getId()) {
                    gamesUser.add(game);
                }
            }
        }

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
        center.setOpaque(false);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(botonesCentrales());
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(300, 300));

        JPanel partidasPanel = new JPanel();
        partidasPanel.setLayout(new BoxLayout(partidasPanel, BoxLayout.Y_AXIS));
        partidasPanel.setOpaque(false);
        partidasPanel.removeAll();
        partidasPanel.revalidate();
        partidasPanel.repaint();

        List<JButton> botonesPartidas = new ArrayList<>();

        if (!gamesUser.isEmpty()) {
            for (int i = 0; i < gamesUser.size(); i++) {
                Game game = gamesUser.get(i);
                String text = "Game name: " + game.getNombre() + "  Num. Coffees: " + game.getNumCafes() + " - Last  Save: " + game.getFechaModificacion();
                JButton btn = new JButton(text);
                btn.setAlignmentX(Component.LEFT_ALIGNMENT);
                btn.setFocusPainted(false);
                btn.setBackground(Color.LIGHT_GRAY);
                btn.setOpaque(true);
                btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                btn.addActionListener(e -> {
                    for (JButton b : botonesPartidas) {
                        b.setBackground(Color.LIGHT_GRAY);
                    }
                    btn.setBackground(Color.ORANGE);
                    selectedGame = game;
                });

                botonesPartidas.add(btn);
                partidasPanel.add(Box.createVerticalStrut(5));
                partidasPanel.add(btn);
            }
        } else {
            JLabel noGamesLabel = new JLabel("NO GAMES SAVED");
            noGamesLabel.setFont(new Font("Arial", Font.BOLD, 16));
            noGamesLabel.setForeground(Color.GRAY);
            noGamesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            partidasPanel.add(Box.createVerticalStrut(100));
            partidasPanel.add(noGamesLabel);
        }

        JScrollPane scrollPane = new JScrollPane(partidasPanel);
        scrollPane.setPreferredSize(new Dimension(300, 300));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createTitledBorder("SELECT GAME"));

        center.add(leftPanel);
        center.add(Box.createRigidArea(new Dimension(20, 0)));
        center.add(scrollPane);

        return center;
    }

    public JPanel botonesCentrales() {
        JPanel preguntasYRespuestas = new JPanel(new GridLayout(4,1));
        JButton but_resume = new JButton("RESUME");
        JButton but_del = new JButton("DELETE");
        preguntasYRespuestas.add(botonStadisticas());

        but_resume.setActionCommand("RESUME");
        but_resume.addActionListener(gameManagementController);
        preguntasYRespuestas.add(but_resume);
        preguntasYRespuestas.add(but_del);
        but_del.addActionListener(e -> gameManagementController.deleteSelectedGame());
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

    public Game getSelectedGame() {
        return selectedGame;
    }
    public JButton botonStadisticas() {
        JButton botonStadisticas = new JButton("Stadisticas");
        botonStadisticas.addActionListener(gameManagementController);
        botonStadisticas.setActionCommand("STADISTICAS");
        return botonStadisticas;
    }
}