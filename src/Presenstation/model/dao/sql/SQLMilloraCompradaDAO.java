package Presenstation.model.dao.sql;

import Presenstation.model.dao.MilloraCompradaDAO;
import Presenstation.model.entity.MilloraComprada;

import java.util.ArrayList;
import java.util.List;

public class SQLMilloraCompradaDAO implements MilloraCompradaDAO {

    @Override
    public void addMilloraComprada(MilloraComprada millora) {
        String query = "INSERT INTO mejoracomprada (id_mejora_comprada, id_partida, id_mejora, nivel) VALUES ('" +
                millora.getIdMejoraComprada() + "', '" +
                millora.getIdPartida() + "', '" +
                millora.getIdMejora() + "', '" +
                millora.getNivel() + "')";

        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public void updateMilloraComprada(MilloraComprada millora) {
        String query = "UPDATE mejoracomprada SET " +
                "id_partida = '" + millora.getIdPartida() + "', " +
                "id_mejora = '" + millora.getIdMejora() + "', " +
                "nivel = '" + millora.getNivel() + "' " +
                "WHERE id_mejora_comprada = '" + millora.getIdMejoraComprada() + "'";

        SQLConnector.getInstance().updateQuery(query);
    }

    @Override
    public void deleteMilloraComprada(MilloraComprada millora) {
        String query = "DELETE FROM mejoracomprada WHERE id_mejora_comprada = '" + millora.getIdMejoraComprada() + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }

    @Override
    public MilloraComprada getMilloraComprada(int id) {
        String query = "SELECT * FROM mejoracomprada WHERE id_mejora_comprada = '" + id + "'";
        var rs = SQLConnector.getInstance().selectQuery(query);

        try {
            if (rs != null && rs.next()) {
                return new MilloraComprada(
                        rs.getInt("id_mejora_comprada"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_mejora"),
                        rs.getInt("nivel")
                );
            }
        } catch (Exception e) {
            System.err.println("Error retrieving millora comprada: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<MilloraComprada> getAllMilloresComprades() {
        String query = "SELECT * FROM MilloraComprada";
        var rs = SQLConnector.getInstance().selectQuery(query);
        List<MilloraComprada> millores = new ArrayList<>();

        try {
            while (rs != null && rs.next()) {
                MilloraComprada millora = new MilloraComprada(
                        rs.getInt("id_mejora_comprada"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_mejora"),
                        rs.getInt("nivel")
                );
                millores.add(millora);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving millores comprades: " + e.getMessage());
        }

        return millores;
    }
}
