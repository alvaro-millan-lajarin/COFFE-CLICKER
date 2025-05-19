package Presenstation.Controller;

import Business.Entidades.Game;
import Business.Entidades.User;
import Business.ManageGame;
import Business.ManageStatics;
import Business.ManageUser;
import Persistence.sql.SQLStatisticDAO;
import Presenstation.Messages;
import Presenstation.View.Grafica.Grafica;
import Business.Refresh.UpdateGrafica;
import Presenstation.View.Scenes.GameManagementScene;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameManagementController implements ActionListener {
    private final LoginController loginController;

    private final ManageStatics manageStatics;
    private final ManageUser manageUser;
    private final Messages messages;
    private final ManageGame manageGame;
    private GameController gameController;
    private Grafica grafica;
    private final GameManagementScene gameManagementScene;
    private MainController mainController;

    public GameManagementController(GameManagementScene gameManagementScene, MainController mainController, LoginController loginController, SignUpController signUpController, ManageUser manageUser, ManageGame manageGame, GameController gameController) {

        this.loginController = loginController;
        this.manageStatics = new ManageStatics(new SQLStatisticDAO());
        this.manageUser = manageUser;
        this.messages = new Messages();
        this.manageGame = manageGame;
        this.gameController = gameController;
        this.gameManagementScene = gameManagementScene;
        this.mainController = mainController;
    }

    public GameManagementScene getScene() {

        return gameManagementScene;
    }


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

                UpdateGrafica updateGrafica = new UpdateGrafica(manageGame, grafica);
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
    public void deleteUser() {
        int confirm = messages.confirmDelete();

        if (confirm == JOptionPane.YES_OPTION) {

            manageUser.deleteUser();
            messages.deleteUser();
            loginController.clearUserData();
            mainController.nextScene(Scenes.MENU);
        }

    }

    public User getUser() {

        return manageUser.getCurrentUser();
    }

    public void deleteSelectedGame() {

        Game selectedGame = getScene().getSelectedGame();

        if (selectedGame != null) {
            int confirm = messages.deleteGame();


            if (confirm == JOptionPane.YES_OPTION) {
                manageGame.deleteGameSelected(selectedGame);
                messages.deleteGameSucces();
            }
        } else {
            messages.seleccionaPartida();

        }
        mainController.resetGameManagement();
        mainController.nextScene(Scenes.GAME_MANAGEMENT);
    }
    public void mostrarGraficaDeCafes(int idPartida) {
        manageStatics.mostrarGraficaCafes(idPartida);
    }
    public List<Game> getAllGames() {
       return manageGame.getAllGames();
    }
}
