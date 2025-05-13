package Business.Entidades;

import java.time.LocalDateTime;

public class Statistic {
    private int id_Estadisticas;
    private int id_Partida;
    private int numCafes;
    private LocalDateTime fechaSave;

    public Statistic(int idEstadisticas, int idPartida, int numCafes, LocalDateTime fechaSave) {
        this.id_Estadisticas = idEstadisticas;
        this.id_Partida = idPartida;
        this.numCafes = numCafes;
        this.fechaSave = fechaSave;
    }



}
