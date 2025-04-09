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
    public void addCafetera(String cafetera) {
        game.addCafetera(cafetera);
    }
    public ArrayList<Integer> getQuantitas() {

        return game.getQuantitats();
    }
    public ArrayList<String> getProduccionsUnitat() {
       return game.getProduccionsUnitat();
    }

}
