package Presenstation.Controller;

import Business.Entidades.User;
import Business.ManageUser;
import Persistence.sql.SQLUserDAO;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;
import Presenstation.View.Scenes.SignUpScene;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SignUpController extends Controller {
    private ManageUser manageUser;


    public SignUpController(Scene view, MainController mainController, ManageUser manageUser) {
        super(view, mainController);
        this.manageUser = manageUser;

    }
    public SignUpScene getScene() {

        return (SignUpScene) super.getView();
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


    public String getEmail() {
        return getScene().getEmail();
    }
    public String getPassword() {
        return getScene().getPassword();
    }
}
