package Persistence;
import Business.Entidades.Game;
import Business.Entidades.Generator;

import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para las partidas del juego.
 */
public interface GameDAO {

    /**
     * Inserta una nueva partida en la base de datos.
     *
     * @param game Partida a insertar.
     */
    void addGame(Game game);

    /**
     * Actualiza los datos de una partida existente.
     *
     * @param game Partida con los datos actualizados.
     */
    void updateGame(Game game);

    /**
     * Elimina una partida de la base de datos.
     *
     * @param game Partida a eliminar.
     */
    void deleteGame(Game game);

    /**
     * Recupera todas las partidas almacenadas en la base de datos.
     *
     * @return Lista de partidas.
     */
    List<Game> getAllGames();

    /**
     * Marca una partida como finalizada en la base de datos.
     *
     * @param game Partida que se desea marcar como finalizada.
     */
    void finishTrue(Game game);

}
