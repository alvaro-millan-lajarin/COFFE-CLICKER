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



    public void addBasicGenerators(int idPartida) {
        SQLConnector connector = SQLConnector.getInstance();

        String query1 = "INSERT INTO Generador(id_partida, nombre, precio, cafes_seg, multiplicador, tiempo_generacion, cost_multiplicador, increment_cost, numero_cafeteras) " +
                "VALUES (" + idPartida + ", 'Cafetera', 10, 0.2, 1, 1, 10, 1.07, 0)";

        String query2 = "INSERT INTO Generador(id_partida, nombre, precio, cafes_seg, multiplicador, tiempo_generacion, cost_multiplicador, increment_cost, numero_cafeteras) " +
                "VALUES (" + idPartida + ", 'CafeCheta', 150, 0.5, 1, 0.7, 150, 1.15, 0)";

        String query3 = "INSERT INTO Generador(id_partida, nombre, precio, cafes_seg, multiplicador, tiempo_generacion, cost_multiplicador, increment_cost, numero_cafeteras) " +
                "VALUES (" + idPartida + ", 'CafeGod', 2000, 30, 1, 1.3, 2000, 1.12, 0)";

        connector.insertQuery(query1);
        connector.insertQuery(query2);
        connector.insertQuery(query3);
    }

    public void updateGeneratorPrice(String nombre, double precio) {
        String query = "UPDATE Generador SET precio = '" + precio + "' WHERE nombre = '" + nombre + "'";
        SQLConnector.getInstance().insertQuery(query);
    }

    public void updateGenerator(String nombre, double precio, double cafesSeg, int multiplicador,
                                double tiempoGeneracion, int costMultiplicador, double incrementCost,
                                int numeroCafeteras, int idPartida) {

        String query = "UPDATE Generador SET " +
                "precio = '" + precio + "', " +
                "cafes_seg = '" + cafesSeg + "', " +
                "multiplicador = '" + multiplicador + "', " +
                "tiempo_generacion = '" + tiempoGeneracion + "', " +
                "cost_multiplicador = '" + costMultiplicador + "', " +
                "increment_cost = '" + incrementCost + "', " +
                "numero_cafeteras = '" + numeroCafeteras + "' " +
                "WHERE nombre = '" + nombre + "' AND id_partida = '" + idPartida + "'";

        SQLConnector.getInstance().insertQuery(query);
    }

   public int numeroGenerador(Generator generator) {
       String query = "SELECT numero_cafeteras FROM Generador WHERE nombre = '" + generator.getNombre() +
               "' AND id_partida = '" + generator.getIdGame() + "'";

       var rs = SQLConnector.getInstance().selectQuery(query);

       try {
           if (rs != null && rs.next()) {
               return rs.getInt("numero_cafeteras");
           }
       } catch (Exception e) {
           System.err.println("Error al obtener el número de cafeteras: " + e.getMessage());
       }

       return 0; // o -1 si quieres indicar que no se encontró
   }

    @Override

    public List<Generator> getAllGenerators() {
        List<Generator> generators = new ArrayList<>();

        String query = "SELECT * FROM Generador";
        ResultSet resultSet = SQLConnector.getInstance().selectQuery(query);

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id_generador"); // Asegúrate de que tienes esta columna si la usas
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

}