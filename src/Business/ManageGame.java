package Business;

import Business.Entidades.Game;

import java.util.ArrayList;

public class ManageGame {
    private Game game;

    public ManageGame() {

    }

    public Game getGame() {
        return game;
    }

    public void increaseNumCafes() {

        game.increaseNumCafes();
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public void addCafetera() {
        game.addCafetera();
    }
    public ArrayList<Integer> getQuantitas() {

        return game.getQuantitats();
    }

}
