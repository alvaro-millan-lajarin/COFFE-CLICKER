package Business.Refresh;
import Presenstation.View.Scenes.GameScene;

/**
 * Hilo encargado de actualizar periódicamente la escena del juego.
 */
public class UpdateGame extends Thread {
    GameScene gameScene;

    /**
     * Constructor que recibe la escena del juego a actualizar.
     *
     * @param gameScene Escena del juego que se actualizará continuamente.
     */
    public UpdateGame(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    /**
     * Ejecuta el hilo de actualización. Llama repetidamente al método updateGameScene()
     * mientras el hilo no sea interrumpido.
     */
    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            gameScene.updateGameScene();

            try {
                Thread.sleep((10));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
