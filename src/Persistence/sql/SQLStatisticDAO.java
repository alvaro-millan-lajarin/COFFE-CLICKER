package Persistence.sql;

import Business.Entidades.Pair;
import Persistence.StatisticDAO;
import Business.Entidades.Statistic;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SQLStatisticDAO implements StatisticDAO {

    @Override
    public void deleteEstadisticasByPartidaId(int idPartida) {
        String query = "DELETE FROM HistoricoCafes WHERE id_partida = '" + idPartida + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }

    public void logCafeHistorico(int id, int numCafes) {
        try {
            System.out.println("Intentando registrar café para partida ID: " + id);

            // Verificar si existe la partida
            String checkQuery = "SELECT id_partida FROM Partida WHERE id_partida = " + id;
            var rs = SQLConnector.getInstance().selectQuery(checkQuery);

            if (rs != null && rs.next()) {
                System.out.println("Partida encontrada, procediendo a insertar...");
                String query = "INSERT INTO HistoricoCafes (id_partida, timestamp, num_cafes) VALUES (" +
                        id + ", CURRENT_TIMESTAMP, " + numCafes + ")";
                SQLConnector.getInstance().insertQuery(query);
            } else {
                System.err.println("ERROR: No existe partida con ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Error en logCafeHistorico: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Pair<LocalDateTime, Integer>> getHistoricoCafes(int idPartida) {
        List<Pair<LocalDateTime, Integer>> historico = new ArrayList<>();
        String query = "SELECT timestamp, num_cafes FROM HistoricoCafes WHERE id_partida = " + idPartida + " ORDER BY timestamp ASC";
        var rs = SQLConnector.getInstance().selectQuery(query);

        try {
            while (rs != null && rs.next()) {
                historico.add(new Pair<>(
                        rs.getTimestamp("timestamp").toLocalDateTime(),
                        rs.getInt("num_cafes")
                ));
            }
        } catch (Exception e) {
            System.err.println("Error leyendo histórico de cafés: " + e.getMessage());
        }
        return historico;
    }


}