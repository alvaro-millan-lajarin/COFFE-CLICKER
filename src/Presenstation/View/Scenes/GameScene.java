package Presenstation.View.Scenes;

import Business.Entidades.Game;
import Business.Entidades.Pair;
import Presenstation.Controller.GameController;

import Presenstation.View.Grafica.Grafica;
import Presenstation.View.Image.JImagePanel;
import Presenstation.View.Table.TableBotigaGenerators;
import Presenstation.View.Table.TableBotigaMillores;
import Presenstation.View.Table.TableGeneradorsDisponibles;
import Presenstation.View.WriteText.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;


/**
 * Escena principal del juego donde el usuario puede ver su progreso,
 * generar cafés, comprar generadores y mejoras.
 */
public class GameScene extends Scene {
    private GameController gameController;
    public final static String GAME_MANAGEMENT = "GAME_MANAGEMENT";
    private Integer n_cafes = 0;
    private JLabel numCafesLabel;
    private TableBotigaGenerators tableBotigaGenerators;
    private TableBotigaMillores tableBotigaMillores;
    private TableGeneradorsDisponibles tableGeneradorsDisponibles;

    /**
     * Inicializa la estructura de la interfaz de la escena de juego.
     */
    public void initialitzate() {
        jPanel = new JImagePanel("data/game.jpg");
        jPanel.setLayout(new BorderLayout(50, 20));
        jPanel.add(topPanel(), BorderLayout.NORTH);

        jPanel.add(centerPanel(), BorderLayout.CENTER);
        jPanel.add(addVacio(), BorderLayout.EAST);
        jPanel.add(addVacio(), BorderLayout.WEST);
        jPanel.add(addAccesButton(), BorderLayout.SOUTH);
    }

    /**
     * Aplica esta escena al JFrame principal y establece su título.
     *
     * @param mainFrame Ventana principal de la aplicación.
     */
    public void apply(JFrame mainFrame) {
        initialitzate();
        super.apply(mainFrame);
        mainFrame.setTitle("Game");
    }

    /**
     * Asigna el controlador correspondiente a la escena de juego.
     *
     * @param gameController Controlador del juego.
     */
    public void setController(GameController gameController) {
        this.gameController = gameController;
        initialitzate();
    }

    /**
     * Crea el panel superior con el botón de finalizar partida, título y botones de cuenta.
     *
     * @return JPanel superior.
     */
    public JPanel topPanel() {
        JPanel topPanel = new JPanel(new GridLayout(1, 6));
        topPanel.add(addBotonFinishGame());
        topPanel.add(addVacio());
        topPanel.add(addTitle("GAME"));
        topPanel.add(addVacio());
        topPanel.add(addVacio());
        topPanel.add(addBotonesArribaDerecha());
        topPanel.setOpaque(false);
        return topPanel;
    }

    /**
     * Crea los botones "Delete Account" y "Logout" en la esquina superior derecha.
     *
     * @return JPanel con botones.
     */
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

    /**
     * Crea el panel central dividido entre información y acciones del usuario.
     *
     * @return JPanel central.
     */
    public JPanel centerPanel() {
        JPanel center = new JPanel(new GridLayout(1, 2));
        center.add(panelIzquierdo());
        center.add(panelDerecho());
        center.setOpaque(false);
        return center;
    }

    /**
     * Crea el panel derecho con las tres tablas: generadores, mejoras y disponibles.
     *
     * @return JPanel derecho.
     */
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

    /**
     * Crea el panel izquierdo con información de cafés y el botón para generar más.
     *
     * @return JPanel izquierdo.
     */
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

    /**
     * Crea un botón con una imagen de taza de café que genera cafés al hacer clic.
     *
     * @return JPanel con el botón interactivo.
     */
    public JPanel panelParaImagen(){
        JButton imagenCafe = new JButton();
        JImagePanel imagePanel = new JImagePanel("data/tazaParaClicar.png");
        imagePanel.setOpaque(false);

        imagenCafe.setLayout(new BorderLayout());
        imagenCafe.add(imagePanel, BorderLayout.CENTER);

        imagenCafe.setOpaque(false);
        imagenCafe.setContentAreaFilled(false);
        imagenCafe.setBorderPainted(false);
        imagenCafe.setFocusPainted(false);

        imagenCafe.setPreferredSize(new Dimension(200, 200));
        imagenCafe.setActionCommand("MORE_COFFE");
        imagenCafe.addActionListener(gameController);

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(imagenCafe);

        return panel;
    }

    /**
     * Crea el botón de salida que lleva a la gestión de partidas.
     *
     * @return JPanel con el botón "EXIT".
     */
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

    /**
     * Crea un panel vacío utilizado para espaciado.
     *
     * @return JPanel vacío.
     */
    public JPanel addVacio() {
        JPanel panel = new JPanel(new FlowLayout());

        panel.setOpaque(false);
        return panel;
    }

    /**
     * Crea un panel con un título centrado y estilizado.
     *
     * @param message Texto del título.
     * @return JPanel con el título.
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
     * Actualiza las tres tablas con los datos proporcionados desde el controlador.
     *
     * @param quantitats Cantidades de cada tipo de generador.
     * @param proudccioUnitat Producción por unidad.
     * @param precioBase Precios actuales de cada generador.
     * @param costMultiplicadores Costes de mejora.
     * @param multiplicadores Multiplicadores actuales.
     */
    public void updateTablas(ArrayList<Integer> quantitats, ArrayList<String> proudccioUnitat, ArrayList<Integer> precioBase, ArrayList<Integer> costMultiplicadores, ArrayList<Integer> multiplicadores) {
        tableGeneradorsDisponibles.setUpdateValores(quantitats,proudccioUnitat, multiplicadores);
        tableBotigaGenerators.setUpdateValores(precioBase, proudccioUnitat);
        tableBotigaMillores.setUpdateValores(costMultiplicadores, multiplicadores);

        jPanel.revalidate();
        jPanel.repaint();
    }

    /**
     * Crea el botón "Finish Game" que finaliza la partida.
     *
     * @return JButton configurado.
     */
    public JButton addBotonFinishGame() {
        JButton finishGameButton = new JButton("Finish Game");
        finishGameButton.setActionCommand("FINISHGAME");
        finishGameButton.addActionListener(gameController);

        return finishGameButton;
    }

    /**
     * Actualiza el contador de cafés disponibles en pantalla.
     */
    public void updateGameScene(){
        numCafesLabel.setText(String.valueOf(gameController.getNumCoffesDisponibles()));
        jPanel.revalidate();
        jPanel.repaint();
    }

}