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

public class ManageStatics {

    private final StatisticDAO statisticDAO;
    private final Messages messages = new Messages();

    public ManageStatics(StatisticDAO statisticDAO) {
        this.statisticDAO = statisticDAO;
    }



    public void mostrarGraficaCafes(int idPartida) {
        // 1. Obtener datos del histórico
        List<Pair<LocalDateTime, Integer>> historico = statisticDAO.getHistoricoCafes(idPartida);

        if (historico.isEmpty()) {
            messages.noHayDatosGraficar();

            return;
        }

        // 2. Crear el panel de gráfica
        Grafica grafica = new Grafica(historico);

        // 3. Mostrarlo en un JFrame
        JFrame frame = new JFrame("Histórico de Cafés");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(grafica);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
