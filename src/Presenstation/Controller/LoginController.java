package Presenstation.Controller;

import Presenstation.View.Scene;
import Presenstation.View.Scenes;

import java.awt.event.ActionEvent;

public class LoginController extends Controller {
    private static final String GAME_MANAGEMENT = "GAME_MANAGEMENT";

    public LoginController(Scene view, MainController mainController) {
        super(view, mainController);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase(GAME_MANAGEMENT)) {
            mainController.nextScene(Scenes.GAME_MANAGEMENT);
        }
    }
}
