package Presenstation.Controller;

import Presenstation.View.Scene;
import Presenstation.View.Scenes;


public class MainController {
    Scene scene;
    public void nextScene(Scenes scenes) {
        switch (scenes) {
            case MAIN:

                break;

        }
    }
    public void run() {
        scene.showVisible();
        loginScene.apply(mainView.getMainFrame());
        musicPlayer.play();
    }
}
