package Presenstation.Controller;

import Business.ManageUser;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;
import Presenstation.View.Scenes.StaticsScene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaticsController implements ActionListener {

    private final MainController mainController;

    public StaticsController( MainController mainController) {

        this.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("STADISTICAS")) {
            mainController.nextScene(Scenes.STATICS);
        }
    }

}
