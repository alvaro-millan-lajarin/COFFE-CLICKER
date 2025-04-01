package Business;

import java.util.ArrayList;

public class TablaMilloresDisponibles {
    private ArrayList<String> noms;
    private ArrayList<Integer> quantitats;
    private ArrayList<String> produccioUnitat;
    private ArrayList<String> produccioTotal;
    private ArrayList<Double> produccioGlobal;

    public TablaMilloresDisponibles(ArrayList<String> noms, ArrayList<Integer> quantitats, ArrayList<String> produccioUnitat, ArrayList<String> produccioTotal, ArrayList<Double> produccioGlobal) {
        this.noms = noms;
        this.quantitats = quantitats;
        this.produccioUnitat = produccioUnitat;
        this.produccioTotal = produccioTotal;
        this.produccioGlobal = produccioGlobal;
    }

    public ArrayList<String> getNoms() {
        return noms;
    }

    public ArrayList<String> getProduccioTotal() {
        return produccioTotal;
    }

    public ArrayList<Double> getProduccioGlobal() {
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

    public void setProduccioGlobal(ArrayList<Double> produccioGlobal) {
        this.produccioGlobal = produccioGlobal;
    }
}
