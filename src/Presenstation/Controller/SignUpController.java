package Presenstation.Controller;

import Presenstation.View.LoginScene;
import Presenstation.View.Scene;
import Presenstation.View.Scenes;
import Presenstation.View.SignUpScene;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SignUpController extends Controller {

    public SignUpController(Scene view, MainController mainController) {
        super(view, mainController);

    }
    public SignUpScene getScene() {

        return (SignUpScene) super.getView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("ACCES")) {
            if (getScene().getEmail().isEmpty()) {
                JOptionPane.showMessageDialog(
                        getScene().addAccesButton(),
                        "The aquarium is close you people will enter when the aquarium is open.",
                        "People can't enter now",
                        JOptionPane.WARNING_MESSAGE
                );
            }
            //mainController.nextScene(Scenes.LOGIN);
        }

    }
}
