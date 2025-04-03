package Presenstation.Controller;

import Presenstation.View.GameScene;
import Presenstation.View.Scene;
import Presenstation.View.Scenes;

import java.awt.event.ActionEvent;

public class GameController extends Controller {
    public GameController(Scene view, MainController mainController) {
        super(view, mainController);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("MORE_COFFE")) {
            getView().addCoffe();
        }else if (e.getActionCommand().equalsIgnoreCase("LOGOUT")) {
            mainController.nextScene(Scenes.MENU);
        }else if (e.getActionCommand().equalsIgnoreCase("DELETE")) {
            mainController.nextScene(Scenes.MENU);
        }
    }
    @Override
    public GameScene getView() {
        return (GameScene) super.getView();
    }
}