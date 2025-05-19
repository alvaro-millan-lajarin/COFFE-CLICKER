package Presenstation.Controller;

import Business.Entidades.Game;
import Business.ManageGame;
import Business.ManageUser;
import Presenstation.Messages;
import Presenstation.View.Grafica.Grafica;
import Business.Refresh.UpdateGrafica;
import Presenstation.View.Scenes.GameCreationScene;
import Presenstation.View.Scenes.GameManagementScene;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;

import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class GameCreationController implements ActionListener {
    private LoginController loginController;
    private Game game;
    private final ManageGame manageGame;
    private final Messages messages = new Messages();
    private final ManageUser manageUser;
    private Grafica grafica;
    private final GameController gameController;
    private UpdateGrafica updateGrafica;
    private final static int MAX_LENGTH = 50;
    private final GameCreationScene gameCreationScene;
    private final MainController mainController;

    public GameCreationController(GameCreationScene view, MainController mainController, LoginController loginController, SignUpController signUpController, ManageGame manageGame, ManageUser manageUser, GameController gameController) {

        this.gameCreationScene = view;
        this.loginController = loginController;
        this.manageGame = manageGame;
        this.manageUser = manageUser;
        this.gameController = gameController;
        this.mainController = mainController;

    }
    public GameCreationScene getScene() {

        return gameCreationScene;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("PLAY")) {


            String nombreGame = getScene().getName();
            if(nombreGame.length() > MAX_LENGTH){
                messages.tooLong();
                return;
            }

            List<Game> games = manageGame.getAllGames();
            for (Game game : games) {
                if (game.getNombre().equals(nombreGame) && game.getIdUser() == manageUser.getCurrentUser().getId()) {
                    messages.gameNameUsed();
                    return;

                }
            }
            LocalDateTime fechaYHoraActual = LocalDateTime.now();
            game = new Game(1,manageUser.getCurrentUser().getId(),nombreGame,fechaYHoraActual,fechaYHoraActual,0, false);
            manageGame.addGame(game);
            manageGame.setGame(game);
            manageGame.addBasicGenerator();


            grafica = new Grafica(new ArrayList<>());
            updateGrafica = new UpdateGrafica(manageGame, grafica);
            updateGrafica.start();
            gameController.setUpdateGrafica(updateGrafica);


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
