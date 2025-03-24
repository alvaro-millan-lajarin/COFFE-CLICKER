package Presenstation.Controller;

import Presenstation.View.MenuScene;
import Presenstation.View.Scene;
import Presenstation.View.Scenes;

import javax.swing.*;


public class MainController {
    private MenuScene menuScene;
    private Scene scene;

    public MainController() {
        menuScene = new MenuScene();
        scene = new Scene();
    }
    public void nextScene(Scenes scenes) {
        switch (scenes) {
            case MAIN:

                break;

        }
    }
    public void run() {
        scene.showVisible();
        menuScene.apply(scene.getMainFrame());
        //musicPlayer.play();
    }
    public JFrame getMainFrame() {
        return scene.getMainFrame();
    }
}
