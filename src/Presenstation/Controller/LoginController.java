package Presenstation.Controller;

import Business.Entidades.User;
import Business.ManageGame;
import Business.ManageUser;

import Presenstation.Messages;
import Presenstation.View.Scenes.LoginScene;

import Presenstation.View.Scenes.Scenes;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador encargado de gestionar el inicio de sesión del usuario.
 * Valida las credenciales e inicia la navegación a la escena de gestión de partidas.
 */
public class LoginController implements ActionListener {
    private static final String GAME_MANAGEMENT = "GAME_MANAGEMENT";
    private MainController mainController;
    private ManageUser manageUser;
    private LoginScene loginScene;
    private final Messages messages;
    private final static int MAX_LENGTH = 50;

    /**
     * Constructor del LoginController.
     *
     * @param view Vista de login.
     * @param mainController Controlador principal.
     * @param manageUser Lógica de gestión de usuarios.
     */
    public LoginController(LoginScene view, MainController mainController, ManageUser manageUser) {

        this.manageUser = manageUser;
        this.loginScene = view;
        this.mainController = mainController;
        messages = new Messages();
    }

    /**
     * Devuelve la escena de login actual.
     *
     * @return LoginScene.
     */
    public LoginScene getScene() {
        return loginScene;
    }

    /**
     * Maneja el evento de login: valida credenciales y navega a la gestión de partidas si son correctas.
     *
     * @param e Evento de acción recibido.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equalsIgnoreCase(GAME_MANAGEMENT)) {
            mainController.resetGameManagement();
            String userOrEmail = getScene().getEmail();
            String password = getScene().getPassword();
            if (manageUser.userLoginCorrect(userOrEmail, password) && userOrEmail.length() < MAX_LENGTH && password.length() < MAX_LENGTH){
                mainController.nextScene(Scenes.GAME_MANAGEMENT);
                return;
            }
            messages.incorrectLogin();
        }
    }

    /**
     * Limpia los datos introducidos en el formulario de login.
     */
    public void clearUserData() {
        getScene().clearUserData();
    }

}
