package Business.Entidades;

public class MilloraComprada {
    private int idMejoraComprada;
    private int idPartida;
    private int idMejora;
    private int nivel;

    public MilloraComprada(int idMejoraComprada, int idPartida, int idMejora, int nivel) {
        this.idMejoraComprada = idMejoraComprada;
        this.idPartida = idPartida;
        this.idMejora = idMejora;
        this.nivel = nivel;
    }

    // Getters
    public int getIdMejoraComprada() {
        return idMejoraComprada;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public int getIdMejora() {
        return idMejora;
    }

    public int getNivel() {
        return nivel;
    }

    // Setters
    public void setIdMejoraComprada(int idMejoraComprada) {
        this.idMejoraComprada = idMejoraComprada;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public void setIdMejora(int idMejora) {
        this.idMejora = idMejora;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
