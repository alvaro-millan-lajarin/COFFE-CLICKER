package Presenstation.Controller;

import Presenstation.View.Scene;

import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener {
    private Scene scene;
    MainController mainController;

    public Controller(Scene view, MainController mainController) {
        this.scene = view;
        this.mainController = mainController;
    }

    public final void run() {

        scene.apply(mainController.getMainFrame());
    }

    public Scene getView() {
        return scene;
    }
}