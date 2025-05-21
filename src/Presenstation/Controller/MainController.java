package Presenstation.Controller;

import Business.Entidades.Game;
import Business.ManageGame;
import Business.ManageGameGenerators;
import Business.ManageStatics;
import Business.ManageUser;
import Business.Refresh.UpdateGame;
import Persistence.sql.SQLStatisticDAO;
import Persistence.sql.SQLUserDAO;
import Presenstation.View.Scenes.*;

import javax.swing.*;

/**
 * Controlador principal de la aplicación.
 * Gestiona la navegación entre escenas, inicialización de controladores y lógica global del sistema.
 */
public class MainController {
    private final MenuScene menuScene;
    private final Scene scene;
    private SignUpScene signUpScene;
    private LoginScene loginScene;
    private GameManagementScene gameManagementScene;
    private GameCreationScene gameCreationScene;
    private GameScene gameScene;
    private final StaticsScene staticsScene;
    private UpdateGame updateGame;
    private LoginController loginController;
    private SignUpController signUpController;
    private MenuController menuController;
    private GameManagementController gameManagementController;
    private GameCreationController gameCreationController;
    private GameController gameController;
    private StaticsController staticsController;
    private final ManageGameGenerators manageGameGenerators;
    private final ManageUser manageUser;
    private final ManageStatics manageStatics;
    private final ManageGame manageGame;

    /**
     * Constructor que inicializa todas las escenas, controladores y lógica del sistema.
     */
    public MainController() {

        manageGameGenerators = new ManageGameGenerators();
        manageUser = new ManageUser(new SQLUserDAO());
        manageStatics = new ManageStatics(new SQLStatisticDAO());
        manageGame = new ManageGame();

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
        gameManagementController = new GameManagementController(gameManagementScene, this, loginController, signUpController, manageUser, manageGameGenerators, gameController, manageStatics, manageGame);
        gameCreationController = new GameCreationController(gameCreationScene, this, loginController, signUpController, manageGameGenerators, manageUser, gameController, manageStatics, manageGame);
        gameController = new GameController(gameScene, this, loginController, manageGameGenerators, manageUser, manageGame);
        staticsController = new StaticsController( this);

        menuScene.setController(menuController);
        signUpScene.setController(signUpController);
        loginScene.setController(loginController);
        gameManagementScene.setController(gameManagementController);
        gameCreationScene.setController(gameCreationController);
        gameScene.setController(gameController);
        staticsScene.setController(staticsController);

    }

    /**
     * Constructor que inicializa todas las escenas, controladores y lógica del sistema.
     */
    public void nextScene(Scenes scenes) {
        scene.clean();

        switch (scenes) {
            case MENU:

                menuScene.apply(scene.getMainFrame());
                break;
            case LOGIN:
                loginScene.clearUserData();
                loginScene.apply(this.getMainFrame());
                break;
            case SIGNUP:
                signUpScene.apply(scene.getMainFrame());
                break;
            case GAME_MANAGEMENT:
                    gameManagementScene.apply(scene.getMainFrame());
                break;
            case GAME_CREATION:
                    gameCreationScene.apply(scene.getMainFrame());
                break;
            case GAME:
                gameScene.setController(gameController);
                gameScene.apply(scene.getMainFrame());
                updateGame = new UpdateGame(gameScene);
                updateGame.start();
                gameController.setupdateGame(updateGame);
                break;
            case STATICS:
                staticsScene.apply(scene.getMainFrame());
                break;
        }
    }

    /**
     * Restaura una partida guardada, reinicia la escena de juego y sus generadores.
     *
     * @param game Partida a reanudar.
     */
    public void resumeGame(Game game) {
        Game gameNuevo = manageGame.getGameBaseDeDatos(game);
        manageGameGenerators.setGame(gameNuevo);
        manageStatics.setGame(gameNuevo);
        manageGameGenerators.setGeneradores();
        manageGame.setGame(gameNuevo);

        gameScene = new GameScene();
        gameController = new GameController(gameScene, this, loginController, manageGameGenerators, manageUser, manageGame);

        gameScene.setController(gameController);

        gameScene.apply(getMainFrame());
        gameController.updateTablas();
        updateGame = new UpdateGame(gameScene);
        updateGame.start();
        gameController.setupdateGame(updateGame);
    }

    /**
     * Inicia la aplicación mostrando la ventana principal y la escena del menú.
     */
    public void run() {
        scene.showVisible();
        menuScene.apply(scene.getMainFrame());

    }

    /**
     * Devuelve el JFrame principal de la aplicación.
     *
     * @return Ventana principal (JFrame).
     */
    public JFrame getMainFrame() {
        return scene.getMainFrame();
    }

    /**
     * Reinicia la escena y controlador de login, y borra la sesión actual.
     */
    public void resetLogin() {

        manageGame.logout();

        loginScene = null;
        loginController = null;

        signUpScene = null;
        signUpController = null;

        loginScene = new LoginScene();
        loginController = new LoginController(loginScene, this, manageUser);
        loginScene.setController(loginController);

        signUpScene = new SignUpScene();
        signUpController = new SignUpController(signUpScene, this, manageUser);
        signUpScene.setController(signUpController);
    }

    /**
     * Reinicia la escena y el controlador de creación de partidas.
     */
    public void resetGameCreation() {
        gameCreationScene = null;
        gameCreationController = null;

        gameCreationScene= new GameCreationScene();
        gameCreationController= new GameCreationController(gameCreationScene,this,loginController,signUpController, manageGameGenerators, manageUser, gameController, manageStatics, manageGame);
        gameCreationScene.setController(gameCreationController);
    }

    /**
     * Reinicia la escena y el controlador de gestión de partidas.
     */
    public void resetGameManagement() {
        gameManagementScene = null;
        gameManagementController = null;

        gameManagementScene= new GameManagementScene();
        gameManagementController= new GameManagementController(gameManagementScene,this,loginController,signUpController, manageUser, manageGameGenerators, gameController, manageStatics, manageGame);
        gameManagementScene.setController(gameManagementController);
    }

    /**
     * Reinicia la escena y el controlador de juego activo.
     */
    public void resetGame(){
        gameScene = null;
        gameController = null;

        gameScene= new GameScene();
        gameController= new GameController(gameScene,this,loginController, manageGameGenerators, manageUser, manageGame);
        gameScene.setController(gameController);

    }

    /**
     * Devuelve el controlador de juego actual.
     *
     * @return GameController en uso.
     */
    public GameController getGameController() {
        return gameController;
    }
}
