package Business;

import Business.Entidades.Game;
import Business.Entidades.Generator;
import Persistence.GameDAO;
import Persistence.GeneratorDAO;
import Persistence.StatisticDAO;
import Persistence.sql.SQLGameDAO;
import Persistence.sql.SQLGeneratorDAO;
import Persistence.sql.SQLStatisticDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona la lógica del juego: partidas, generadores y estadísticas.
 */
public class ManageGameGenerators {
    private Game game;
    private final GameDAO gameDAO;
    private final GeneratorDAO generatorDAO;

    /**
     * Constructor de ManageGame. Inicializa los DAOs para acceder a la base de datos.
     */
    public ManageGameGenerators() {
        this.gameDAO = new SQLGameDAO();
        this.generatorDAO = new SQLGeneratorDAO();

    }

    /**
     * Devuelve el juego actual en ejecución.
     *
     * @return Juego actual.
     */
    public Game getGame() {
        return game;
    }


    /**
     * Establece el juego actual cargándolo desde la base de datos.
     *
     * @param game Partida seleccionada.
     */
    public void setGame(Game game) {
        this.game = game;


    }

    /**
     * Añade generadores básicos al juego actual en la base de datos.
     */
    public void addBasicGenerator() {
        generatorDAO.addBasicGenerators(this.game.getId());
    }

    /**
     * Devuelve la cantidad de generadores de cada tipo.
     *
     * @return Lista con cantidades: [Cafetera, Cheta, God].
     */
    public ArrayList<Integer> getQuantitas() {
        ArrayList<Integer> quantitats = new ArrayList<>();
        quantitats.add(game.getGeneradoresCafetera().size());
        quantitats.add(game.getGeneradoresChetas().size());
        quantitats.add(game.getGeneradoresGod().size());

        return quantitats;
    }

    /**
     * Devuelve la producción por unidad de cada tipo de generador.
     *
     * @return Lista con descripciones de producción.
     */
    public ArrayList<String> getProduccionsUnitat() {
       return game.getProduccionsUnitat();
    }

    /**
     * Inicia un generador tipo Cafetera.
     */
    public void startGeneratorCafetera(){
        game.startGeneratorCafetera();
    }

    /**
     * Inicia un generador tipo Cafetera Cheta.
     */
    public void startGeneratorCafeteraCheta(){
        game.startGeneratorCafeteraCheta();
    }

    /**
     * Inicia un generador tipo Cafetera God.
     */
    public void startGeneratorCafeteraGod(){
        game.startGeneratorCafeteraGod();
    }

    /**
     * Comprueba si hay suficiente café para comprar una Cafetera.
     *
     * @return true si hay café suficiente, false en caso contrario.
     */
    public boolean enughtCoffeCafeteria(){
        if(game.getGeneradoresCafetera().isEmpty()){
            if(game.getNumCafes()>= 10){
                return true;
            }else{
                return false;
            }
        }else{
            if(game.getNumCafes()>= game.getGeneradoresCafetera().getFirst().getPrecio()){
                return true;
            }else{
                return false;
            }

        }
    }

    /**
     * Comprueba si hay suficiente café para comprar una Cafetera Cheta.
     *
     * @return true si hay café suficiente, false en caso contrario.
     */
    public boolean enughtCoffeCheta(){
        if(game.getGeneradoresChetas().isEmpty()){
            if(game.getNumCafes()>= 150){
                return true;
            }else{
                return false;
            }
        }else{
            if(game.getNumCafes()>= game.getGeneradoresChetas().getFirst().getPrecio()){
                return true;
            }else{
                return false;
            }

        }
    }

    /**
     * Comprueba si hay suficiente café para comprar una Cafetera God.
     *
     * @return true si hay café suficiente, false en caso contrario.
     */
    public boolean enughtCoffeGod(){
        if(game.getGeneradoresGod().isEmpty()){
            if(game.getNumCafes()>= 2000){
                return true;
            }else{
                return false;
            }
        }else{
            if(game.getNumCafes()>= game.getGeneradoresGod().getFirst().getPrecio()){
                return true;
            }else{
                return false;
            }

        }
    }


    /**
     * Actualiza el precio de una cafetera según su tipo.
     *
     * @param cafetera Nombre del tipo de cafetera ("cafetera", "CafeCheta", "CafeGod").
     */
    public void updatePriceCoffe(String cafetera) {

        switch (cafetera) {
            case "cafetera":
                updatePriceCafetera();

                break;
            case "CafeCheta":
                updatePriceCafeteraCheta();

                break;
            case "CafeGod":
                updatePriceCafeteraGod();

                break;
        }
    }

