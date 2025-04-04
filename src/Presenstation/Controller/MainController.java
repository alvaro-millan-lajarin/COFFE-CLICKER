package Presenstation.Controller;

import Presenstation.View.*;

import javax.swing.*;


public class MainController {
    private MenuScene menuScene;
    private Scene scene;
    private SignUpScene signUpScene;
    private LoginScene loginScene;
    private GameManagementScene gameManagementScene;
    private GameCreationScene gameCreationScene;
    private GameScene gameScene;



    private LoginController loginController;
    private SignUpController signUpController;
    private MenuController menuController;
    private GameManagementController gameManagementController;
    private GameCreationController gameCreationController;
    private GameController gameController;

    public MainController() {
        menuScene = new MenuScene();
        scene = new Scene();
        signUpScene = new SignUpScene();
        loginScene = new LoginScene();
        gameManagementScene = new GameManagementScene();
        gameCreationScene = new GameCreationScene();
        gameScene = new GameScene();


        loginController = new LoginController(loginScene, this);
        signUpController = new SignUpController(signUpScene, this);
        menuController = new MenuController(menuScene, this);
        gameManagementController = new GameManagementController(gameManagementScene, this, loginController, signUpController);
        gameCreationController = new GameCreationController(gameCreationScene, this, loginController, signUpController);
        gameController = new GameController(gameScene, this, loginController, signUpController);

        menuScene.setController(menuController);
        signUpScene.setController(signUpController);
        loginScene.setController(loginController);
        gameManagementScene.setController(gameManagementController);
        gameCreationScene.setController(gameCreationController);
        gameScene.setController(gameController);

    }
    public void nextScene(Scenes scenes) {


        switch (scenes) {
            case MENU:
                menuController.run();
                break;
            case LOGIN:
                loginScene.clearUserData();
                loginScene.apply(this.getMainFrame());
                //loginController.run();
                break;
            case SIGNUP:
                //scene.showVisible();
                signUpScene.apply(scene.getMainFrame());
                //signUpController.run();
                break;
            case GAME_MANAGEMENT:
                    gameManagementScene.apply(scene.getMainFrame());
                break;
            case GAME_CREATION:
                    gameCreationScene.apply(scene.getMainFrame());
                break;
            case GAME:
                gameScene.apply(scene.getMainFrame());
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
