package Business;

import Business.Entidades.Pair;
import Persistence.StatisticDAO;
import Persistence.sql.SQLGameDAO;
import Persistence.sql.SQLStatisticDAO;
import Presenstation.Messages;
import Presenstation.View.Grafica.Grafica;
import com.mysql.cj.conf.ConnectionUrlParser;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase encargada de gestionar la visualización de estadísticas del juego,
 * como el histórico de cafés generados.
 */
public class ManageStatics {

    private final StatisticDAO statisticDAO;
    private final Messages messages = new Messages();

    /**
     * Constructor de ManageStatics.
     *
     * @param statisticDAO DAO que permite acceder a los datos estadísticos desde la base de datos.
     */
    public ManageStatics(StatisticDAO statisticDAO) {
        this.statisticDAO = statisticDAO;
    }

    /**
     * Muestra una gráfica con el histórico de cafés generados por una partida.
     * Si no hay datos, muestra un mensaje informativo.
     *
     * @param idPartida ID de la partida para la que se desea ver la gráfica.
     */
    public void mostrarGraficaCafes(int idPartida) {
        List<Pair<LocalDateTime, Integer>> historico = statisticDAO.getHistoricoCafes(idPartida);

        if (historico.isEmpty()) {
            messages.noHayDatosGraficar();
            return;
        }

        Grafica grafica = new Grafica(historico);

        JFrame frame = new JFrame("Histórico de Cafés");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(grafica);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
