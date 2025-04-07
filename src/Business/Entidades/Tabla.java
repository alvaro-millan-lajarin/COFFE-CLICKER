package Business.Entidades;

import java.util.ArrayList;

public class Tabla {
    private Generator cafeteria;
    private Generator cafeteriaCheta;
    private Generator cafeteriaGod;
    private Game game;

    public Tabla( Game game) {
        this.game = game;
        cafeteria = new Generator();
        cafeteriaCheta = new Generator();
        cafeteriaGod = new Generator();

        for (int i = 0; i < 3; i++) {
         cafeteria.start();
         cafeteriaCheta.start();
         cafeteriaGod.start();
        }

    }
    public void addNumCafes(int numCafes) {
        game.addNumCafes(numCafes);
    }


}
