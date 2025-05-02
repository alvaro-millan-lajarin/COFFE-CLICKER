package Presenstation.Controller;

import Business.ManageUser;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;

import java.awt.event.ActionEvent;

public class StaticsController extends Controller {

    public StaticsController(Scene view, MainController mainController, ManageUser manageUser) {

        super(view, mainController);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("STADISTICAS")) {
            mainController.nextScene(Scenes.STATICS);
        }
    }

}
