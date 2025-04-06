package Business.Entidades;

import java.util.ArrayList;

public class Tabla {
    private ArrayList<Generator> generators;
    private Game game;

    public Tabla(ArrayList<Generator> generators, Game game) {
        this.game = game;
    }
    public void addNumCafes(int numCafes) {
        game.addNumCafes(numCafes);
    }


}
