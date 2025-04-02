package Presenstation.model.entity;

public class Generator {
    private int id;
    private String nombre;
    private double precio;
    private double cafeSeg;

    public Generator(int id, String nombre, double precio, double cafeSeg) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cafeSeg = cafeSeg;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public double getCafeSeg() {
        return cafeSeg;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCafeSeg(double cafeSeg) {
        this.cafeSeg = cafeSeg;
    }
}
