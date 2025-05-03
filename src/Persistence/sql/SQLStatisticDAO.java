package Persistence.sql;

import Persistence.StatisticDAO;
import Business.Entidades.Statistic;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SQLStatisticDAO implements StatisticDAO {

    @Override
    public void addEstadistica(Statistic estadistica) {
        String query = "INSERT INTO Estadisticas(id_partida, num_cafes, fechaSave) VALUES ('" +
                estadistica.getIdPartida() + "', '" +
                estadistica.getNumCafes() + "', '" +
                Timestamp.valueOf(estadistica.getFechaSave()) + "')";

        SQLConnector.getInstance().insertQuery(query);
    }


    @Override
    public void updateEstadistica(Statistic estadistica) {
        String query = "UPDATE Estadisticas SET " +
                "id_partida = '" + estadistica.getIdPartida() + "', " +
                "num_cafes = '" + estadistica.getNumCafes() + "', " +
                "fechaSave = '" + Timestamp.valueOf(estadistica.getFechaSave()) + "' " +
                "WHERE id_estadisticas = '" + estadistica.getId_Estadisticas() + "'";

        SQLConnector.getInstance().updateQuery(query);
    }


    @Override
    public void deleteEstadistica(Statistic estadistica) {
        String query = "DELETE FROM HistoricoCafes WHERE id_estadisticas = '" + estadistica.getId_Estadisticas() + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }
    public void deleteEstadisticasByPartidaId(int idPartida) {
        String query = "DELETE FROM HistoricoCafes WHERE id_partida = '" + idPartida + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }

    @Override
    public Statistic getEstadistica(int id) {
        String query = "SELECT * FROM Estadisticas WHERE id_estadisticas = '" + id + "'";
        var rs = SQLConnector.getInstance().selectQuery(query);

        try {
            if (rs != null && rs.next()) {
                return new Statistic(
                        rs.getInt("id_estadisticas"),
                        rs.getInt("id_partida"),
                        rs.getInt("num_cafes"),
                        rs.getTimestamp("fechaSave").toLocalDateTime()
                );
            }
        } catch (Exception e) {
            System.err.println("Error retrieving estadistica: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Statistic> getAllEstadisticas() {
        String query = "SELECT * FROM Estadisticas";
        var rs = SQLConnector.getInstance().selectQuery(query);
        List<Statistic> lista = new ArrayList<>();

        try {
            while (rs != null && rs.next()) {
                Statistic estadistica = new Statistic(
                        rs.getInt("id_estadisticas"),
                        rs.getInt("id_partida"),
                        rs.getInt("num_cafes"),
                        rs.getTimestamp("fechaSave").toLocalDateTime()
                );
                lista.add(estadistica);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving estadisticas: " + e.getMessage());
        }

        return lista;
    }
}