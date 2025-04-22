package Presenstation.Controller;

import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;

import java.awt.event.ActionEvent;

public class MenuController extends Controller {
    private static final String LOGIN = "LOGIN";
    private static final String SIGNUP = "SIGNUP";

    public MenuController(Scene view, MainController mainController) {
        super(view, mainController);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase(LOGIN)) {
            mainController.nextScene(Scenes.LOGIN);
        } else if (e.getActionCommand().equalsIgnoreCase(SIGNUP)) {
            mainController.nextScene(Scenes.SIGNUP);
        }
    }
}
