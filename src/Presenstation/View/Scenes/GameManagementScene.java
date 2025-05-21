package Presenstation.View.Scenes;

import Business.Entidades.Game;
import Business.Entidades.User;
import Persistence.sql.SQLGameDAO;
import Persistence.sql.SQLUserDAO;
import Presenstation.Controller.GameManagementController;
import Presenstation.View.Image.JImagePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Escena encargada de mostrar las partidas del usuario, clasificadas en activas y finalizadas.
 * Permite seleccionar, eliminar, reanudar partidas o acceder a estadísticas.
 */
public class GameManagementScene extends Scene {
    private GameManagementController gameManagementController;
    private Game selectedGame;
    private final static String DELETE = "DELETE";
    private final static String LOGOUT = "LOGOUT";
    private final List<JButton> allGameButtons = new ArrayList<>();


    /**
     * Inicializa la estructura del panel principal, distribuyendo las secciones (título, centro, botones).
     */
    public void initialitzate() {
        jPanel.setLayout(new BorderLayout(50, 20));
        jPanel.setBackground(new Color(210, 180, 140));

        jPanel.add(topPanel(), BorderLayout.NORTH);
        jPanel.add(centerPanel(), BorderLayout.CENTER);
        jPanel.add(addVacio(), BorderLayout.EAST);
        jPanel.add(addVacio(), BorderLayout.WEST);
        jPanel.add(addAccesButton(), BorderLayout.SOUTH);
    }

    /**
     * Aplica esta escena al JFrame principal y define el título de la ventana.
     *
     * @param mainFrame Ventana principal de la aplicación.
     */
    public void apply(JFrame mainFrame) {
        initialitzate();
        super.apply(mainFrame);
        mainFrame.setTitle("Game management");
    }

    /**
     * Establece el controlador de la escena y reinicia el diseño.
     *
     * @param gameManagementController Controlador de gestión de partidas.
     */
    public void setController(GameManagementController gameManagementController) {
        this.gameManagementController = gameManagementController;
        initialitzate();
    }

    /**
     * Crea el panel superior con el título y botones de acción (logout y eliminar cuenta).
     *
     * @return JPanel superior.
     */
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

    /**
     * Crea los botones de la parte superior derecha (logout y eliminar cuenta).
     *
     * @return JPanel con los botones.
     */
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

    /**
     * Crea el panel central que contiene los botones de acciones y la lista de partidas activas/finalizadas.
     *
     * @return JPanel central.
     */
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

        JPanel rightPanel = new JPanel(new GridLayout(1, 2));
        rightPanel.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(partidasNoFinalizadas(gamesUser));
        scrollPane.setPreferredSize(new Dimension(300, 300));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        TitledBorder border = BorderFactory.createTitledBorder("ACTIVE GAMES");
        border.setTitleFont(new Font("Apple casual", Font.BOLD, 20));
        border.setTitleColor(Color.WHITE);
        scrollPane.setBorder(border);

        JScrollPane scrollPane2 = new JScrollPane(partidasFinalizadas(gamesUser));
        scrollPane2.setPreferredSize(new Dimension(300, 300));
        scrollPane2.setOpaque(false);
        scrollPane2.getViewport().setOpaque(false);
        TitledBorder border2 = BorderFactory.createTitledBorder("FINISHED GAMES");
        border2.setTitleFont(new Font("Apple casual", Font.BOLD, 20));
        border2.setTitleColor(Color.WHITE);
        scrollPane2.setBorder(border2);

        rightPanel.add(scrollPane);
        rightPanel.add(scrollPane2);

        center.add(leftPanel);
        center.add(Box.createRigidArea(new Dimension(20, 0)));
        center.add(rightPanel);

