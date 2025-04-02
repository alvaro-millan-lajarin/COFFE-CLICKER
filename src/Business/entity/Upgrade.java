package Business.entity;

public class Upgrade {
    private int id_Mejora;
    private int id_Generador;
    private String nombre;
    private double precio;
    private double incremento;

    public Upgrade(int idMejora, int idGenerador, String nombre, double precio, double incremento) {
        this.id_Mejora = idMejora;
        this.id_Generador = idGenerador;
        this.nombre = nombre;
        this.precio = precio;
        this.incremento = incremento;
    }


    public int getIdMejora() {
        return id_Mejora;
    }

    public int getIdGenerador() {
        return id_Generador;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public double getIncremento() {
        return incremento;
    }

    public void setIdMejora(int idMejora) {
        this.id_Mejora = idMejora;
    }

    public void setIdGenerador(int idGenerador) {
        this.id_Generador = idGenerador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setIncremento(double incremento) {
        this.incremento = incremento;
    }
}
