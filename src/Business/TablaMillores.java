package Business;

import java.util.ArrayList;

public class TablaMillores {
    private ArrayList<String> noms;
    private ArrayList<Double> costos;
    private ArrayList<String> multiplicadores;

    public TablaMillores(ArrayList<String> noms, ArrayList<Double> costos, ArrayList<String> multiplicadores) {
        this.noms = noms;
        this.costos = costos;
        this.multiplicadores = multiplicadores;
    }

    public ArrayList<String> getNoms() {
        return noms;
    }

    public ArrayList<Double> getCostos() {
        return costos;
    }

    public ArrayList<String> getMultiplicadores() {
        return multiplicadores;
    }

    public void setNoms(ArrayList<String> noms) {
        this.noms = noms;
    }

    public void setCostos(ArrayList<Double> costos) {
        this.costos = costos;
    }

    public void setMultiplicadores(ArrayList<String> multiplicadores) {
        this.multiplicadores = multiplicadores;
    }
}
