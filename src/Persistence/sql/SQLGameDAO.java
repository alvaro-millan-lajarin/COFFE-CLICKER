package Persistence.sql;

import Business.Entidades.Game;
import Business.Entidades.Pair;
import Persistence.GameDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de GameDAO que accede a los datos de partidas usando SQL y MySQL.
 */
public class SQLGameDAO implements GameDAO {

    /**
     * Inserta una nueva partida en la base de datos.
     *
     * @param game Partida a insertar.
     */
    public void addGame(Game game) {
        String query = "INSERT INTO Partida(id_usuario, nombre_partida, fecha_creacion, fecha_ultimo_save, num_cafes) VALUES ('" +
                game.getIdUser() + "', '" +
                game.getNombre() + "', '" +
                game.getFechaCreacion() + "', '" +
                game.getFechaModificacion() + "', '" +
                game.getNumCafes() + "')";

        SQLConnector.getInstance().insertQuery(query);
    }

    /**
     * Actualiza los datos de una partida existente en la base de datos.
     *
     * @param game Partida con los nuevos datos.
     */
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

    /**
     * Elimina una partida de la base de datos según su nombre.
     *
     * @param game Partida a eliminar.
     */
    @Override
    public void deleteGame(Game game) {
        String query = "DELETE FROM Partida WHERE nombre_partida = '" + game.getNombre() + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }

    /**
     * Recupera todas las partidas almacenadas en la base de datos.
     *
     * @return Lista de partidas encontradas.
     */
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
                        rs.getInt("num_cafes"),
                        rs.getBoolean("finish")
                );
                games.add(game);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving games: " + e.getMessage());
        }

        return games;
    }

    /**
     * Marca como finalizada una partida en la base de datos.
     *
     * @param game Partida a marcar como finalizada.
     */
    public void finishTrue(Game game) {
        String query = "UPDATE Partida SET finish = TRUE WHERE id_partida = '" + game.getId() + "'";
        SQLConnector.getInstance().updateQuery(query);
    }

}