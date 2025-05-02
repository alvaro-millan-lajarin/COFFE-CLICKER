package Presenstation.Controller;

import Business.Entidades.Game;
import Business.Entidades.User;
import Business.ManageStatics;
import Persistence.GameDAO;
import Persistence.sql.SQLGameDAO;
import Persistence.sql.SQLUserDAO;
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

    public GameManagementController(Scene view, MainController mainController, LoginController loginController, SignUpController signUpController) {
        super(view, mainController);
        this.loginController = loginController;
        this.signUpController = signUpController;
        this.manageStatics = new ManageStatics();
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
                JOptionPane.showMessageDialog(null, "Selecciona una partida primero.");
            }
        }else if (e.getActionCommand().equals("STADISTICAS")) {
            Game selectedGame = getScene().getSelectedGame();
            mostrarGraficaDeCafes(selectedGame.getId());
        }

        //Implementar els botons DELETEGAME



    }
    public void deleteUser() {
        int confirm = JOptionPane.showConfirmDialog(
                getScene().addAccesButton(),
                "Are you sure you want to delete your account?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {

            String emailLogin = signUpController.getEmail();
            if (emailLogin.isEmpty()) {
                emailLogin = loginController.getEmail();

            }
            User user = sqlUserDAO.findUserByEmail(emailLogin);
            if (user == null) {
                user = sqlUserDAO.findUserByUsername(emailLogin);
            }
            sqlUserDAO.deleteUser(user);


            JOptionPane.showMessageDialog(
                    getScene().addAccesButton(),
                    "Account successfully deleted.",
                    "Deletion Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );
            loginController.clearUserData();
            mainController.nextScene(Scenes.MENU);
        }
        mainController.nextScene(Scenes.MENU);
    }

    public User getUser() {
        String userOrEmail = loginController.getEmail();
        if (userOrEmail.isEmpty()) {
            userOrEmail= signUpController.getEmail();
        }

        SQLUserDAO sqlUserDAO = new SQLUserDAO();


        return sqlUserDAO.findUserByEmail(userOrEmail);
    }

    public void deleteSelectedGame() {

        Game selectedGame = getScene().getSelectedGame();
        if (selectedGame != null) {
            int confirm = JOptionPane.showConfirmDialog(
                    getScene().getPanel(),  // <- aquí está el cambio
                    "Are you sure you want to delete the selected game?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                GameDAO sqlGameDAO = new SQLGameDAO();
                sqlGameDAO.deleteGame(selectedGame);
                JOptionPane.showMessageDialog(getScene().getPanel(), "Game deleted successfully.");
            }
        } else {
            JOptionPane.showMessageDialog(getScene().getPanel(), "Please select a game to delete.");
        }
        mainController.resetGameManagement();
        mainController.nextScene(Scenes.GAME_MANAGEMENT);
    }
    public void mostrarGraficaDeCafes(int idPartida) {
        manageStatics.mostrarGraficaCafes(idPartida);
    }
}
