package Presenstation.Controller;

import Business.ManageUser;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;
import Presenstation.View.Scenes.StaticsScene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador de la escena de estadísticas.
 * Gestiona la navegación hacia la vista de estadísticas del juego.
 */
public class StaticsController implements ActionListener {
    private final MainController mainController;

    /**
     * Constructor del StaticsController.
     *
     * @param mainController Controlador principal de la aplicación.
     */
    public StaticsController( MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Maneja el evento para cambiar a la escena de estadísticas.
     *
     * @param e Evento de acción recibido.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("STADISTICAS")) {
            mainController.nextScene(Scenes.STATICS);
        }
    }

}
