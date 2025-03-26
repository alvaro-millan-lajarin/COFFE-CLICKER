package Presenstation.Controller;

import Presenstation.View.*;

import javax.swing.*;


public class MainController {
    private MenuScene menuScene;
    private Scene scene;
    private SignUpScene signUpScene;
    private LoginScene loginScene;
    private GameManagementScene gameManagementScene;



    private LoginController loginController;
    private SignUpController signUpController;
    private MenuController menuController;
    private GameManagementController gameManagementController;

    public MainController() {
        menuScene = new MenuScene();
        scene = new Scene();
        signUpScene = new SignUpScene();
        loginScene = new LoginScene();
        gameManagementScene = new GameManagementScene();


        loginController = new LoginController(loginScene, this);
        signUpController = new SignUpController(signUpScene, this);
        menuController = new MenuController(menuScene, this);
        gameManagementController = new GameManagementController(gameManagementScene, this);

        menuScene.setController(menuController);
        signUpScene.setController(signUpController);
        loginScene.setController(loginController);
        gameManagementScene.setController(gameManagementController);

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
                //scene.showVisible();
                signUpScene.apply(scene.getMainFrame());
                //signUpController.run();
                break;
                case GAME_MANAGEMENT:
                    gameManagementScene.apply(scene.getMainFrame());
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
