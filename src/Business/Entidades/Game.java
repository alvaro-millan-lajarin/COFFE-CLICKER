package Business.Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {
    private int id;
    private int idUser;
    private String nombre;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private int numCafes;

    private List<Generator> generadoresCafetera = new ArrayList<>();

    private ArrayList<Integer> quantitats;//0->cafeteria, 1->cafeteraCheta, 2->cafeteraGod
    private ArrayList<String> produccionsUnitat;


    //VARIABLE GENERADORES AL PRINCIPIO
    private double cafeteriaCafeSeg = 0.2;
    private double cafeteriaTiempoGeneracion = 1;
    private double cafeteriaChetaCafeSeg = 0.5;
    private double cafeteriaChetaTiempoGeneracion = 0.7;
    private double cafeteriaGodCafeSeg = 30;
    private double cafeteriaGodTiempoGeneracion = 1.3;

    // Nuevas variables para el precio y multiplicador
    private double cafeteriaPrecio = 10; // Precio de la cafetería básica
    private Integer cafeteriaMultiplicador = 0; // Multiplicador de la cafetería básica

    private double cafeteriaChetaPrecio = 150; // Precio de la cafetería cheta
    private Integer cafeteriaChetaMultiplicador = 0; // Multiplicador de la cafetería cheta

    private double cafeteriaGodPrecio = 2000; // Precio de la cafetería God
    private Integer cafeteriaGodMultiplicador = 0;




    public Game(int id, int idUser, String nombre, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion, int numCafes) {
        this.id = id;
        this.idUser = idUser;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.numCafes = numCafes;


        quantitats = new ArrayList<>();
        quantitats.add(0);
        quantitats.add(0);
        quantitats.add(0);

        produccionsUnitat = new ArrayList<>();

    }
    public void startGeneratorCafetera() {

        Generator cafeteria = new Generator(1, "cafeteria",cafeteriaPrecio,cafeteriaCafeSeg , cafeteriaTiempoGeneracion,1.07,cafeteriaMultiplicador,this);
        generadoresCafetera.add(cafeteria);
        cafeteria.start();
    }
    public void startGeneratorCafeteraCheta() {
        Generator cafeteriaCheta = new Generator(2, "cafeteriaCheta",cafeteriaChetaPrecio, cafeteriaChetaCafeSeg, cafeteriaChetaTiempoGeneracion,cafeteriaChetaMultiplicador,0,this);
        generadoresCafetera.add(cafeteriaCheta);
        cafeteriaCheta.start();
    }
    public void startGeneratorCafeteraGod() {
        Generator cafeteriaGod = new Generator(3, "cafeteriaGod",cafeteriaGodPrecio, cafeteriaGodCafeSeg, cafeteriaGodTiempoGeneracion,cafeteriaGodMultiplicador,0,this);
        generadoresCafetera.add(cafeteriaGod);
        cafeteriaGod.start();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public int getNumCafes() {
        return numCafes;
    }

    public void setNumCafes(int numCafes) {
        this.numCafes = numCafes;
    }

    public void increaseNumCafes(){
        this.numCafes++;
    }
    public void addNumCafes(Integer coffes){
        this.numCafes = this.numCafes + coffes;
    }
    public void addCafetera(String cafetera) {
        if(cafetera.equals("Cafetera") ){
            quantitats.set(0, quantitats.get(0) + 1);
        } else if (cafetera.equals("CafeCheta") ) {
            quantitats.set(1, quantitats.get(1) + 1);
        } else if (cafetera.equals("CafeGod")) {
            quantitats.set(2, quantitats.get(2) + 1);
        }

    }

    public ArrayList<Integer> getQuantitats(){
        return quantitats;
    }
    public ArrayList<String> getProduccionsUnitat() {
        ArrayList<String> produccions = new ArrayList<>();

        Generator cafetera = null;
        Generator cheta = null;
        Generator god = null;

        int countCafetera = 0;
        int countCheta = 0;
        int countGod = 0;

        for (Generator g : generadoresCafetera) {
            switch (g.getNombre()) {
                case "cafeteria":
                    countCafetera++;
                    if (cafetera == null) cafetera = g;
                    break;
                case "cafeteriaCheta":
                    countCheta++;
                    if (cheta == null) cheta = g;
                    break;
                case "cafeteriaGod":
                    countGod++;
                    if (god == null) god = g;
                    break;
            }
        }

        if (cafetera != null)
            produccions.add(cafetera.getCafeSeg()+" cafès / "+cafetera.getTiempoGeneracion()+" s");
        else
            produccions.add("0 cafès / 0 s");

        if (cheta != null)
            produccions.add(cheta.getCafeSeg()+" cafès / "+cheta.getTiempoGeneracion()+" s");
        else
            produccions.add("0 cafès / 0 s");

        if (god != null)
            produccions.add(god.getCafeSeg() + " cafès / " + god.getTiempoGeneracion() + " s");
        else
            produccions.add("0 cafès / 0 s");

        return produccions;
    }

    public double getCafeteriaPrecio() {
        return cafeteriaPrecio;
    }

    public double getCafeteriaChetaPrecio() {
        return cafeteriaChetaPrecio;
    }

    public double getCafeteriaGodPrecio() {
        return cafeteriaGodPrecio;
    }
}
