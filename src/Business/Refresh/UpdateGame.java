package Business.Refresh;

import Presenstation.View.Scenes.GameScene;

public class UpdateGame extends Thread {
    GameScene gameScene;
    public UpdateGame(GameScene gameScene) {
        this.gameScene = gameScene;
    }

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