    /**
     * Actualiza el precio de las cafeteras God en función de cuántas hay.
     */
    public void updatePriceCafeteraGod(){
        double cafeteraPrecio = 0;
        cafeteraPrecio = 2000 * Math.pow(1.12, game.getGeneradoresGod().size());
        for(Generator generator : game.getGeneradoresGod()){
            generator.setPrecio(cafeteraPrecio);
        }
    }


    /**
     * Actualiza el precio de las cafeteras Cheta en función de cuántas hay.
     */
    public void updatePriceCafeteraCheta(){
        double cafeteraPrecio = 0;
        cafeteraPrecio = 150 * Math.pow(1.15, game.getGeneradoresChetas().size());
        for(Generator generator : game.getGeneradoresChetas()){
            generator.setPrecio(cafeteraPrecio);
        }
    }

    /**
     * Actualiza el precio de las cafeteras normales en función de cuántas hay.
     */
    public void updatePriceCafetera(){
        double cafeteraPrecio = 0;
        cafeteraPrecio = 10 * Math.pow(1.07, game.getGeneradoresCafetera().size());
        for(Generator generator : game.getGeneradoresCafetera()){
            generator.setPrecio(cafeteraPrecio);
        }
    }

    /**
     * Devuelve los precios base de los generadores actuales.
     *
     * @return Lista con precios actuales de [Cafetera, Cheta, God].
     */
    public ArrayList<Integer> getPreciosBase() {
        ArrayList<Integer> preciosBase = new ArrayList<>();
        int precioCafetera = 10;
        int precioCheta = 150;
        int precioGod = 2000;
        if(!game.getGeneradoresCafetera().isEmpty()){
            precioCafetera = game.getGeneradoresCafetera().get(0).getPrecio().intValue();
        }
        if(!game.getGeneradoresChetas().isEmpty()){
            precioCheta = game.getGeneradoresChetas().get(0).getPrecio().intValue();
        }
        if(!game.getGeneradoresGod().isEmpty()){
            precioGod = game.getGeneradoresGod().get(0).getPrecio().intValue();
        }
        preciosBase.add(precioCafetera);
        preciosBase.add(precioCheta);
        preciosBase.add(precioGod);
        return preciosBase;
    }

    /**
     * Mejora todas las cafeteras normales del juego actual.
     */
    public void mejorarCafetera(){
        game.mejorarCafetera();
    }

    /**
     * Mejora todas las cafeteras Cheta del juego actual.
     */
    public void mejorarCheta(){
        game.mejorarCheta();
    }

    /**
     * Mejora todas las cafeteras God del juego actual.
     */
    public void mejorarGod(){
        game.mejorarGod();
    }

    /**
     * Devuelve el coste de mejora (costMultiplicador) de cada tipo de generador.
     *
     * @return Lista con los costes: [Cafetera, Cheta, God].
     */
    public ArrayList<Integer> getCostMultplicadors(){
        return game.getCostMultplicadors();
    }

    /**
     * Devuelve el valor actual del multiplicador de cada tipo de generador.
     *
     * @return Lista con los multiplicadores: [Cafetera, Cheta, God].
     */
    public ArrayList<Integer> getMultplicadors(){
       return game.getMultplicadors();
    }

