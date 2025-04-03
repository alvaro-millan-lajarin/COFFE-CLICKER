package Presenstation.Controller;

import Presenstation.View.Scene;
import Presenstation.View.Scenes;

import java.awt.event.ActionEvent;

public class GameCreationController extends Controller {

    public GameCreationController(Scene view, MainController mainController) {
        super(view, mainController);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("PLAY")) {
            mainController.nextScene(Scenes.GAME);
        }else if (e.getActionCommand().equalsIgnoreCase("LOGOUT")) {
            mainController.nextScene(Scenes.MENU);
        }else if (e.getActionCommand().equalsIgnoreCase("DELETE")) {
            mainController.nextScene(Scenes.MENU);
        }
    }
}
