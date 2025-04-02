package Business.entity;

public class GeneradorsComprats {
    private int idGeneradorComprado;
    private int idPartida;
    private int idGenerador;
    private int cantidad;
    private int nivel;

    public GeneradorsComprats(int idGeneradorComprado, int idPartida, int idGenerador, int cantidad, int nivel) {
        this.idGeneradorComprado = idGeneradorComprado;
        this.idPartida = idPartida;
        this.idGenerador = idGenerador;
        this.cantidad = cantidad;
        this.nivel = nivel;

    }

    // Getters
    public int getIdGeneradorComprado() {
        return idGeneradorComprado;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public int getIdGenerador() {
        return idGenerador;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getNivel() {
        return nivel;
    }

    // Setters
    public void setIdGeneradorComprado(int idGeneradorComprado) {
        this.idGeneradorComprado = idGeneradorComprado;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public void setIdGenerador(int idGenerador) {
        this.idGenerador = idGenerador;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
