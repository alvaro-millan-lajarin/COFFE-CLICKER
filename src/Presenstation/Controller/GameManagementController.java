package Presenstation.Controller;

import Business.Entidades.Game;
import Business.Entidades.User;
import Business.ManageGameGenerators;
import Business.ManageStatics;
import Business.ManageUser;
import Persistence.sql.SQLStatisticDAO;
import Presenstation.Messages;
import Presenstation.View.Grafica.Grafica;
import Business.Refresh.UpdateGrafica;
import Presenstation.View.Scenes.GameManagementScene;
import Presenstation.View.Scenes.Scenes;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de la escena de gestión de partidas.
 * Permite crear, reanudar, eliminar partidas y consultar estadísticas.
 */
public class GameManagementController implements ActionListener {
    private final LoginController loginController;
    private final ManageStatics manageStatics;
    private final ManageUser manageUser;
    private final Messages messages;
    private final ManageGameGenerators manageGameGenerators;
    private GameController gameController;
    private Grafica grafica;
    private final GameManagementScene gameManagementScene;
    private MainController mainController;

    /**
     * Constructor de GameManagementController.
     *
     * @param gameManagementScene Escena de gestión de partidas.
     * @param mainController Controlador principal.
     * @param loginController Controlador de login.
     * @param signUpController Controlador de registro (no usado directamente aquí).
     * @param manageUser Lógica de usuarios.
     * @param manageGameGenerators Lógica del juego.
     * @param gameController Controlador de juego.
     */
    public GameManagementController(GameManagementScene gameManagementScene, MainController mainController, LoginController loginController, SignUpController signUpController, ManageUser manageUser, ManageGameGenerators manageGameGenerators, GameController gameController, ManageStatics manageStatics) {

        this.loginController = loginController;
        this.manageStatics = manageStatics;
        this.manageUser = manageUser;
        this.messages = new Messages();
        this.manageGameGenerators = manageGameGenerators;
        this.gameController = gameController;
        this.gameManagementScene = gameManagementScene;
        this.mainController = mainController;
    }

    /**
     * Devuelve la escena de gestión de partidas.
     *
     * @return GameManagementScene actual.
     */
    public GameManagementScene getScene() {
        return gameManagementScene;
    }


    /**
     * Maneja los eventos de acción de la escena: logout, borrar usuario, crear partida, reanudar o ver estadísticas.
     *
     * @param e Evento de acción recibido.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("LOGOUT")) {

            mainController.resetLogin();
            mainController.nextScene(Scenes.MENU);
        }else if (e.getActionCommand().equalsIgnoreCase("DELETE")) {
            mainController.resetLogin();
            deleteUser();


        }else if (e.getActionCommand().equalsIgnoreCase("CREATE_GAME")) {
            mainController.resetGameCreation();

            mainController.nextScene(Scenes.GAME_CREATION);
        } else if (e.getActionCommand().equalsIgnoreCase("RESUME")) {
            mainController.resetGame();
            Game selectedGame = getScene().getSelectedGame();
            if( selectedGame.isFinished()){
                messages.gameFinishedCantResume();
                return;
            }
            if (selectedGame != null) {
                grafica = new Grafica(new ArrayList<>());
                mainController.resumeGame(selectedGame);
                gameController = mainController.getGameController();

                UpdateGrafica updateGrafica = new UpdateGrafica(manageGameGenerators, grafica, manageStatics);
                updateGrafica.start();
                gameController.setUpdateGrafica(updateGrafica);

            } else {
                messages.seleccionaPartida();
            }
        }else if (e.getActionCommand().equals("STADISTICAS")) {
            Game selectedGame = getScene().getSelectedGame();
            if (selectedGame != null && selectedGame.isFinished()) {
                mostrarGraficaDeCafes(selectedGame.getId());
            } else {
                messages.stadisticasNoDisponibles();
            }
        }
    }

    /**
     * Elimina el usuario actual tras confirmación y vuelve al menú principal.
     */
    public void deleteUser() {
        int confirm = messages.confirmDelete();

        if (confirm == JOptionPane.YES_OPTION) {

            manageUser.deleteUser();
            messages.deleteUser();
            loginController.clearUserData();
            mainController.nextScene(Scenes.MENU);
        }

    }

    /**
     * Devuelve el usuario actualmente logueado.
     *
     * @return Usuario actual.
     */
    public User getUser() {
        return manageUser.getCurrentUser();
    }

    /**
     * Elimina la partida seleccionada tras confirmación del usuario.
     */
    public void deleteSelectedGame() {
        Game selectedGame = getScene().getSelectedGame();

        if (selectedGame != null) {
            int confirm = messages.deleteGame();

            if (confirm == JOptionPane.YES_OPTION) {
                manageGameGenerators.deleteGameSelected(selectedGame);
                messages.deleteGameSucces();
            }
        } else {
            messages.seleccionaPartida();

        }
        mainController.resetGameManagement();
        mainController.nextScene(Scenes.GAME_MANAGEMENT);
    }

    /**
     * Muestra la gráfica de evolución de cafés de la partida seleccionada.
     *
     * @param idPartida ID de la partida.
     */
    public void mostrarGraficaDeCafes(int idPartida) {
        manageStatics.mostrarGraficaCafes(idPartida);
    }

    /**
     * Devuelve todas las partidas registradas.
     *
     * @return Lista de partidas.
     */
    public List<Game> getAllGames() {
       return manageGameGenerators.getAllGames();
    }
}
