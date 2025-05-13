package Presenstation.Controller;

import Business.Entidades.Game;
import Business.ManageGame;
import Business.ManageUser;
import Presenstation.Messages;
import Presenstation.View.Grafica.Grafica;
import Presenstation.View.Refresh.UpdateGrafica;
import Presenstation.View.Scenes.GameCreationScene;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;
import java.time.LocalDateTime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class GameCreationController extends Controller {
    private LoginController loginController;
    private SignUpController signUpController;
    private Game game;
    private ManageGame manageGame;
    private Messages messages = new Messages();
    private ManageUser manageUser;
    private Grafica grafica;
    private GameController gameController;
    private UpdateGrafica updateGrafica;

    public GameCreationController(Scene view, MainController mainController, LoginController loginController, SignUpController signUpController, ManageGame manageGame, ManageUser manageUser, GameController gameController) {

        super(view, mainController);
        this.loginController = loginController;
        this.signUpController = signUpController;
        this.manageGame = manageGame;
        this.manageUser = manageUser;
        this.gameController = gameController;

    }
    public GameCreationScene getScene() {

        return (GameCreationScene) super.getView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("PLAY")) {


            String nombreGame = getScene().getName();

            List<Game> games = manageGame.getAllGames();
            for (Game game : games) {
                if (game.getNombre().equals(nombreGame) && game.getIdUser() == manageUser.getCurrentUser().getId()) {
                    messages.gameNameUsed();
                    return;

                }
            }
            LocalDateTime fechaYHoraActual = LocalDateTime.now();
            game = new Game(1,manageUser.getCurrentUser().getId(),nombreGame,fechaYHoraActual,fechaYHoraActual,0, manageGame);
            manageGame.addGame(game);
            manageGame.setGame(game);
            manageGame.addBasicGenerator();
            game.setManageGame(manageGame);

            grafica = new Grafica(new ArrayList<>());
            updateGrafica = new UpdateGrafica(manageGame, grafica);
            updateGrafica.start();
            gameController.setUpdateGrafica(updateGrafica);
            //gameController.iniciarRegistroCafes(manageGame.getGame(), grafica);

            mainController.nextScene(Scenes.GAME);

        }else if (e.getActionCommand().equalsIgnoreCase("LOGOUT")) {
            mainController.resetLogin();
            mainController.nextScene(Scenes.MENU);
        }else if (e.getActionCommand().equalsIgnoreCase("DELETE")) {
            mainController.resetLogin();
            loginController.clearUserData();
            deleteUser();

        }
    }
    public void deleteUser() {
        int confirm = messages.confirmDelete();

        if (confirm == JOptionPane.YES_OPTION) {
            manageUser.deleteUser();
            messages.deleteUser();
            loginController.clearUserData();
            mainController.nextScene(Scenes.MENU);
        }

    }


}
