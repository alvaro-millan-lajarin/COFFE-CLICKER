package Business.Entidades;

import java.util.ArrayList;

public class TablaGeneradorsDisponibles {
    private ArrayList<String> noms;
    private ArrayList<Generator> generators;
    private ArrayList<Integer> quantitats;
    private ArrayList<String> produccioUnitat;
    private ArrayList<String> produccioTotal;
    private ArrayList<String> produccioGlobal;

    public TablaGeneradorsDisponibles() {
        this.noms = new ArrayList<>();
        this.quantitats = new ArrayList<>();
        this.produccioUnitat = new ArrayList<>();
        this.produccioTotal = new ArrayList<>();
        this.produccioGlobal = new ArrayList<>();
    }

    public ArrayList<String> getNoms() {
        return noms;
    }

    public ArrayList<String> getProduccioTotal() {
        return produccioTotal;
    }

    public ArrayList<String> getProduccioGlobal() {
        return produccioGlobal;
    }

    public ArrayList<String> getProduccioUnitat() {
        return produccioUnitat;
    }

    public ArrayList<Integer> getQuantitats() {
        return quantitats;
    }

    public void setNoms(ArrayList<String> noms) {
        this.noms = noms;
    }

    public void setQuantitats(ArrayList<Integer> quantitats) {
        this.quantitats = quantitats;
    }

    public void setProduccioUnitat(ArrayList<String> produccioUnitat) {
        this.produccioUnitat = produccioUnitat;
    }

    public void setProduccioTotal(ArrayList<String> produccioTotal) {
        this.produccioTotal = produccioTotal;
    }

    public void setProduccioGlobal(ArrayList<String> produccioGlobal) {
        this.produccioGlobal = produccioGlobal;
    }
}
