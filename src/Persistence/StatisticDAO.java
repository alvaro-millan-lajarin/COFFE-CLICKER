package Persistence;
import Business.Entidades.Pair;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos relacionadas con las estadísticas del juego.
 */
public interface StatisticDAO {

    /**
     * Elimina todas las estadísticas asociadas a una partida.
     *
     * @param idPartida ID de la partida.
     */
    void deleteEstadisticasByPartidaId(int idPartida);

    /**
     * Registra en el histórico el número actual de cafés generados por una partida.
     *
     * @param id ID de la partida.
     * @param numCafes Número de cafés a registrar.
     */
    void logCafeHistorico(int id, int numCafes);

    /**
     * Recupera el histórico de cafés generados para una partida específica.
     *
     * @param idPartida ID de la partida.
     * @return Lista de pares (fecha, número de cafés) ordenada por fecha.
     */
    List<Pair<LocalDateTime, Integer>> getHistoricoCafes(int idPartida);
}
