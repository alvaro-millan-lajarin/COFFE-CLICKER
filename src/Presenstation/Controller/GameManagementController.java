package Presenstation.Controller;

import Business.Entidades.Game;
import Business.Entidades.User;
import Business.ManageStatics;
import Business.ManageUser;
import Persistence.GameDAO;
import Persistence.sql.SQLGameDAO;
import Persistence.sql.SQLUserDAO;
import Presenstation.Messages;
import Presenstation.View.Scenes.GameManagementScene;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameManagementController extends Controller {
    private LoginController loginController;
    private SignUpController signUpController;
    private SQLUserDAO sqlUserDAO = new SQLUserDAO();
    private ManageStatics manageStatics;
    private ManageUser manageUser;
    private Messages messages;

    public GameManagementController(Scene view, MainController mainController, LoginController loginController, SignUpController signUpController, ManageUser manageUser) {
        super(view, mainController);
        this.loginController = loginController;
        this.signUpController = signUpController;
        this.manageStatics = new ManageStatics();
        this.manageUser = manageUser;
        messages = new Messages();
    }

    public GameManagementScene getScene() {

        return (GameManagementScene) super.getView();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("LOGOUT")) {
            //loginController.clearUserData();
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
            if (selectedGame != null) {
                mainController.resumeGame(selectedGame);
            } else {
                messages.seleccionaPartida();
            }
        }else if (e.getActionCommand().equals("STADISTICAS")) {
            Game selectedGame = getScene().getSelectedGame();
            mostrarGraficaDeCafes(selectedGame.getId());
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
                GameDAO sqlGameDAO = new SQLGameDAO();
                sqlGameDAO.deleteGame(selectedGame);
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
}
