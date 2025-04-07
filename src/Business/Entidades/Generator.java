package Business.Entidades;

import Presenstation.Controller.GameController;

import java.time.Instant;

public class Generator extends Thread {
    private int id;
    private String nombre;
    private double precio;
    private double cafeSeg;
    private Game game;

    public Generator(int id, String nombre, double precio, double cafeSeg, Game game) {

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cafeSeg = cafeSeg;
        this.game = game;

    }
    public void run() {
        for (int i = 0; i < 1000; i++) {
            game.addNumCafes(22);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    // Getters
    public long getId() {

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
