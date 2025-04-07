package Business;

import Business.Entidades.Game;
import Business.Entidades.Tabla;

public class ManageGame {
    private Game game;

    public ManageGame(Game game) {
        this.game = game;
    }

    public void increaseNumCafes() {
        game.increaseNumCafes();
    }
    public Game getGame(){
        return game;

    }
}
