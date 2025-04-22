package Persistence.sql;

import Business.Entidades.Game;
import Business.Entidades.Pair;
import Persistence.GameDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SQLGameDAO implements GameDAO {

    public void addGame(Game game) {
        String query = "INSERT INTO Partida(id_usuario, nombre_partida, fecha_creacion, fecha_ultimo_save, num_cafes) VALUES ('" +
                game.getIdUser() + "', '" +
                game.getNombre() + "', '" +
                game.getFechaCreacion() + "', '" +
                game.getFechaModificacion() + "', '" +
                game.getNumCafes() + "')";

        SQLConnector.getInstance().insertQuery(query);
    }


    @Override
    public void updateGame(Game game) {
        String query = "UPDATE Partida SET " +
                "id_usuario = '" + game.getIdUser() + "', " +
                "nombre_partida = '" + game.getNombre() + "', " +
                "fecha_creacion = '" + game.getFechaCreacion() + "', " +
                "fecha_ultimo_save = '" + game.getFechaModificacion() + "', " +
                "num_cafes = '" + game.getNumCafes() + "', " +
                "cantidad_cafeteras = '" + listToString(game.getQuantitats()) + "', " +
                "multiplicadores = '" + listToString(game.getMultplicadors()) + "', " +
                "precios = '" + listToStringDoubles(game.getPreciosBase()) + "' " +
                "WHERE id_partida = '" + game.getId() + "'";

        SQLConnector.getInstance().updateQuery(query);
    }

    @Override
    public void deleteGame(Game game) {
        String query = "DELETE FROM Partida WHERE nombre_partida = '" + game.getNombre() + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }
    @Override
    public Game getGame(int id) {
        String query = "SELECT * FROM Partida WHERE id_partida = '" + id + "'";
        var rs = SQLConnector.getInstance().selectQuery(query);

        try {
            if (rs != null && rs.next()) {
                Game game = new Game(
                        rs.getInt("id_partida"),
                        rs.getInt("id_usuario"),
                        rs.getString("nombre_partida"),
                        rs.getTimestamp("fecha_creacion").toLocalDateTime(),
                        rs.getTimestamp("fecha_ultimo_save").toLocalDateTime(),
                        rs.getInt("num_cafes")
                );

                // Parseo seguro de las columnas que pueden venir como NULL
                String cantidadStr = rs.getString("cantidad_cafeteras");
                String multiplicadoresStr = rs.getString("multiplicadores");
                String preciosStr = rs.getString("precios");

                if (cantidadStr != null && multiplicadoresStr != null && preciosStr != null) {
                    String[] qs = cantidadStr.split(",");
                    String[] ms = multiplicadoresStr.split(",");
                    String[] ps = preciosStr.split(",");

                    ArrayList<Integer> quantitats = new ArrayList<>();
                    ArrayList<Integer> multiplicadores = new ArrayList<>();
                    ArrayList<Double> precios = new ArrayList<>();

                    for (String q : qs) quantitats.add(Integer.parseInt(q.trim()));
                    for (String m : ms) multiplicadores.add(Integer.parseInt(m.trim()));
                    for (String p : ps) precios.add(Double.parseDouble(p.trim()));

                    game.setQuantitats(quantitats);
                    game.setMultiplicadors(multiplicadores);
                    game.setPrecios(precios);
                }

                return game;
            }
        } catch (Exception e) {
            System.err.println("Error retrieving game: " + e.getMessage());
            e.printStackTrace(); // opcional para más detalles en consola
        }

        return null;
    }


    @Override
    public List<Game> getAllGames() {
        String query = "SELECT * FROM Partida";
        var rs = SQLConnector.getInstance().selectQuery(query);
        List<Game> games = new ArrayList<>();

        try {
            while (rs != null && rs.next()) {
                Game game = new Game(
                        rs.getInt("id_partida"),
                        rs.getInt("id_usuario"),
                        rs.getString("nombre_partida"),
                        rs.getTimestamp("fecha_creacion").toLocalDateTime(),
                        rs.getTimestamp("fecha_ultimo_save").toLocalDateTime(),
                        rs.getInt("num_cafes")
                );

                // Leer valores de las columnas nuevas
                String cantidadStr = rs.getString("cantidad_cafeteras");
                String multiStr = rs.getString("multiplicadores");
                String precioStr = rs.getString("precios");

                ArrayList<Integer> quantitats = new ArrayList<>();
                ArrayList<Integer> multiplicadores = new ArrayList<>();
                ArrayList<Double> precios = new ArrayList<>();


                if (cantidadStr != null) {
                    for (String q : cantidadStr.split(",")) quantitats.add(Integer.parseInt(q));
                } else {
                    quantitats.add(0); quantitats.add(0); quantitats.add(0);
                }

                if (multiStr != null) {
                    for (String m : multiStr.split(",")) multiplicadores.add(Integer.parseInt(m));
                } else {
                    multiplicadores.add(1); multiplicadores.add(1); multiplicadores.add(1);
                }

                if (precioStr != null) {
                    for (String p : precioStr.split(",")) precios.add(Double.parseDouble(p));
                } else {
                    precios.add(10.0); precios.add(150.0); precios.add(2000.0);
                }

                game.setQuantitats(quantitats);
                game.setMultiplicadors(multiplicadores);
                game.setPrecios(precios);

                games.add(game);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving games: " + e.getMessage());
        }

        return games;
    }


    private String listToString(List<Integer> list) {
        return list.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
    private String listToStringDoubles(List<Integer> list) {
        return list.stream().map(String::valueOf).collect(Collectors.joining(","));
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