package Presenstation.Controller;

import Business.Entidades.User;
import Business.ManageGame;
import Business.ManageUser;
import Persistence.sql.SQLUserDAO;
import Presenstation.Messages;
import Presenstation.View.Scenes.LoginScene;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginController extends Controller {
    private static final String GAME_MANAGEMENT = "GAME_MANAGEMENT";
    private ManageUser manageUser;
    private Messages messages = new Messages();

    public LoginController(Scene view, MainController mainController, ManageUser manageUser) {
        super(view, mainController);
        this.manageUser = manageUser;
    }

    public LoginScene getScene() {

        return (LoginScene) super.getView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equalsIgnoreCase(GAME_MANAGEMENT)) {
            mainController.resetGameManagement();
            String userOrEmail = getScene().getEmail();
            String password = getScene().getPassword();

            manageUser.userLoginCorrect(userOrEmail, password);

            if (manageUser.userLoginCorrect(userOrEmail, password)) {
                mainController.nextScene(Scenes.GAME_MANAGEMENT);
                return;
            }
            messages.incorrectLogin();

        }
    }

    public void clearUserData() {
        getScene().clearUserData();
    }

}
