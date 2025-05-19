package Business.Entidades;



import Business.ManageGame;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;


public class Game {

    private ManageGame manageGame;
    private int id;
    private int idUser;
    private String nombre;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private int numCafes;
    private boolean finished;

    private List<Generator> generadoresCafetera;
    private List<Generator> generadoresChetas;
    private List<Generator> generadoresGod;



    public Game(int id, int idUser, String nombre, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion, int numCafes, boolean finished) {
        this.id = id;
        this.idUser = idUser;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.numCafes = numCafes;
        this.finished = finished;
        this.manageGame = new ManageGame();
        this.generadoresCafetera = new ArrayList<>();
        this.generadoresChetas = new ArrayList<>();
        this.generadoresGod = new ArrayList<>();

    }
    public void startGeneratorCafetera() {

        Generator cafeteria = manageGame.getCafeteraBaseDeDatos(getId(), "Cafetera");
        cafeteria.setGame(this);
        generadoresCafetera.add(cafeteria);
        cafeteria.start();
    }
    public void startGeneratorCafeteraCheta() {
        Generator cafeteriaCheta = manageGame.getCafeteraBaseDeDatos(getId(), "CafeCheta");
        cafeteriaCheta.setGame(this);
        generadoresChetas.add(cafeteriaCheta);
        cafeteriaCheta.start();
    }
    public void startGeneratorCafeteraGod() {
        Generator cafeteriaGod = manageGame.getCafeteraBaseDeDatos(getId(), "CafeGod");
        cafeteriaGod.setGame(this);
        generadoresGod.add(cafeteriaGod);
        cafeteriaGod.start();
    }

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
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
    public synchronized void addNumCafes(Integer coffes){
        this.numCafes = this.numCafes + coffes;
    }



    public ArrayList<String> getProduccionsUnitat() {
        ArrayList<String> produccions = new ArrayList<>();

        // Cafeteras normales
        if (!generadoresCafetera.isEmpty()) {
            Generator g = generadoresCafetera.get(0);
            produccions.add(String.format("%.2f cafès / %.2f s", g.getCafeSeg(), g.getTiempoGeneracion()));
        } else {
            produccions.add("0,20 cafès / 1,00 s");
        }

        // Cafeteras chetas
        if (!generadoresChetas.isEmpty()) {
            Generator g = generadoresChetas.get(0);
            produccions.add(String.format("%.2f cafès / %.2f s", g.getCafeSeg(), g.getTiempoGeneracion()));
        } else {
            produccions.add("0,50 cafès / 0,70 s");
        }

        // Cafeteras god
        if (!generadoresGod.isEmpty()) {
            Generator g = generadoresGod.get(0);
            produccions.add(String.format("%.2f cafès / %.2f s", g.getCafeSeg(), g.getTiempoGeneracion()));
        } else {
            produccions.add("30 cafès / 1,3 s");
        }

        return produccions;
    }


    public double getCafeteriaPrecio() {
        int precio = 10;
        if(!generadoresCafetera.isEmpty()){
            return generadoresCafetera.getFirst().getPrecio();
        }
        return precio;

    }

    public double getCafeteriaChetaPrecio() {
        int precio = 150;

        if(!generadoresChetas.isEmpty()){
            return generadoresChetas.getFirst().getPrecio();
        }
        return precio;
    }

    public double getCafeteriaGodPrecio() {
        int precio = 2000;

        if(!generadoresGod.isEmpty()){
            return generadoresGod.getFirst().getPrecio();
        }
        return precio;
    }
    public void mejorarCafetera(){
        for(Generator generator : generadoresCafetera){
            generator.setMultiplicador(generator.getMultiplicador()+1);
            generator.setCostMultiplicador(generator.getCostMultiplicador()*2);

            generator.setCafeSeg(generator.getCafeSeg()*generator.getMultiplicador());
        }
    }
    public void mejorarCheta(){
        for (Generator generator: generadoresChetas){
            generator.setMultiplicador(generator.getMultiplicador()+1);
            generator.setCostMultiplicador(generator.getCostMultiplicador()*2);

            generator.setCafeSeg(generator.getCafeSeg()*generator.getMultiplicador());
        }
    }

    public void mejorarGod(){

        for(Generator generator: generadoresGod){
            generator.setMultiplicador(generator.getMultiplicador()+1);
            generator.setCostMultiplicador(generator.getCostMultiplicador()*2);

            generator.setCafeSeg(generator.getCafeSeg()*generator.getMultiplicador());

        }
    }
    public ArrayList<Integer> getCostMultplicadors() {
        ArrayList<Integer> costMultplicadors = new ArrayList<>();
        int costMultplicadorCafetera = 10;
        int costMultplicadorCheta = 150;
        int costMultplicadrGod = 2000;

        if(!getGeneradoresCafetera().isEmpty()){
            costMultplicadorCafetera = getGeneradoresCafetera().getFirst().getCostMultiplicador();
        }
        if(!getGeneradoresChetas().isEmpty()){
            costMultplicadorCheta = getGeneradoresChetas().getFirst().getCostMultiplicador();
        }

        if(!getGeneradoresGod().isEmpty()){
            costMultplicadrGod = getGeneradoresGod().getFirst().getCostMultiplicador();
        }
        costMultplicadors.add(costMultplicadorCafetera);
        costMultplicadors.add(costMultplicadorCheta);
        costMultplicadors.add(costMultplicadrGod);

        return costMultplicadors;
    }
    public ArrayList<Integer> getMultplicadors() {
        ArrayList<Integer> multplicadors = new ArrayList<>();
        int multplicadorCafetera = 1;
        int multplicadorCheta = 1;
        int multiplicadorGod = 1;

        if(!getGeneradoresCafetera().isEmpty()){
            multplicadorCafetera = getGeneradoresCafetera().getFirst().getMultiplicador();
        }
        if(!getGeneradoresChetas().isEmpty()){
            multplicadorCheta = getGeneradoresChetas().getFirst().getMultiplicador();
        }

        if(!getGeneradoresGod().isEmpty()){
            multiplicadorGod = getGeneradoresGod().getFirst().getMultiplicador();
        }
        multplicadors.add(multplicadorCafetera);
        multplicadors.add(multplicadorCheta);
        multplicadors.add(multiplicadorGod);
        return multplicadors;
    }



    public List<Generator> getGeneradoresCafetera() {
        return generadoresCafetera;
    }

    public List<Generator> getGeneradoresChetas() {
        return generadoresChetas;
    }

    public List<Generator> getGeneradoresGod() {
        return generadoresGod;
    }



    public void setGeneradoresCafetera(List<Generator> generadoresCafetera) {
        this.generadoresCafetera = generadoresCafetera;
    }

    public void setGeneradoresChetas(List<Generator> generadoresChetas) {
        this.generadoresChetas = generadoresChetas;
    }

    public void setGeneradoresGod(List<Generator> generadoresGod) {
        this.generadoresGod = generadoresGod;
    }
    public void setFinished() {
        this.finished = true;
    }
    public boolean isFinished() {
        return finished;
    }
}
