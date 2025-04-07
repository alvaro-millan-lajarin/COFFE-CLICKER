package Business;

import Business.Entidades.Game;

public class ManageGame {
    private Game game;

    public ManageGame() {

    }

    public Game getGame() {
        return game;
    }

    public void increaseNumCafes() {
        game = getGame();
        game.increaseNumCafes();
    }
    public void setGame(Game game) {
        this.game = game;
    }
}
