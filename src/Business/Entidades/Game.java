package Business.Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private int id;
    private int idUser;
    private String nombre;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private int numCafes;

    private Generator cafeteria;
    private Generator cafeteriaCheta;
    private Generator cafeteriaGod;



    private ArrayList<Integer> quantitats;//0->cafeteria, 1->cafeteraCheta, 2->cafeteraGod
    private ArrayList<String> produccionsUnitat;


    public Game(int id, int idUser, String nombre, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion, int numCafes) {
        this.id = id;
        this.idUser = idUser;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.numCafes = numCafes;


        cafeteria = new Generator(1, "cafeteria",10,0.2 , 1,1.07,0,this);
        cafeteriaCheta = new Generator(2, "cafeteriaCheta",150, 0.5, 0.7,1.15,0,this);
        cafeteriaGod = new Generator(3, "cafeteriaGod",2000, 30.0, 1.3,1.12,0,this);


        cafeteria.start();
        cafeteriaCheta.start();
        cafeteriaGod.start();

        quantitats = new ArrayList<>();
        quantitats.add(0);
        quantitats.add(0);
        quantitats.add(0);

        produccionsUnitat = new ArrayList<>();




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
    public ArrayList<String> getProduccionsUnitat(){

        produccionsUnitat.add(cafeteria.getCafeSeg()+" cafès / "+cafeteria.getTiempoGeneracion()+" s");
        produccionsUnitat.add(cafeteriaCheta.getCafeSeg()+" cafès / "+cafeteriaCheta.getTiempoGeneracion()+" s");
        produccionsUnitat.add(cafeteriaGod.getCafeSeg()+" cafès / "+cafeteriaGod.getTiempoGeneracion()+" s");



        return produccionsUnitat;

    }
}
