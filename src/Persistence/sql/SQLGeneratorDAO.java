package Persistence.sql;

import Business.Entidades.Game;
import Persistence.GeneratorDAO;
import Business.Entidades.Generator;
import Presenstation.Controller.GameManagementController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLGeneratorDAO implements GeneratorDAO {


    @Override
    public void addGenerator(Generator generator) {
        String query = "INSERT INTO Generador(id_partida, nombre, precio, cafes_seg, multiplicador, tiempo_generacion, cost_multiplicador, increment_cost) VALUES ('" +
                generator.getIdGame() + "', '" +
                generator.getNombre() + "', '" +
                generator.getPrecio() + "', '" +
                generator.getCafeSeg() + "', '" +
                generator.getMultiplicador() + "', '" +
                generator.getTiempoGeneracion() + "', '" +
                generator.getCostMultiplicador() + "', '" +
                generator.getIncrementCost() + "')";

        SQLConnector.getInstance().insertQuery(query);
    }


    public void updateGeneratorPrice(String nombre, double precio) {
        String query = "UPDATE Generador SET precio = '" + precio + "' WHERE nombre = '" + nombre + "'";
        SQLConnector.getInstance().insertQuery(query);
    }




    @Override
    public void deleteGenerator(Generator generator) {
        String query = "DELETE FROM Generador WHERE id_generador = '" + generator.getId() + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }

    @Override
    public Generator getGenerator(int id) {
        return null;
    }

   /* @Override
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
                        rs.getDouble("tiempoGeneracion"),
                        rs.getDouble("incrementCost"),
                        rs.getInt("multiplicador"),
                        game
                );
            }
        } catch (Exception e) {
            System.err.println("Error retrieving generator: " + e.getMessage());
        }

        return null;
    }*/

    @Override

    public List<Generator> getAllGenerators() {
        List<Generator> generators = new ArrayList<>();

        String query = "SELECT * FROM Generador";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id"); // Asegúrate de que tienes esta columna si la usas
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                double cafeSeg = resultSet.getDouble("cafes_seg");
                double tiempoGeneracion = resultSet.getDouble("tiempo_generacion");
                double incrementCost = resultSet.getDouble("increment_cost");
                int costMultiplicador = resultSet.getInt("cost_multiplicador");
                int multiplicador = resultSet.getInt("multiplicador");
                int idPartida = resultSet.getInt("id_partida");

                Generator generator = new Generator(id, nombre, precio, cafeSeg, tiempoGeneracion, incrementCost, costMultiplicador, multiplicador, idPartida);
                generators.add(generator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generators;

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