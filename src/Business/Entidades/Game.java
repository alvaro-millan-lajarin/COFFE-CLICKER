package Business.Entidades;

import java.time.LocalDateTime;

public class Game {
    private int id;
    private int idUser;
    private String nombre;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private int numCafes;
    private Tabla tablaDisponible;
    private Tabla tablaMillores;
    private Tabla tablaGeneradors;


    public Game(int id, int idUser, String nombre, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion, int numCafes) {
        this.id = id;
        this.idUser = idUser;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.numCafes = numCafes;

        tablaDisponible = new Tabla(this);
        tablaGeneradors = new Tabla(this);
        tablaMillores = new Tabla(this);


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
}
