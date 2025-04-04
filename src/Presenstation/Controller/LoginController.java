package Presenstation.Controller;

import Business.Entidades.User;
import Persistence.sql.SQLUserDAO;
import Presenstation.View.LoginScene;
import Presenstation.View.Scene;
import Presenstation.View.Scenes;
import Presenstation.View.SignUpScene;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginController extends Controller {
    private static final String GAME_MANAGEMENT = "GAME_MANAGEMENT";

    public LoginController(Scene view, MainController mainController) {
        super(view, mainController);
    }

    public LoginScene getScene() {

        return (LoginScene) super.getView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equalsIgnoreCase(GAME_MANAGEMENT)) {

            String userOrEmail = getScene().getEmail();
            String password = getScene().getPassword();

            SQLUserDAO sqlUserDAO = new SQLUserDAO();


            User existingUserByEmail = sqlUserDAO.findUserByEmail(userOrEmail);
            User existingUserByUsername = sqlUserDAO.findUserByUsername(userOrEmail);


            if (existingUserByEmail != null && existingUserByEmail.getPassword().equals(password)) {
                mainController.nextScene(Scenes.GAME_MANAGEMENT);
                return;
            }

            if (existingUserByUsername != null && existingUserByUsername.getPassword().equals(password)) {
                mainController.nextScene(Scenes.GAME_MANAGEMENT);
                return;
            }
                JOptionPane.showMessageDialog(
                        getScene().addAccesButton(),
                        "Incorrect email, username or password",
                        "Error login",
                        JOptionPane.WARNING_MESSAGE
                );


        }
    }

}
