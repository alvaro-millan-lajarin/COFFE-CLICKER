package Presenstation.View.Refresh;

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
                Thread.sleep((long) (10)); // de segundos a ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
