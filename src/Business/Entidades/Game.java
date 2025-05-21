package Business.Entidades;



import Business.ManageGameGenerators;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;


public class Game {

    private ManageGameGenerators manageGameGenerators;
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


    /**
     * Constructor de la clase Game.
     *
     * @param id Identificador único de la partida.
     * @param idUser Identificador del usuario propietario de la partida.
     * @param nombre Nombre de la partida.
     * @param fechaCreacion Fecha en la que se creó la partida.
     * @param fechaModificacion Fecha en la que se modificó por última vez.
     * @param numCafes Número de cafés acumulados.
     * @param finished Indica si la partida ha finalizado.
     */
    public Game(int id, int idUser, String nombre, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion, int numCafes, boolean finished) {
        this.id = id;
        this.idUser = idUser;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.numCafes = numCafes;
        this.finished = finished;
        this.manageGameGenerators = new ManageGameGenerators();
        this.generadoresCafetera = new ArrayList<>();
        this.generadoresChetas = new ArrayList<>();
        this.generadoresGod = new ArrayList<>();

    }

    /**
     * Inicia un generador tipo Cafetera, lo configura y lo arranca.
     */
    public void startGeneratorCafetera() {

        Generator cafeteria = manageGameGenerators.getCafeteraBaseDeDatos(getId(), "Cafetera");
        cafeteria.setGame(this);
        generadoresCafetera.add(cafeteria);
        cafeteria.start();
    }

    /**
     * Inicia un generador tipo Cafetera Cheta, lo configura y lo arranca.
     */
    public void startGeneratorCafeteraCheta() {
        Generator cafeteriaCheta = manageGameGenerators.getCafeteraBaseDeDatos(getId(), "CafeCheta");
        cafeteriaCheta.setGame(this);
        generadoresChetas.add(cafeteriaCheta);
        cafeteriaCheta.start();
    }

    /**
     * Inicia un generador tipo Cafetera God, lo configura y lo arranca.
     */
    public void startGeneratorCafeteraGod() {
        Generator cafeteriaGod = manageGameGenerators.getCafeteraBaseDeDatos(getId(), "CafeGod");
        cafeteriaGod.setGame(this);
        generadoresGod.add(cafeteriaGod);
        cafeteriaGod.start();
    }

    /**
     * Devuelve el ID de la partida.
     *
     * @return ID de la partida.
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve el ID del usuario propietario de la partida.
     *
     * @return ID del usuario.
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Devuelve el nombre de la partida.
     *
     * @return Nombre de la partida.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la fecha de creación de la partida.
     *
     * @return Fecha de creación.
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Devuelve la fecha de la última modificación de la partida.
     *
     * @return Fecha de modificación.
     */
    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * Devuelve el número actual de cafés acumulados.
     *
     * @return Número de cafés.
     */
    public int getNumCafes() {
        return numCafes;
    }

    /**
     * Establece el número actual de cafés acumulados.
     *
     * @param numCafes Nuevo valor de cafés.
     */
    public void setNumCafes(int numCafes) {
        this.numCafes = numCafes;
    }

    /**
     * Incrementa en 1 el número de cafés acumulados.
     */
    public void increaseNumCafes(){
        this.numCafes++;

    }

    /**
     * Añade una cantidad específica de cafés al total.
     *
     * @param coffes Número de cafés a añadir.
     */
    public synchronized void addNumCafes(Integer coffes){
        this.numCafes = this.numCafes + coffes;
    }

    /**
     * Devuelve la producción por segundo y tiempo de generación de cada tipo de cafetera.
     *
     * @return Lista con descripciones formateadas de la producción por tipo.
     */
    public ArrayList<String> getProduccionsUnitat() {
        ArrayList<String> produccions = new ArrayList<>();

        // Cafeteras normales
        if (!generadoresCafetera.isEmpty()) {
            Generator g = generadoresCafetera.getFirst();
            produccions.add(String.format("%.2f cafès / %.2f s", g.getCafeSeg(), g.getTiempoGeneracion()));
        } else {
            produccions.add("0,20 cafès / 1,00 s");
        }

        // Cafeteras chetas
        if (!generadoresChetas.isEmpty()) {
            Generator g = generadoresChetas.getFirst();
            produccions.add(String.format("%.2f cafès / %.2f s", g.getCafeSeg(), g.getTiempoGeneracion()));
        } else {
            produccions.add("0,50 cafès / 0,70 s");
        }

        // Cafeteras god
        if (!generadoresGod.isEmpty()) {
            Generator g = generadoresGod.getFirst();
            produccions.add(String.format("%.2f cafès / %.2f s", g.getCafeSeg(), g.getTiempoGeneracion()));
        } else {
            produccions.add("30 cafès / 1,3 s");
        }

        return produccions;
    }

