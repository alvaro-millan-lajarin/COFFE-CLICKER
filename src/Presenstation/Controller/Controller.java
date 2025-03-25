package Presenstation.Controller;

import Presenstation.View.Scene;

import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener {
    private Scene view;
    MainController mainController;

    public Controller(Scene view, MainController mainController) {
        this.view = view;
        this.mainController = mainController;
    }

    public final void run() {

        view.apply(mainController.getMainFrame());
    }


}