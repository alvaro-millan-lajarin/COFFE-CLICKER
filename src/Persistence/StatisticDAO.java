package Persistence;



import Business.Entidades.Pair;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticDAO {

    void deleteEstadisticasByPartidaId(int idPartida);
    void logCafeHistorico(int id, int numCafes);
    List<Pair<LocalDateTime, Integer>> getHistoricoCafes(int idPartida);

}
