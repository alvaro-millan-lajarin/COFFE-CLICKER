package Business.entity;

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

    // Getters
    public int getId_Estadisticas() {
        return id_Estadisticas;
    }

    public int getIdPartida() {
        return id_Partida;
    }

    public int getNumCafes() {
        return numCafes;
    }

    public LocalDateTime getFechaSave() {
        return fechaSave;
    }

    // Setters
    public void setId_Estadisticas(int id_Estadisticas) {
        this.id_Estadisticas = id_Estadisticas;
    }

    public void setIdPartida(int idPartida) {
        this.id_Partida = idPartida;
    }

    public void setNumCafes(int numCafes) {
        this.numCafes = numCafes;
    }

    public void setFechaSave(LocalDateTime fechaSave) {
        this.fechaSave = fechaSave;
    }
}
