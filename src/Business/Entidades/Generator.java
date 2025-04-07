package Business.Entidades;

import Presenstation.Controller.GameController;

import java.time.Instant;

public class Generator extends Thread {
    private int id;
    private String nombre;
    private double precio;
    private double cafeSeg;
    private Game game;
    private double tiempoGeneracion;
    private double incrementCost;

    public Generator(int id, String nombre, double precio, double cafeSeg, double tiempoGeneracion, double incrementCost,Game game) {

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cafeSeg = cafeSeg;
        this.game = game;
        this.tiempoGeneracion = tiempoGeneracion;
        this.incrementCost = incrementCost;

    }
    @Override
    public void run() {
        double acumulador = 0.0;

        while (!Thread.currentThread().isInterrupted()) {
            acumulador += cafeSeg;

            int cafesEnteros = (int) acumulador;
            if (cafesEnteros >= 1) {
                game.addNumCafes(cafesEnteros);
                acumulador -= cafesEnteros; // dejar solo el resto decimal
            }

            try {
                Thread.sleep((long) (tiempoGeneracion * 1000)); // de segundos a ms
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
