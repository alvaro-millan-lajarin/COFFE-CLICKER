package Persistence.sql;

import Business.Entidades.Pair;
import Persistence.StatisticDAO;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de StatisticDAO que accede a los datos estadísticos de las partidas
 * mediante sentencias SQL sobre una base de datos MySQL.
 */
public class SQLStatisticDAO implements StatisticDAO {

    /**
     * Elimina todas las estadísticas asociadas a una partida específica.
     *
     * @param idPartida ID de la partida cuyas estadísticas se eliminarán.
     */
    @Override
    public void deleteEstadisticasByPartidaId(int idPartida) {
        String query = "DELETE FROM estadisticas WHERE id_partida = '" + idPartida + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }

    /**
     * Registra en la base de datos el número actual de cafés junto con la fecha y hora,
     * como parte del histórico de una partida.
     * Solo realiza la operación si la partida existe.
     *
     * @param id ID de la partida.
     * @param numCafes Número de cafés actuales a registrar.
     */
    public void logCafeHistorico(int id, int numCafes) {
        try {
            System.out.println("Intentando registrar café para partida ID: " + id);

            // Verificar si existe la partida
            String checkQuery = "SELECT id_partida FROM Partida WHERE id_partida = " + id;
            var rs = SQLConnector.getInstance().selectQuery(checkQuery);

            if (rs != null && rs.next()) {
                System.out.println("Partida encontrada, procediendo a insertar...");
                String query = "INSERT INTO estadisticas (id_partida, timestamp, num_cafes) VALUES (" +
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

    /**
     * Obtiene el histórico de cafés registrados para una partida, ordenado por fecha.
     *
     * @param idPartida ID de la partida.
     * @return Lista de pares (fecha, número de cafés) del histórico.
     */
    public List<Pair<LocalDateTime, Integer>> getHistoricoCafes(int idPartida) {
        List<Pair<LocalDateTime, Integer>> historico = new ArrayList<>();
        String query = "SELECT timestamp, num_cafes FROM estadisticas WHERE id_partida = " + idPartida + " ORDER BY timestamp ASC";
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