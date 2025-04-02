package Presenstation.model.dao.sql;

import Presenstation.model.dao.GeneradorsCompratsDAO;
import Presenstation.model.entity.GeneradorsComprats;



import java.util.ArrayList;
import java.util.List;

public class SQLGeneradorsCompratsDAO implements GeneradorsCompratsDAO {

    @Override
    public void addGeneradorComprat(GeneradorsComprats generador) {
        String query = "INSERT INTO GeneradorsComprats(id_generador_comprado, id_partida, id_generador, cantidad, nivel) VALUES ('" +
                generador.getIdGeneradorComprado() + "', '" +
                generador.getIdPartida() + "', '" +
                generador.getIdGenerador() + "', '" +
                generador.getCantidad() + "', '" +
                generador.getNivel() + "')";

        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public void updateGeneradorComprat(GeneradorsComprats generador) {
        String query = "UPDATE GeneradorsComprats SET " +
                "id_partida = '" + generador.getIdPartida() + "', " +
                "id_generador = '" + generador.getIdGenerador() + "', " +
                "cantidad = '" + generador.getCantidad() + "', " +
                "nivel = '" + generador.getNivel() + "' " +
                "WHERE id_generador_comprado = '" + generador.getIdGeneradorComprado() + "'";

        SQLConnector.getInstance().updateQuery(query);
    }

    @Override
    public void deleteGeneradorComprat(GeneradorsComprats generador) {
        String query = "DELETE FROM GeneradorsComprats WHERE id_generador_comprado = '" + generador.getIdGeneradorComprado() + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }

    @Override
    public GeneradorsComprats getGeneradorComprat(int id) {
        String query = "SELECT * FROM GeneradorsComprats WHERE id_generador_comprado = '" + id + "'";
        var rs = SQLConnector.getInstance().selectQuery(query);

        try {
            if (rs != null && rs.next()) {
                return new GeneradorsComprats(
                        rs.getInt("id_generador_comprado"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_generador"),
                        rs.getInt("cantidad"),
                        rs.getInt("nivel")
                );
            }
        } catch (Exception e) {
            System.err.println("Error retrieving generador comprat: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<GeneradorsComprats> getAllGeneradorsComprats() {
        String query = "SELECT * FROM GeneradorsComprats";
        var rs = SQLConnector.getInstance().selectQuery(query);
        List<GeneradorsComprats> lista = new ArrayList<>();

        try {
            while (rs != null && rs.next()) {
                GeneradorsComprats generador = new GeneradorsComprats(
                        rs.getInt("id_generador_comprado"),
                        rs.getInt("id_partida"),
                        rs.getInt("id_generador"),
                        rs.getInt("cantidad"),
                        rs.getInt("nivel")
                );
                lista.add(generador);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving generadors comprats: " + e.getMessage());
        }

        return lista;
    }
}
