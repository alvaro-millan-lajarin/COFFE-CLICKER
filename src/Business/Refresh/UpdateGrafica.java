package Business.Refresh;

import Business.Entidades.Pair;
import Business.ManageGame;
import Presenstation.View.Grafica.Grafica;

import java.time.LocalDateTime;

/**
 * Hilo encargado de actualizar la gráfica de producción de café cada minuto.
 * Registra el número de cafés actuales y actualiza la visualización histórica.
 */
public class UpdateGrafica extends Thread {
    private ManageGame manageGame;
    private Grafica grafica;

    /**
     * Constructor de la clase UpdateGrafica.
     *
     * @param manageGame Objeto que gestiona el estado de la partida.
     * @param grafica Componente gráfico donde se representa el histórico de cafés.
     */
    public UpdateGrafica(ManageGame manageGame, Grafica grafica) {
        this.manageGame = manageGame;
        this.grafica = grafica;

    }

    /**
     * Ejecuta el hilo que actualiza la gráfica cada 60 segundos.
     * Registra el número de cafés actuales y añade el dato al histórico.
     */
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
}
