package Presenstation.Controller;

import Presenstation.View.Scenes.MenuScene;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador del menú principal.
 * Gestiona la navegación hacia las escenas de login o registro según la acción del usuario.
 */
public class MenuController implements ActionListener {
    private static final String LOGIN = "LOGIN";
    private static final String SIGNUP = "SIGNUP";
    private final MainController mainController;

    /**
     * Constructor del MenuController.
     *
     * @param menuScene Escena del menú (no se utiliza directamente en este controlador).
     * @param mainController Controlador principal de la aplicación.
     */
    public MenuController(MenuScene menuScene, MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Maneja los eventos de acción del menú.
     * Redirige al usuario a la escena correspondiente (login o registro).
     *
     * @param e Evento de acción recibido.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase(LOGIN)) {
            mainController.nextScene(Scenes.LOGIN);
        } else if (e.getActionCommand().equalsIgnoreCase(SIGNUP)) {
            mainController.nextScene(Scenes.SIGNUP);
        }
    }
}
