package Persistence.sql;

import Persistence.GameDAO;
import Business.entity.Game;

import java.util.List;

public class SQLGameDAO implements GameDAO {

    @Override
    public void addGame(Game game) {
        String query = "INSERT INTO Partida(id_partida, id_usuario, nombre_partida, fecha_creacion, fecha_ultimo_save, num_cafes) VALUES ('" +
                game.getId() + "', '" +
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
                "num_cafes = '" + game.getNumCafes() + "' " +
                "WHERE id_partida = '" + game.getId() + "'";

        SQLConnector.getInstance().updateQuery(query);
    }

    @Override
    public void deleteGame(Game game) {
        String query = "DELETE FROM Partida WHERE id_partida = '" + game.getId() + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }

    @Override
    public Game getGame(int id) {
        String query = "SELECT * FROM Partida WHERE id_partida = '" + id + "'";
        var rs = SQLConnector.getInstance().selectQuery(query);

        try {
            if (rs != null && rs.next()) {
                return new Game(
                        rs.getInt("id_partida"),
                        rs.getInt("id_usuario"),
                        rs.getString("nombre_partida"),
                        rs.getTimestamp("fecha_creacion").toLocalDateTime(),
                        rs.getTimestamp("fecha_ultimo_save").toLocalDateTime(),
                        rs.getInt("num_cafes")
                );
            }
        } catch (Exception e) {
            System.err.println("Error retrieving game: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Game> getAllGames() {
        String query = "SELECT * FROM Partida";
        var rs = SQLConnector.getInstance().selectQuery(query);
        List<Game> games = new java.util.ArrayList<>();

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
                games.add(game);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving games: " + e.getMessage());
        }

        return games;
    }
}
