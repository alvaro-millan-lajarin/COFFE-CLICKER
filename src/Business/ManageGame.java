package Business;

import Business.Entidades.Game;

public class ManageGame {
    private Game game;

    public ManageGame() {
        this.game = new Game(); 
    }

    public Game getGame() {
        return game;
    }

    public void increaseNumCafes() {
        game = getGame();
        game.increaseNumCafes();
    }

}
