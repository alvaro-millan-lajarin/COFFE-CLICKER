package Presenstation.Controller;

import Business.Entidades.Game;
import Business.Entidades.User;
import Business.ManageGame;
import Business.ManageUser;
import Persistence.sql.SQLGameDAO;
import Persistence.sql.SQLUserDAO;
import Presenstation.Messages;
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
    private SQLUserDAO sqlUserDAO = new SQLUserDAO();
    private Game game;
    private ManageGame manageGame;
    private Messages messages = new Messages();
    private ManageUser manageUser;

    public GameCreationController(Scene view, MainController mainController, LoginController loginController, SignUpController signUpController, ManageGame manageGame, ManageUser manageUser) {

        super(view, mainController);
        this.loginController = loginController;
        this.signUpController = signUpController;
        this.manageGame = manageGame;
        this.manageUser = manageUser;

    }
    public GameCreationScene getScene() {

        return (GameCreationScene) super.getView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("PLAY")) {
            String nombreGame = getScene().getName();
            SQLGameDAO sqlGameDAO = new SQLGameDAO();
            List<Game> games = sqlGameDAO.getAllGames();
            for (Game game : games) {
                if (game.getNombre().equals(nombreGame)) {
                    messages.gameNameUsed();
                    return;

                }
            }

            String userOrEmail = loginController.getEmail();
            String password = loginController.getPassword();
            if (userOrEmail.isEmpty() || password.isEmpty()) {
                userOrEmail= signUpController.getEmail();
                password = signUpController.getPassword();
            }

            SQLUserDAO sqlUserDAO = new SQLUserDAO();

            User existingUserByEmail = sqlUserDAO.findUserByEmail(userOrEmail);
            if (existingUserByEmail == null) {
                existingUserByEmail = sqlUserDAO.findUserByUsername(userOrEmail);
            }
            LocalDateTime fechaYHoraActual = LocalDateTime.now();

            if (existingUserByEmail != null && existingUserByEmail.getPassword().equals(password)) {
                game = new Game(1,existingUserByEmail.getId(),nombreGame,fechaYHoraActual,fechaYHoraActual,0);
                sqlGameDAO.addGame(game);

                setGame(sqlGameDAO, nombreGame, existingUserByEmail);

                mainController.nextScene(Scenes.GAME);
            }

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
    public void setGame(SQLGameDAO sqlGameDAO, String nombreGame, User user) {
        List<Game> gamesArray = sqlGameDAO.getAllGames();

        for (Game game : gamesArray) {
            if (game.getIdUser() == user.getId() && game.getNombre().equals(nombreGame)) {
                Game gameCopy = sqlGameDAO.getGame(game.getId());
                manageGame.setGame(gameCopy);
            }
        }


    }

}
