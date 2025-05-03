package Persistence.sql;

import Persistence.StatisticDAO;
import Business.Entidades.Statistic;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SQLStatisticDAO implements StatisticDAO {

    @Override
    public void deleteEstadisticasByPartidaId(int idPartida) {
        String query = "DELETE FROM HistoricoCafes WHERE id_partida = '" + idPartida + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }


}