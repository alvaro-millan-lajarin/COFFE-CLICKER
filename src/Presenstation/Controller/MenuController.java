package Presenstation.Controller;

import Presenstation.View.Scene;
import Presenstation.View.Scenes;

import java.awt.event.ActionEvent;

public class MenuController extends Controller {
    public static final String LOGIN = "LOGIN";
    public static final String SIGNUP = "SIGNUP";

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
