package Presenstation.Controller;

import Business.Entidades.Game;
import Business.ManageGame;
import Business.ManageStatics;
import Business.ManageUser;
import Presenstation.View.Scenes.*;

import javax.swing.*;


public class MainController {
    private MenuScene menuScene;
    private Scene scene;
    private SignUpScene signUpScene;
    private LoginScene loginScene;
    private Game game;
    private GameManagementScene gameManagementScene;
    private GameCreationScene gameCreationScene;
    private GameScene gameScene;
    private StaticsScene staticsScene;


    private LoginController loginController;
    private SignUpController signUpController;
    private MenuController menuController;
    private GameManagementController gameManagementController;
    private GameCreationController gameCreationController;
    private GameController gameController;
    private StaticsController staticsController;

    private ManageGame manageGame;
    private ManageStatics manageStatics;
    private ManageUser manageUser;

    public MainController() {

        manageGame = new ManageGame();
        manageStatics = new ManageStatics();
        manageUser = new ManageUser();

        menuScene = new MenuScene();
        scene = new Scene();
        signUpScene = new SignUpScene();
        loginScene = new LoginScene();
        gameManagementScene = new GameManagementScene();
        gameCreationScene = new GameCreationScene();
        gameScene = new GameScene();
        staticsScene = new StaticsScene();


        loginController = new LoginController(loginScene, this, manageUser);
        signUpController = new SignUpController(signUpScene, this, manageUser);
        menuController = new MenuController(menuScene, this);
        gameManagementController = new GameManagementController(gameManagementScene, this, loginController, signUpController);
        gameCreationController = new GameCreationController(gameCreationScene, this, loginController, signUpController, manageGame);
        gameController = new GameController(gameScene, this, loginController, signUpController,manageGame);
        staticsController = new StaticsController(staticsScene, this);

        menuScene.setController(menuController);
        signUpScene.setController(signUpController);
        loginScene.setController(loginController);
        gameManagementScene.setController(gameManagementController);
        gameCreationScene.setController(gameCreationController);
        gameScene.setController(gameController);
        staticsScene.setController(staticsController);



    }
    public void nextScene(Scenes scenes) {
        scene.clean();

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
            case STATICS:
                staticsScene.apply(scene.getMainFrame());
                break;

        }
    }

    public void resumeGame(Game game) {

        manageGame.setGame(game);
        game.inicialitzarGeneradors();
        gameScene = new GameScene();
        gameController = new GameController(gameScene, this, loginController, signUpController, manageGame);

        gameScene.setController(gameController);
        gameScene.apply(getMainFrame());
    }


    public void run() {
        scene.showVisible();
        menuScene.apply(scene.getMainFrame());
        //musicPlayer.play();
    }
    public JFrame getMainFrame() {
        return scene.getMainFrame();
    }
    public void resetLogin() {

        loginScene = null;
        loginController = null;


        signUpScene = null;
        signUpController = null;

        System.gc();


        loginScene = new LoginScene();
        loginController = new LoginController(loginScene, this, manageUser);
        loginScene.setController(loginController);

        signUpScene = new SignUpScene();
        signUpController = new SignUpController(signUpScene, this, manageUser);
        signUpScene.setController(signUpController);
    }
    public void resetGameCreation() {
        gameCreationScene = null;
        gameCreationController = null;

        gameCreationScene= new GameCreationScene();
        gameCreationController= new GameCreationController(gameCreationScene,this,loginController,signUpController,manageGame);
        gameCreationScene.setController(gameCreationController);
    }
    public void resetGameManagement() {
        gameManagementScene = null;
        gameManagementController = null;

        gameManagementScene= new GameManagementScene();
        gameManagementController= new GameManagementController(gameManagementScene,this,loginController,signUpController);
        gameManagementScene.setController(gameManagementController);
    }
    public void resetGame(){
        gameScene = null;
        gameController = null;

        gameScene= new GameScene();
        gameController= new GameController(gameScene,this,loginController,signUpController, manageGame);
        gameScene.setController(gameController);

    }
}
