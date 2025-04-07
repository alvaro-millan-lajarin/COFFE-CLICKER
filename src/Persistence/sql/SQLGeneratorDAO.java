package Persistence.sql;

import Business.Entidades.Game;
import Persistence.GeneratorDAO;
import Business.Entidades.Generator;
import Presenstation.Controller.GameManagementController;

import java.util.List;

public class SQLGeneratorDAO implements GeneratorDAO {


    @Override
    public void addGenerator(Generator generator) {
        String query = "INSERT INTO Generador(nombre, precio, cafes_seg) VALUES ('" +
                generator.getNombre() + "', '" +
                generator.getPrecio() + "', '" +
                generator.getCafeSeg() + "')";

        SQLConnector.getInstance().insertQuery(query);
    }


    @Override
    public void updateGenerator(Generator generator) {
        String query = "UPDATE Generador SET " +
                "nombre = '" + generator.getNombre() + "', " +
                "precio = '" + generator.getPrecio() + "', " +
                "cafe_seg = '" + generator.getCafeSeg() + "' " +
                "WHERE id_generador = '" + generator.getId() + "'";

        SQLConnector.getInstance().updateQuery(query);
    }

    @Override
    public void deleteGenerator(Generator generator) {
        String query = "DELETE FROM Generador WHERE id_generador = '" + generator.getId() + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }

    @Override
    public Generator getGenerator(int id) {
        String query = "SELECT * FROM Generador WHERE id_generador = '" + id + "'";
        var rs = SQLConnector.getInstance().selectQuery(query);

        try {
            if (rs != null && rs.next()) {
                int idGame = rs.getInt("id_game");
                SQLGameDAO gameDAO = new SQLGameDAO();
                Game game = gameDAO.getGame(idGame);

                return new Generator(
                        rs.getInt("id_generador"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getDouble("cafes_seg"),
                        game
                );
            }
        } catch (Exception e) {
            System.err.println("Error retrieving generator: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Generator> getAllGenerators() {
        return List.of();
    }


    //Stoy - 10
    /*@Override
    public List<Generator> getAllGenerators() {
        String query = "SELECT * FROM Generador";
        var rs = SQLConnector.getInstance().selectQuery(query);
        List<Generator> generators = new java.util.ArrayList<>();

        try {
            while (rs != null && rs.next()) {
                Generator generator = new Generator(
                        rs.getInt("id_generador"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getDouble("cafes_seg"),
                        game
                );
                generators.add(generator);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving generators: " + e.getMessage());
        }

        return generators;
    }*/
}