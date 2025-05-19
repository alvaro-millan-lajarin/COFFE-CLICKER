package Presenstation.Controller;

import Presenstation.View.Scenes.MenuScene;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private static final String LOGIN = "LOGIN";
    private static final String SIGNUP = "SIGNUP";
    private final MainController mainController;


    public MenuController(MenuScene menuScene, MainController mainController) {

        this.mainController = mainController;

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
