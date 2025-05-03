package Business.Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class Game {
    private int id;
    private int idUser;
    private String nombre;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private int numCafes;


    private List<Generator> generadoresCafetera = new ArrayList<>();
    private List<Generator> generadoresChetas = new ArrayList<>();
    private List<Generator> generadoresGod = new ArrayList<>();

    //VARIABLE GENERADORES AL PRINCIPIO
    private double cafeteriaCafeSeg = 0.2;
    private double cafeteriaTiempoGeneracion = 1;
    private double cafeteriaChetaCafeSeg = 0.5;
    private double cafeteriaChetaTiempoGeneracion = 0.7;
    private double cafeteriaGodCafeSeg = 30;
    private double cafeteriaGodTiempoGeneracion = 1.3;

    // Nuevas variables para el precio y multiplicador
    private double cafeteriaPrecio = 10; // Precio de la cafetería básica
    private Integer cafeteriaMultiplicador = 1; // Multiplicador de la cafetería básica
    private Integer cafeteriaCostMultiplicador = 10;

    private double cafeteriaChetaPrecio = 150; // Precio de la cafetería cheta
    private Integer cafeteriaChetaMultiplicador = 1; // Multiplicador de la cafetería cheta
    private Integer chetaCostMultiplicador = 150;

    private double cafeteriaGodPrecio = 2000; // Precio de la cafetería God
    private Integer cafeteriaGodMultiplicador = 1;
    private Integer GodCostMultiplicador = 2000;

    private java.util.function.Consumer<Integer> onCafeChanged;


    public Game(int id, int idUser, String nombre, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion, int numCafes) {
        this.id = id;
        this.idUser = idUser;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.numCafes = numCafes;


    }
    public void startGeneratorCafetera() {
        if(!generadoresCafetera.isEmpty()){
            cafeteriaPrecio = generadoresCafetera.getFirst().getPrecio();
            cafeteriaCafeSeg = generadoresCafetera.getFirst().getCafeSeg();
            cafeteriaTiempoGeneracion = generadoresCafetera.getFirst().getTiempoGeneracion();
            cafeteriaCostMultiplicador = generadoresCafetera.getFirst().getCostMultiplicador();
            cafeteriaMultiplicador = generadoresCafetera.getFirst().getMultiplicador();
        }

        Generator cafeteria = new Generator(1, "cafeteria",cafeteriaPrecio,cafeteriaCafeSeg , cafeteriaTiempoGeneracion,1.07,cafeteriaCostMultiplicador, cafeteriaMultiplicador,this.getId());
        cafeteria.setGame(this);
        generadoresCafetera.add(cafeteria);
        cafeteria.start();
    }
    public void startGeneratorCafeteraCheta() {
        if(!generadoresChetas.isEmpty()){
            cafeteriaChetaPrecio = generadoresChetas.getFirst().getPrecio();
            cafeteriaChetaCafeSeg = generadoresChetas.getFirst().getCafeSeg();
            cafeteriaChetaTiempoGeneracion = generadoresChetas.getFirst().getTiempoGeneracion();
            chetaCostMultiplicador = generadoresChetas.getFirst().getCostMultiplicador();
            cafeteriaChetaMultiplicador = generadoresChetas.getFirst().getMultiplicador();
        }
        Generator cafeteriaCheta = new Generator(2, "cafeteriaCheta",cafeteriaChetaPrecio, cafeteriaChetaCafeSeg, cafeteriaChetaTiempoGeneracion,1.15,chetaCostMultiplicador, cafeteriaChetaMultiplicador,this.getId());
        cafeteriaCheta.setGame(this);
        generadoresChetas.add(cafeteriaCheta);
        cafeteriaCheta.start();
    }
    public void startGeneratorCafeteraGod() {
        if(!generadoresGod.isEmpty()){
            cafeteriaGodPrecio = generadoresGod.getFirst().getPrecio();
            cafeteriaGodCafeSeg = generadoresGod.getFirst().getCafeSeg();
            cafeteriaGodTiempoGeneracion = generadoresGod.getFirst().getTiempoGeneracion();
            GodCostMultiplicador = generadoresGod.getFirst().getCostMultiplicador();
            cafeteriaGodMultiplicador = generadoresGod.getFirst().getMultiplicador();
        }
        Generator cafeteriaGod = new Generator(3, "cafeteriaGod",cafeteriaGodPrecio, cafeteriaGodCafeSeg, cafeteriaGodTiempoGeneracion,1.12,GodCostMultiplicador, cafeteriaGodMultiplicador,this.getId());
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
        if (onCafeChanged != null) onCafeChanged.accept(this.numCafes);
    }

    public void increaseNumCafes(){

        this.numCafes++;
        if (onCafeChanged != null) onCafeChanged.accept(this.numCafes);
    }
    public synchronized void addNumCafes(Integer coffes){
        this.numCafes = this.numCafes + coffes;
        if (onCafeChanged != null) onCafeChanged.accept(this.numCafes);
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

    public ArrayList<Integer> getPreciosBase() {
        ArrayList<Integer> preciosBase = new ArrayList<>();
        preciosBase.add((int) (cafeteriaPrecio));
        preciosBase.add((int)(cafeteriaChetaPrecio));
        preciosBase.add((int)(cafeteriaGodPrecio));
        return preciosBase;
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



    public void setMultiplicadors(ArrayList<Integer> multipliers) {
        this.cafeteriaMultiplicador = multipliers.get(0);
        this.cafeteriaChetaMultiplicador = multipliers.get(1);
        this.cafeteriaGodMultiplicador = multipliers.get(2);
    }

    public void setPrecios(ArrayList<Double> precios) {
        this.cafeteriaPrecio = precios.get(0);
        this.cafeteriaChetaPrecio = precios.get(1);
        this.cafeteriaGodPrecio = precios.get(2);
    }

    public void mejorarGod(){

        for(Generator generator: generadoresGod){
            generator.setMultiplicador(generator.getMultiplicador()+1);
            generator.setCostMultiplicador(generator.getCostMultiplicador()*2);

            //GodCostMultiplicador = generator.getCostMultiplicador()*2;
            //cafeteriaGodMultiplicador = generator.getMultiplicador()+1;
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






    public Integer getCafeteriaMultiplicador() {
        return cafeteriaMultiplicador;
    }

    public Integer getCafeteriaCostMultiplicador() {
        return cafeteriaCostMultiplicador;
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
}
