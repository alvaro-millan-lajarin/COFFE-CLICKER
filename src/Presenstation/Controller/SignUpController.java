package Presenstation.Controller;

import Business.Entidades.User;
import Business.ManageUser;
import Persistence.sql.SQLUserDAO;
import Presenstation.View.Scenes.LoginScene;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;
import Presenstation.View.Scenes.SignUpScene;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpController implements ActionListener {
    private final ManageUser manageUser;
    private final SignUpScene signUpScene;
    private final MainController mainController;


    public SignUpController(SignUpScene view, MainController mainController, ManageUser manageUser) {

        this.manageUser = manageUser;
        signUpScene = view;
        this.mainController = mainController;

    }
    public SignUpScene getScene() {

        return signUpScene;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("SIGNUP")) {
            mainController.resetGameManagement();
            if(manageUser.signUp(getScene().getName(), getScene().getEmail(), getScene().getPassword(), getScene(), getScene().getPasswordAgain())){
                mainController.nextScene(Scenes.GAME_MANAGEMENT);
            }
        }
    }
}
