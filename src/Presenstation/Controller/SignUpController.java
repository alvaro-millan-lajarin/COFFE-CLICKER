package Presenstation.Controller;

import Business.Entidades.User;
import Business.ManageUser;
import Persistence.sql.SQLUserDAO;
import Presenstation.View.Scenes.LoginScene;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;
import Presenstation.View.Scenes.SignUpScene;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador encargado de gestionar el registro de nuevos usuarios.
 * Valida los datos introducidos y crea la cuenta si es válida.
 */
public class SignUpController implements ActionListener {
    private final ManageUser manageUser;
    private final SignUpScene signUpScene;
    private final MainController mainController;

    /**
     * Constructor del SignUpController.
     *
     * @param view Escena de registro.
     * @param mainController Controlador principal.
     * @param manageUser Lógica de gestión de usuarios.
     */
    public SignUpController(SignUpScene view, MainController mainController, ManageUser manageUser) {
        this.manageUser = manageUser;
        this.signUpScene = view;
        this.mainController = mainController;

    }

    /**
     * Devuelve la escena de registro actual.
     *
     * @return SignUpScene.
     */
    public SignUpScene getScene() {
        return signUpScene;
    }

    /**
     * Maneja el evento de registro. Si los datos son válidos, se crea el usuario y se cambia a la escena de gestión.
     *
     * @param e Evento de acción recibido.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("SIGNUP")) {
            mainController.resetGameManagement();
            if(manageUser.signUp(getScene().getName(), getScene().getEmail(), getScene().getPassword(), getScene(), getScene().getPasswordAgain())){
                mainController.nextScene(Scenes.GAME_MANAGEMENT);
            }
        }
    }
}
