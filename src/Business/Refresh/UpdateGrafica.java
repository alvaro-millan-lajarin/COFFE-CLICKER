package Business.Refresh;

import Business.Entidades.Pair;
import Business.ManageGame;
import Presenstation.View.Grafica.Grafica;

import java.time.LocalDateTime;

public class UpdateGrafica extends Thread {
    private ManageGame manageGame;
    private Grafica grafica;

    public UpdateGrafica(ManageGame manageGame, Grafica grafica) {
        this.manageGame = manageGame;
        this.grafica = grafica;

    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {


            try {
                Thread.sleep( (60 * 1000)); // 1 min
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            int cafesActuales = manageGame.getGame().getNumCafes();
            LocalDateTime ahora = LocalDateTime.now();
            manageGame.logCafeHistorico(manageGame.getGame().getId(), cafesActuales);
            grafica.getHistorico().add(new Pair<>(ahora, cafesActuales));
            grafica.repaint();
        }
    }
    public void setGrafica(Grafica grafica) {
        this.grafica = grafica;
    }
}
