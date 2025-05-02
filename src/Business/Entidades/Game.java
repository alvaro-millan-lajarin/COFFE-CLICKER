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

    private ArrayList<Integer> quantitatsGeneredors;//0->cafeteria, 1->cafeteraCheta, 2->cafeteraGod, esto se pasa a la base de datos

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
    private Integer cafeteriaMultiplicador = 1; // Multiplicador de la cafetería básica
    private Integer cafeteriaCostMultiplicador = 10;

    private double cafeteriaChetaPrecio = 150; // Precio de la cafetería cheta
    private Integer cafeteriaChetaMultiplicador = 1; // Multiplicador de la cafetería cheta
    private Integer chetaCostMultiplicador = 100;

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


        quantitatsGeneredors = new ArrayList<>();
        quantitatsGeneredors.add(0);
        quantitatsGeneredors.add(0);
        quantitatsGeneredors.add(0);

        produccionsUnitat = new ArrayList<>();

    }
    public void startGeneratorCafetera() {

        Generator cafeteria = new Generator(1, "cafeteria",cafeteriaPrecio,cafeteriaCafeSeg , cafeteriaTiempoGeneracion,1.07,cafeteriaCostMultiplicador, cafeteriaMultiplicador,this.getId());
        cafeteria.setGame(this);
        generadoresCafetera.add(cafeteria);
        cafeteria.start();
    }
    public void startGeneratorCafeteraCheta() {
        Generator cafeteriaCheta = new Generator(2, "cafeteriaCheta",cafeteriaChetaPrecio, cafeteriaChetaCafeSeg, cafeteriaChetaTiempoGeneracion,1.15,chetaCostMultiplicador, cafeteriaChetaMultiplicador,this.getId());
        cafeteriaCheta.setGame(this);
        generadoresChetas.add(cafeteriaCheta);
        cafeteriaCheta.start();
    }
    public void startGeneratorCafeteraGod() {
        Generator cafeteriaGod = new Generator(3, "cafeteriaGod",cafeteriaGodPrecio, cafeteriaGodCafeSeg, cafeteriaGodTiempoGeneracion,1.12,GodCostMultiplicador, cafeteriaGodMultiplicador,this.getId());
        cafeteriaGod.setGame(this);
        generadoresGod.add(cafeteriaGod);
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
    public void addCafetera(String cafetera) {
        if(cafetera.equals("Cafetera") ){
            quantitatsGeneredors.set(0, quantitatsGeneredors.get(0) + 1);
        } else if (cafetera.equals("CafeCheta") ) {
            quantitatsGeneredors.set(1, quantitatsGeneredors.get(1) + 1);
        } else if (cafetera.equals("CafeGod")) {
            quantitatsGeneredors.set(2, quantitatsGeneredors.get(2) + 1);
        }

    }

    public ArrayList<Integer> getQuantitats(){
        return quantitatsGeneredors;
    }
    public ArrayList<String> getProduccionsUnitat() {
        ArrayList<String> produccions = new ArrayList<>();

        // Cafeteras normales
        if (!generadoresCafetera.isEmpty()) {
            Generator g = generadoresCafetera.get(0);
            produccions.add(String.format("%.2f cafès / %.2f s", g.getCafeSeg(), g.getTiempoGeneracion()));
        } else {
            produccions.add("0 cafès / 0 s");
        }

        // Cafeteras chetas
        if (!generadoresChetas.isEmpty()) {
            Generator g = generadoresChetas.get(0);
            produccions.add(String.format("%.2f cafès / %.2f s", g.getCafeSeg(), g.getTiempoGeneracion()));
        } else {
            produccions.add("0 cafès / 0 s");
        }

        // Cafeteras god
        if (!generadoresGod.isEmpty()) {
            Generator g = generadoresGod.get(0);
            produccions.add(String.format("%.2f cafès / %.2f s", g.getCafeSeg(), g.getTiempoGeneracion()));
        } else {
            produccions.add("0 cafès / 0 s");
        }

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

    public void setCafeteriaChetaPrecio() {
        this.cafeteriaChetaPrecio = 150 * Math.pow(1.15, quantitatsGeneredors.get(1));
    }

    public void setCafeteriaPrecio() {
        this.cafeteriaPrecio = 10 * Math.pow(1.07, quantitatsGeneredors.get(0));
    }

    public void setCafeteriaGodPrecio() {
        this.cafeteriaGodPrecio = 2000 * Math.pow(1.12, quantitatsGeneredors.get(2));
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
            cafeteriaCostMultiplicador = generator.getCostMultiplicador()*2;
            cafeteriaMultiplicador = generator.getMultiplicador()+1;
            generator.setCafeSeg(generator.getCafeSeg()*cafeteriaMultiplicador);


            generator.setMultiplicador(generator.getMultiplicador()+1);
            generator.setCostMultiplicador(generator.getCostMultiplicador()*2);
        }
    }
    public void mejorarCheta(){
        for (Generator generator: generadoresChetas){
            chetaCostMultiplicador = generator.getCostMultiplicador()*2;
            cafeteriaChetaMultiplicador = generator.getMultiplicador()+1;
            generator.setCafeSeg(generator.getCafeSeg()*cafeteriaChetaMultiplicador);

            generator.setMultiplicador(generator.getMultiplicador()+1);
            generator.setCostMultiplicador(generator.getCostMultiplicador()*2);
        }
    }

    public void setQuantitats(ArrayList<Integer> quantitats) {
        this.quantitatsGeneredors = quantitats;
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
            GodCostMultiplicador = generator.getCostMultiplicador()*2;
            cafeteriaGodMultiplicador = generator.getMultiplicador()+1;
            generator.setCafeSeg(generator.getCafeSeg()*cafeteriaGodMultiplicador);

            generator.setMultiplicador(generator.getMultiplicador()+1);
            generator.setCostMultiplicador(generator.getCostMultiplicador()*2);
        }
    }
    public ArrayList<Integer> getCostMultplicadors() {
        ArrayList<Integer> costMultplicadors = new ArrayList<>();
        costMultplicadors.add(cafeteriaCostMultiplicador);
        costMultplicadors.add(chetaCostMultiplicador);
        costMultplicadors.add(GodCostMultiplicador);
        return costMultplicadors;
    }
    public ArrayList<Integer> getMultplicadors() {
        ArrayList<Integer> Multplicadors = new ArrayList<>();
        Multplicadors.add(cafeteriaMultiplicador);
        Multplicadors.add(cafeteriaChetaMultiplicador);
        Multplicadors.add(cafeteriaGodMultiplicador);
        return Multplicadors;
    }

    public void inicialitzarGeneradors() {
        for (int i = 0; i < quantitatsGeneredors.get(0); i++) startGeneratorCafetera();
        for (int i = 0; i < quantitatsGeneredors.get(1); i++) startGeneratorCafeteraCheta();
        for (int i = 0; i < quantitatsGeneredors.get(2); i++) startGeneratorCafeteraGod();
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

    public ArrayList<Integer> getQuantitatsGeneredors() {
        return quantitatsGeneredors;
    }

    public double getCafeteriaCafeSeg() {
        return cafeteriaCafeSeg;
    }

    public double getCafeteriaTiempoGeneracion() {
        return cafeteriaTiempoGeneracion;
    }

    public double getCafeteriaChetaCafeSeg() {
        return cafeteriaChetaCafeSeg;
    }

    public double getCafeteriaChetaTiempoGeneracion() {
        return cafeteriaChetaTiempoGeneracion;
    }

    public double getCafeteriaGodCafeSeg() {
        return cafeteriaGodCafeSeg;
    }

    public double getCafeteriaGodTiempoGeneracion() {
        return cafeteriaGodTiempoGeneracion;
    }

    public Integer getCafeteriaMultiplicador() {
        return cafeteriaMultiplicador;
    }

    public Integer getCafeteriaCostMultiplicador() {
        return cafeteriaCostMultiplicador;
    }

    public Integer getCafeteriaChetaMultiplicador() {
        return cafeteriaChetaMultiplicador;
    }

    public Integer getChetaCostMultiplicador() {
        return chetaCostMultiplicador;
    }

    public Integer getCafeteriaGodMultiplicador() {
        return cafeteriaGodMultiplicador;
    }

    public Integer getGodCostMultiplicador() {
        return GodCostMultiplicador;
    }

    public Consumer<Integer> getOnCafeChanged() {
        return onCafeChanged;
    }
}