        return center;
    }

    /**
     * Genera un panel con las partidas activas del usuario (no finalizadas).
     *
     * @param gamesUser Lista de partidas del usuario.
     * @return JPanel con botones correspondientes a partidas activas.
     */
    public JPanel partidasNoFinalizadas(ArrayList<Game> gamesUser) {
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
                if(!game.isFinished()) {
                    JPanel buttonContent = new JPanel();

                    buttonContent.setLayout(new BoxLayout(buttonContent, BoxLayout.Y_AXIS));
                    buttonContent.setOpaque(false);

                    JLabel nameLabel = new JLabel("GAME: " + game.getNombre());
                    nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                    JLabel cafesLabel = new JLabel("Cafes: " + game.getNumCafes());
                    cafesLabel.setFont(new Font("Arial", Font.PLAIN, 10));
                    cafesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                    buttonContent.add(nameLabel);
                    buttonContent.add(cafesLabel);

                    JButton btn = new JButton();


                    btn.setLayout(new BorderLayout());
                    btn.add(buttonContent, BorderLayout.CENTER);
                    btn.setAlignmentX(Component.LEFT_ALIGNMENT);
                    btn.setFocusPainted(false);
                    btn.setBackground(new Color(210, 140, 95)); // marrón claro
                    btn.setOpaque(true);
                    btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    btn.addActionListener(e -> {
                        for (JButton b : allGameButtons) {
                            b.setBackground(new Color(210, 140, 95));
                        }
                        btn.setBackground(Color.ORANGE);
                        selectedGame = game;
                    });

                    botonesPartidas.add(btn);
                    allGameButtons.add(btn);
                    partidasPanel.add(Box.createVerticalStrut(5));
                    partidasPanel.add(btn);
                }
            }
        } else {
            JLabel noGamesLabel = new JLabel("NO GAMES SAVED");
            noGamesLabel.setFont(new Font("Arial", Font.BOLD, 16));
            noGamesLabel.setForeground(Color.GRAY);
            noGamesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            partidasPanel.add(Box.createVerticalStrut(100));
            partidasPanel.add(noGamesLabel);
        }
        return partidasPanel;
    }

    /**
     * Genera un panel con las partidas finalizadas del usuario.
     *
     * @param gamesUser Lista de partidas del usuario.
     * @return JPanel con botones correspondientes a partidas finalizadas.
     */
    public JPanel partidasFinalizadas(ArrayList<Game> gamesUser) {
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
                if(game.isFinished()) {
                    JPanel buttonContent = new JPanel();

                    buttonContent.setLayout(new BoxLayout(buttonContent, BoxLayout.Y_AXIS));
                    buttonContent.setOpaque(false);

                    JLabel nameLabel = new JLabel("GAME: " + game.getNombre());
                    nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                    JLabel cafesLabel = new JLabel("Cafes: " + game.getNumCafes());
                    cafesLabel.setFont(new Font("Arial", Font.PLAIN, 10));
                    cafesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                    buttonContent.add(nameLabel);
                    buttonContent.add(cafesLabel);

                    JButton btn = new JButton();

                    btn.setLayout(new BorderLayout());
                    btn.add(buttonContent, BorderLayout.CENTER);
                    btn.setAlignmentX(Component.LEFT_ALIGNMENT);
                    btn.setFocusPainted(false);
                    btn.setBackground(new Color(210, 140, 95)); // marrón claro
                    btn.setOpaque(true);
                    btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    btn.addActionListener(e -> {
                        for (JButton b : allGameButtons) {
                            b.setBackground(new Color(210, 140, 95));
                        }
                        btn.setBackground(Color.ORANGE);
                        selectedGame = game;
                    });

                    botonesPartidas.add(btn);
                    allGameButtons.add(btn);
                    partidasPanel.add(Box.createVerticalStrut(5));
                    partidasPanel.add(btn);
                }
            }
        } else {
            JLabel noGamesLabel = new JLabel("NO GAMES SAVED");
            noGamesLabel.setFont(new Font("Arial", Font.BOLD, 16));
            noGamesLabel.setForeground(Color.GRAY);
            noGamesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            partidasPanel.add(Box.createVerticalStrut(100));
            partidasPanel.add(noGamesLabel);
        }
        return partidasPanel;
    }

    /**
     * Crea el conjunto de botones centrales: estadísticas, reanudar y eliminar partida.
     *
     * @return JPanel con botones de acción.
     */
    public JPanel botonesCentrales() {


        JPanel preguntasYRespuestas = new JPanel();
        preguntasYRespuestas.setLayout(new BoxLayout(preguntasYRespuestas, BoxLayout.Y_AXIS));

        JButton but_resume = new JButton("RESUME");
        but_resume.setMinimumSize(new Dimension(100, 80));
        but_resume.setPreferredSize(new Dimension(100, 80));
        but_resume.setMaximumSize(new Dimension(150, 80));

        but_resume.setContentAreaFilled(false);
        but_resume.setFocusPainted(false);
        but_resume.setOpaque(false);
        but_resume.setForeground(Color.BLACK);
        but_resume.setFont(new Font("Arial", Font.BOLD, 16));
        but_resume.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 180), 2, true));

        JImagePanel imagePanel = new JImagePanel("data/caffeRaro.jpg");
        imagePanel.setPreferredSize(new Dimension(100, 40));
        imagePanel.setMaximumSize(new Dimension(400, 80));
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(but_resume, BorderLayout.CENTER);

        but_resume.setActionCommand("RESUME");
        but_resume.addActionListener(gameManagementController);

        JButton but_del = new JButton("DELETE");

        but_del.setMinimumSize(new Dimension(100, 80));
        but_del.setPreferredSize(new Dimension(100, 80));
        but_del.setMaximumSize(new Dimension(150, 80));

        but_del.setContentAreaFilled(false);
        but_del.setFocusPainted(false);
        but_del.setOpaque(false);
        but_del.setForeground(Color.BLACK);
        but_del.setFont(new Font("Arial", Font.BOLD, 16));
        but_del.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 180), 2, true));

        JImagePanel imagePanel2 = new JImagePanel("data/caffeRaro.jpg");
        imagePanel2.setPreferredSize(new Dimension(100, 40));
        imagePanel2.setMaximumSize(new Dimension(400, 80));
        imagePanel2.setLayout(new BorderLayout());
        imagePanel2.add(but_del, BorderLayout.CENTER);

        but_del.addActionListener(e -> gameManagementController.deleteSelectedGame());

        preguntasYRespuestas.add(botonStadisticas());
        preguntasYRespuestas.add(Box.createVerticalStrut(10));
        preguntasYRespuestas.add(imagePanel); // Agregar el JImagePanel en lugar del botón
        preguntasYRespuestas.add(Box.createVerticalStrut(10));
        preguntasYRespuestas.add(imagePanel2);

        preguntasYRespuestas.setOpaque(false);
        return preguntasYRespuestas;
    }


    /**
     * Crea el botón inferior que permite crear una nueva partida.
     *
     * @return JPanel con el botón de creación.
     */
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

    /**
     * Crea un panel vacío transparente utilizado como espaciador.
     *
     * @return JPanel vacío.
     */
    public JPanel addVacio() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);
        return panel;
    }

    /**
     * Crea un panel vacío transparente utilizado como espaciador.
     *
     * @return JPanel vacío.
     */
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

    /**
     * Crea un panel vacío transparente utilizado como espaciador.
     *
     * @return JPanel vacío.
     */
    public Game getSelectedGame() {
        return selectedGame;
    }

    /**
     * Crea el botón de estadísticas con imagen de fondo.
     *
     * @return JImagePanel con el botón de estadísticas integrado.
     */
    public JImagePanel botonStadisticas() {
        JButton botonStadisticas = new JButton("Stadisticas");

        botonStadisticas.setMinimumSize(new Dimension(100, 80));
        botonStadisticas.setPreferredSize(new Dimension(100, 80));
        botonStadisticas.setMaximumSize(new Dimension(100, 80));

        botonStadisticas.setContentAreaFilled(false);
        botonStadisticas.setFocusPainted(false);
        botonStadisticas.setOpaque(false);
        botonStadisticas.setForeground(Color.BLACK);
        botonStadisticas.setFont(new Font("Arial", Font.BOLD, 16));
        botonStadisticas.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 180), 2, true));

        JImagePanel imagePanel2 = new JImagePanel("data/caffeRaro.jpg");
        imagePanel2.setPreferredSize(new Dimension(100, 40));
        imagePanel2.setMaximumSize(new Dimension(400, 80));
        imagePanel2.setLayout(new BorderLayout());
        imagePanel2.add(botonStadisticas, BorderLayout.CENTER);

        botonStadisticas.addActionListener(gameManagementController);
        botonStadisticas.setActionCommand("STADISTICAS");

        return imagePanel2;
    }
}