package Business.Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;

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


    public Game(int id, int idUser, String nombre, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion, int numCafes) {
        this.id = id;
        this.idUser = idUser;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.numCafes = numCafes;


        cafeteria = new Generator(1, "cafeteria",10, 0, 0,1.07,0,this);
        cafeteriaCheta = new Generator(2, "cafeteriaCheta",150, 0, 0,1.15,0,this);
        cafeteriaGod = new Generator(3, "cafeteriaGod",2000, 0, 0,1.12,0,this);


        cafeteria.start();
        cafeteriaCheta.start();
        cafeteriaGod.start();

        quantitats = new ArrayList<>();
        quantitats.add(0);
        quantitats.add(0);
        quantitats.add(0);



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
    public void addCafetera() {
        quantitats.set(0, quantitats.get(0) + 1);
    }

    public ArrayList<Integer> getQuantitats(){
        return quantitats;
    }

}
