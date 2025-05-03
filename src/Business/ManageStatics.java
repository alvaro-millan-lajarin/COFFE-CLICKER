package Business;

import Business.Entidades.Pair;
import Persistence.sql.SQLGameDAO;
import Presenstation.View.Grafica.Grafica;
import com.mysql.cj.conf.ConnectionUrlParser;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

public class ManageStatics {

    private final SQLGameDAO sqlGameDAO;

    public ManageStatics() {
        sqlGameDAO = new SQLGameDAO();
    }

    public void mostrarGraficaCafes(int idPartida) {
        // 1. Obtener datos del histórico
        List<Pair<LocalDateTime, Integer>> historico = sqlGameDAO.getHistoricoCafes(idPartida);

        if (historico.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay datos para graficar.");
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