    /**
     * Devuelve el precio actual de una Cafetera normal.
     *
     * @return Precio de la primera cafetera o valor por defecto.
     */
    public double getCafeteriaPrecio() {
        int precio = 10;
        if(!generadoresCafetera.isEmpty()){
            return generadoresCafetera.getFirst().getPrecio();
        }
        return precio;

    }

    /**
     * Devuelve el precio actual de una Cafetera Cheta.
     *
     * @return Precio de la primera cafetera cheta o valor por defecto.
     */
    public double getCafeteriaChetaPrecio() {
        int precio = 150;

        if(!generadoresChetas.isEmpty()){
            return generadoresChetas.getFirst().getPrecio();
        }
        return precio;
    }

    /**
     * Devuelve el precio actual de una Cafetera God.
     *
     * @return Precio de la primera cafetera god o valor por defecto.
     */
    public double getCafeteriaGodPrecio() {
        int precio = 2000;

        if(!generadoresGod.isEmpty()){
            return generadoresGod.getFirst().getPrecio();
        }
        return precio;
    }

    /**
     * Mejora todas las cafeteras normales incrementando su multiplicador y su coste de mejora.
     */
    public void mejorarCafetera(){
        for(Generator generator : generadoresCafetera){
            generator.setMultiplicador(generator.getMultiplicador()+1);
            generator.setCostMultiplicador(generator.getCostMultiplicador()*2);

            generator.setCafeSeg(generator.getCafeSeg()*generator.getMultiplicador());
        }
    }

    /**
     * Mejora todas las cafeteras Cheta incrementando su multiplicador y su coste de mejora.
     */
    public void mejorarCheta(){
        for (Generator generator: generadoresChetas){
            generator.setMultiplicador(generator.getMultiplicador()+1);
            generator.setCostMultiplicador(generator.getCostMultiplicador()*2);

            generator.setCafeSeg(generator.getCafeSeg()*generator.getMultiplicador());
        }
    }

    /**
     * Mejora todas las cafeteras God incrementando su multiplicador y su coste de mejora.
     */
    public void mejorarGod(){

        for(Generator generator: generadoresGod){
            generator.setMultiplicador(generator.getMultiplicador()+1);
            generator.setCostMultiplicador(generator.getCostMultiplicador()*2);

            generator.setCafeSeg(generator.getCafeSeg()*generator.getMultiplicador());

        }
    }

    /**
     * Devuelve los costes actuales de mejora (costMultiplicador) de cada tipo de cafetera.
     *
     * @return Lista con los tres costes: Cafetera, Cheta, God.
     */
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

    /**
     * Devuelve los multiplicadores actuales de cada tipo de cafetera.
     *
     * @return Lista con los tres multiplicadores: Cafetera, Cheta, God.
     */
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

    /**
     * Devuelve la lista de generadores tipo Cafetera.
     *
     * @return Lista de generadores Cafetera.
     */
    public List<Generator> getGeneradoresCafetera() {
        return generadoresCafetera;
    }

    /**
     * Devuelve la lista de generadores tipo Cheta.
     *
     * @return Lista de generadores Cheta.
     */
    public List<Generator> getGeneradoresChetas() {
        return generadoresChetas;
    }

    /**
     * Devuelve la lista de generadores tipo God.
     *
     * @return Lista de generadores God.
     */
    public List<Generator> getGeneradoresGod() {
        return generadoresGod;
    }

    /**
     * Establece la lista de generadores tipo Cafetera.
     *
     * @param generadoresCafetera Lista de generadores a asignar.
     */
    public void setGeneradoresCafetera(List<Generator> generadoresCafetera) {
        this.generadoresCafetera = generadoresCafetera;
    }

    /**
     * Establece la lista de generadores tipo Cheta.
     *
     * @param generadoresChetas Lista de generadores a asignar.
     */
    public void setGeneradoresChetas(List<Generator> generadoresChetas) {
        this.generadoresChetas = generadoresChetas;
    }

    /**
     * Establece la lista de generadores tipo God.
     *
     * @param generadoresGod Lista de generadores a asignar.
     */
    public void setGeneradoresGod(List<Generator> generadoresGod) {
        this.generadoresGod = generadoresGod;
    }

    /**
     * Marca la partida como finalizada.
     */
    public void setFinished() {
        this.finished = true;
    }

    /**
     * Indica si la partida está finalizada.
     *
     * @return true si la partida ha finalizado, false en caso contrario.
     */
    public boolean isFinished() {
        return finished;
    }
}
