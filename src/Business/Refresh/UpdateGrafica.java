package Business.Refresh;

import Business.Entidades.Pair;
import Business.ManageGameGenerators;
import Business.ManageStatics;
import Presenstation.View.Grafica.Grafica;

import java.time.LocalDateTime;

/**
 * Hilo encargado de actualizar la gráfica de producción de café cada minuto.
 * Registra el número de cafés actuales y actualiza la visualización histórica.
 */
public class UpdateGrafica extends Thread {
    private final ManageGameGenerators manageGameGenerators;
    private final ManageStatics manageStatics;
    private final Grafica grafica;

    /**
     * Constructor de la clase UpdateGrafica.
     *
     * @param manageGameGenerators Objeto que gestiona el estado de la partida.
     * @param grafica Componente gráfico donde se representa el histórico de cafés.
     */
    public UpdateGrafica(ManageGameGenerators manageGameGenerators, Grafica grafica, ManageStatics manageStatics) {
        this.manageGameGenerators = manageGameGenerators;
        this.grafica = grafica;
        this.manageStatics = manageStatics;

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
            int cafesActuales = manageGameGenerators.getGame().getNumCafes();
            LocalDateTime ahora = LocalDateTime.now();
            manageStatics.logCafeHistorico();
            grafica.getHistorico().add(new Pair<>(ahora, cafesActuales));
            grafica.repaint();
        }
    }
}
