package Business.Entidades;

import java.util.ArrayList;

public class TablaGeneradors {
    private ArrayList<String> noms;
    private ArrayList<Double> costos;
    private ArrayList<String> produccions;

    public TablaGeneradors(ArrayList<String> noms, ArrayList<Double> costos, ArrayList<String> produccions) {
        this.noms = noms;
        this.costos = costos;
        this.produccions = produccions;
    }

    public ArrayList<String> getNoms() {
        return noms;
    }

    public ArrayList<Double> getCostos() {
        return costos;
    }

    public ArrayList<String> getProduccions() {
        return produccions;
    }

    public void setNoms(ArrayList<String> noms) {
        this.noms = noms;
    }

    public void setCostos(ArrayList<Double> costos) {
        this.costos = costos;
    }

    public void setProduccions(ArrayList<String> produccions) {
        this.produccions = produccions;
    }
}
