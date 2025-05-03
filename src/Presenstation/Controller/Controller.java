package Presenstation.Controller;

import Business.ManageUser;
import Presenstation.View.Scenes.Scene;

import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener {
    private Scene scene;
    MainController mainController;
    private ManageUser manageUser;

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

    public void hello(){
        System.out.println("Hello World");
    }
}