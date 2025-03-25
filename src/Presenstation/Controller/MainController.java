package Presenstation.Controller;

import Presenstation.View.*;

import javax.swing.*;


public class MainController {
    private MenuScene menuScene;
    private Scene scene;
    private SignUpScene signUpScene;
    private LoginScene loginScene;



    private LoginController loginController;
    private SignUpController signUpController;
    private MenuController menuController;
    public MainController() {
        menuScene = new MenuScene();
        scene = new Scene();
        signUpScene = new SignUpScene();
        loginScene = new LoginScene();

        loginController = new LoginController(loginScene, this);
        signUpController = new SignUpController(signUpScene, this);
        menuController = new MenuController(menuScene, this);

        menuScene.setController(menuController);

    }
    public void nextScene(Scenes scenes) {
        switch (scenes) {
            case MENU:
                menuController.run();
                break;
            case LOGIN:
                loginController.run();
                break;
            case SIGNUP:
                scene.showVisible();
                signUpScene.apply(scene.getMainFrame());
                //signUpController.run();
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