    /**
     * Verifica si hay suficiente café para mejorar la Cafetera.
     *
     * @return true si hay suficiente café, false en caso contrario.
     */
    public boolean enoughtCoffeMejoraCafetera(){
        if(game.getNumCafes()>= game.getCostMultplicadors().getFirst() && !game.getGeneradoresCafetera().isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Verifica si hay suficiente café para mejorar la Cafetera Cheta.
     *
     * @return true si hay suficiente café, false en caso contrario.
     */
    public boolean enoughtCoffeMejoraCheta(){
        if(game.getNumCafes()>= game.getCostMultplicadors().get(1) && !game.getGeneradoresChetas().isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Verifica si hay suficiente café para mejorar la Cafetera God.
     *
     * @return true si hay suficiente café, false en caso contrario.
     */
    public boolean enoughtCoffeMejoraGod(){
        if(game.getNumCafes()>= game.getCostMultplicadors().get(2) && !game.getGeneradoresGod().isEmpty()){
            return true;
        }
        return false;
    }


    /**
     * Devuelve una lista con todas las partidas almacenadas en la base de datos.
     *
     * @return Lista de partidas.
     */
    public List<Game> getAllGames() {
        return gameDAO.getAllGames();
    }

    /**
     * Actualiza los datos de la partida actual en la base de datos.
     */
    public void updateGame() {
        gameDAO.updateGame(getGame());
    }

    /**
     * Actualiza la información de los generadores actuales en la base de datos.
     */
    public void updateGenerators( ){

        if(!game.getGeneradoresCafetera().isEmpty()){
            generatorDAO.updateGenerator(game.getGeneradoresCafetera().getFirst(), game.getId(), game.getGeneradoresCafetera().size());
        }

        if(!game.getGeneradoresChetas().isEmpty()){
            generatorDAO.updateGenerator(game.getGeneradoresChetas().getFirst(), game.getId(), game.getGeneradoresChetas().size());

        }
        if(!game.getGeneradoresGod().isEmpty()){
            generatorDAO.updateGenerator(game.getGeneradoresGod().getFirst(), game.getId(), game.getGeneradoresGod().size());
        }
    }

    /**
     * Detiene todos los hilos de generadores del juego actual.
     */
    public void stopGenerators(){
        stopGeneratorsCafetera();
        stopGeneratorsCheta();
        stopGeneratorsGod();
    }

    /**
     * Detiene todos los hilos de generadores tipo Cafetera.
     */
    public void stopGeneratorsCafetera(){
        for(Generator generator : game.getGeneradoresCafetera()){
            generator.interrupt();

        }
    }

    /**
     * Detiene todos los hilos de generadores tipo Cheta.
     */
    public void stopGeneratorsCheta(){
        for(Generator generator : game.getGeneradoresChetas()){
            generator.interrupt();

        }
    }

    /**
     * Detiene todos los hilos de generadores tipo God.
     */
    public void stopGeneratorsGod(){
        for(Generator generator : game.getGeneradoresGod()){
            generator.interrupt();

        }
    }

    /**
     * Recupera de la base de datos los generadores asociados a la partida actual
     * y los carga en memoria, creando y arrancando sus hilos.
     */
    public void setGeneradores(){
        List<Generator> generatorsCafetera = new ArrayList<>();
        List<Generator> generatorsChetas = new ArrayList<>();
        List<Generator> generatorsGod = new ArrayList<>();
        boolean cafeteraAdded = false;
        boolean chetasAdded = false;
        boolean godadded = false;

        for(Generator generator : generatorDAO.getAllGenerators()){
            if(generator.getIdGame() == game.getId()){
                if(generator.getNombre().equals("Cafetera") && !cafeteraAdded){
                    int cantidadGenerador = generatorDAO.numeroGenerador(generator);

                    for(int i = 0; i< cantidadGenerador; i++){
                        Generator nuevo = new Generator((int) generator.getId(), generator.getNombre(), generator.getPrecio(), generator.getCafeSeg(), generator.getTiempoGeneracion(), generator.getIncrementCost(), generator.getCostMultiplicador(), generator.getMultiplicador(), game.getId());
                        nuevo.setGame(game);
                        nuevo.start();
                        generatorsCafetera.add(nuevo);
                    }
                    cafeteraAdded = true;
                }
                if(generator.getNombre().equals("CafeCheta") && !chetasAdded){
                    int cantidadGenerador = generatorDAO.numeroGenerador(generator);

                    for(int i = 0; i< cantidadGenerador; i++){
                        Generator nuevo = new Generator((int) generator.getId(), generator.getNombre(), generator.getPrecio(), generator.getCafeSeg(), generator.getTiempoGeneracion(), generator.getIncrementCost(), generator.getCostMultiplicador(), generator.getMultiplicador(), game.getId());
                        nuevo.setGame(game);
                        nuevo.start();
                        generatorsChetas.add(nuevo);
                    }
                    chetasAdded = true;
                }
                if(generator.getNombre().equals("CafeGod") && !godadded){
                    int cantidadGenerador = generatorDAO.numeroGenerador(generator);

                    for(int i = 0; i< cantidadGenerador; i++){
                        Generator nuevo = new Generator((int) generator.getId(), generator.getNombre(), generator.getPrecio(), generator.getCafeSeg(), generator.getTiempoGeneracion(), generator.getIncrementCost(), generator.getCostMultiplicador(), generator.getMultiplicador(), game.getId());
                        nuevo.setGame(game);
                        nuevo.start();
                        generatorsGod.add(nuevo);
                    }
                    godadded = true;
                }
            }

        }
        game.setGeneradoresCafetera(generatorsCafetera);
        game.setGeneradoresChetas(generatorsChetas);
        game.setGeneradoresGod(generatorsGod);
    }

    /**
     * Recupera un generador desde la base de datos por nombre e ID de partida.
     *
     * @param gameId ID de la partida.
     * @param nombreCafetera Nombre del tipo de cafetera ("Cafetera", "CafeCheta", "CafeGod").
     * @return Generador encontrado en la base de datos.
     */
    public Generator getCafeteraBaseDeDatos(int gameId, String nombreCafetera){
        return generatorDAO.getGenerator(gameId, nombreCafetera);
    }


}

