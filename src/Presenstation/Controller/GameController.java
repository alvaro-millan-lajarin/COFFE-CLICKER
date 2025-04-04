package Presenstation.Controller;

import Business.Entidades.User;
import Persistence.sql.SQLUserDAO;
import Presenstation.View.GameManagementScene;
import Presenstation.View.GameScene;
import Presenstation.View.Scene;
import Presenstation.View.Scenes;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameController extends Controller {
    private LoginController loginController;
    private SignUpController signUpController;
    private SQLUserDAO sqlUserDAO = new SQLUserDAO();
    public GameController(Scene view, MainController mainController, LoginController loginController, SignUpController signUpController) {

        super(view, mainController);
        this.loginController = loginController;
        this.signUpController = signUpController;
    }
    public GameScene getScene() {

        return (GameScene) super.getView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("MORE_COFFE")) {
            getView().addCoffe();
        }else if (e.getActionCommand().equalsIgnoreCase("LOGOUT")) {
            mainController.resetLogin();
            mainController.nextScene(Scenes.MENU);
        }else if (e.getActionCommand().equalsIgnoreCase("DELETE")) {
            mainController.resetLogin();
            loginController.clearUserData();
            deleteUser();

        }
    }
    @Override
    public GameScene getView() {
        return (GameScene) super.getView();
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

            String emailNameLogin = loginController.getEmail();
            String emailPasswordRegistre = signUpController.getEmail();


            if(loginController.getEmail().isEmpty() && loginController.getPassword().isEmpty()) {
                User existingUserByEmail = sqlUserDAO.findUserByEmail(emailPasswordRegistre);
                User existingUserByUsername = sqlUserDAO.findUserByUsername(emailPasswordRegistre);
                if (existingUserByEmail != null) {
                    sqlUserDAO.deleteUser(existingUserByEmail);
                }else{
                    sqlUserDAO.deleteUser(existingUserByUsername);
                }
            }else{
                User existingUserByEmail = sqlUserDAO.findUserByEmail(emailNameLogin);
                User existingUserByUsername = sqlUserDAO.findUserByUsername(emailNameLogin);
                if (existingUserByEmail != null) {
                    sqlUserDAO.deleteUser(existingUserByEmail);
                }else{
                    sqlUserDAO.deleteUser(existingUserByUsername);
                }

            }

            JOptionPane.showMessageDialog(
                    getScene().addAccesButton(),
                    "Account successfully deleted.",
                    "Deletion Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            mainController.nextScene(Scenes.MENU);
        }
        mainController.nextScene(Scenes.MENU);
    }
}