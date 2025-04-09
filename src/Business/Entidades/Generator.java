package Business.Entidades;

import Presenstation.Controller.GameController;

import java.time.Instant;
import java.util.HashMap;

public class Generator extends Thread {
    private int id;
    private String nombre;
    private double precio;
    private Double cafeSeg;//cantidad_cafe/tiempo
    private Game game;
    private double tiempoGeneracion;
    private double incrementCost;
    private Integer multiplicador;


    public Generator(int id, String nombre, double precio, Double cafeSeg, double tiempoGeneracion, double incrementCost,Integer mutiplicador,Game game) {

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cafeSeg = cafeSeg;
        this.game = game;
        this.tiempoGeneracion = tiempoGeneracion;
        this.incrementCost = incrementCost;
        this.multiplicador = mutiplicador;

    }
    @Override
    public void run() {
        double acumulador = 0.0;

        while (!Thread.currentThread().isInterrupted()) {
            acumulador += cafeSeg;

            int cafesEnteros = (int) acumulador;
            if (cafesEnteros >= 1) {
                game.addNumCafes(cafesEnteros);
                acumulador -= cafesEnteros;
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

    public Double getCafeSeg() {
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

    public void setCafeSeg(Double cafeSeg) {
        this.cafeSeg = cafeSeg;
    }

    public double getIncrementCost() {
        return incrementCost;
    }

    public double getTiempoGeneracion() {
        return tiempoGeneracion;
    }
}
