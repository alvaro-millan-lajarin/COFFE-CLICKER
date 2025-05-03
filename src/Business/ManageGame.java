package Business;

import Business.Entidades.Game;
import Business.Entidades.Generator;
import Business.Entidades.User;
import Persistence.GameDAO;
import Persistence.sql.SQLGameDAO;
import Persistence.sql.SQLGeneratorDAO;
import Persistence.sql.SQLStatisticDAO;
import Persistence.sql.SQLUserDAO;
import Presenstation.View.Scenes.Scenes;

import java.util.ArrayList;
import java.util.List;

public class ManageGame {
    private Game game;
    private SQLGameDAO sqlGameDAO = new SQLGameDAO();
    private SQLGeneratorDAO sqlGeneratorDAO = new SQLGeneratorDAO();
    private SQLStatisticDAO sqlStatisticDAO = new SQLStatisticDAO();




    public ManageGame() {

    }
    public Game getGame() {
        return game;
    }

    public void increaseNumCafes() {


        game.increaseNumCafes();
        sqlGameDAO.logCafeHistorico(game.getId(), game.getNumCafes());
    }
    public void setGame(Game game) {
        this.game = getGameBaseDeDatos(game);


    }
    public void addBasicGenerator() {
        sqlGeneratorDAO.addBasicGenerators(this.game.getId());
    }
    public ArrayList<Integer> getQuantitas() {
        ArrayList<Integer> quantitats = new ArrayList<>();
        quantitats.add(game.getGeneradoresCafetera().size());
        quantitats.add(game.getGeneradoresChetas().size());
        quantitats.add(game.getGeneradoresGod().size());

        return quantitats;
    }
    public ArrayList<String> getProduccionsUnitat() {
       return game.getProduccionsUnitat();
    }
    public void startGeneratorCafetera(){
        game.startGeneratorCafetera();
    }
    public void startGeneratorCafeteraCheta(){
        game.startGeneratorCafeteraCheta();
    }
    public void startGeneratorCafeteraGod(){
        game.startGeneratorCafeteraGod();
    }

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
    public void restarCafe(String cafetera) {

        Integer cafeActual = game.getNumCafes();
        Double costeCafe =0.0;
        switch (cafetera) {
            case "cafetera":
                costeCafe = game.getCafeteriaPrecio();
                break;
            case "CafeCheta":
               costeCafe = game.getCafeteriaChetaPrecio();
               break;
            case "cafeterGod":
                costeCafe = game.getCafeteriaGodPrecio();
                break;
        }

        game.setNumCafes((int) (cafeActual-costeCafe));

    }
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
    public void updatePriceCafeteraGod(){
        double cafeteraPrecio = 0;
        cafeteraPrecio = 2000 * Math.pow(1.12, game.getGeneradoresGod().size());
        for(Generator generator : game.getGeneradoresGod()){
            generator.setPrecio(cafeteraPrecio);
        }
    }
    public void updatePriceCafeteraCheta(){
        double cafeteraPrecio = 0;
        cafeteraPrecio = 150 * Math.pow(1.15, game.getGeneradoresChetas().size());
        for(Generator generator : game.getGeneradoresChetas()){
            generator.setPrecio(cafeteraPrecio);
        }
    }
    public void updatePriceCafetera(){
        double cafeteraPrecio = 0;
        cafeteraPrecio = 10 * Math.pow(1.07, game.getGeneradoresCafetera().size());
        for(Generator generator : game.getGeneradoresCafetera()){
            generator.setPrecio(cafeteraPrecio);
        }
    }
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

    public void mejorarCafetera(){
        game.mejorarCafetera();
    }
    public void mejorarCheta(){
        game.mejorarCheta();
    }
    public void mejorarGod(){
        game.mejorarGod();
    }
    public ArrayList<Integer> getCostMultplicadors(){


        return game.getCostMultplicadors();
    }
    public ArrayList<Integer> getMultplicadors(){
       return game.getMultplicadors();
    }
    public boolean enoughtCoffeMejoraCafetera(){
        if(game.getNumCafes()>= game.getCostMultplicadors().get(0)){
            return true;
        }
        return false;
    }
    public boolean enoughtCoffeMejoraCheta(){
        if(game.getNumCafes()>= game.getCostMultplicadors().get(1)){
            return true;
        }
        return false;
    }
    public boolean enoughtCoffeMejoraGod(){
        if(game.getNumCafes()>= game.getCostMultplicadors().get(2)){
            return true;
        }
        return false;
    }
    public void restarCafeMejora(String cafetera) {

        Integer cafeActual = game.getNumCafes();
        Integer costeCafe =0;
        switch (cafetera) {
            case "cafetera":
                costeCafe = game.getCostMultplicadors().get(0);
                break;
            case "CafeCheta":
                costeCafe = game.getCostMultplicadors().get(1);
                break;
            case "cafeGod":
                costeCafe = game.getCostMultplicadors().get(2);
                break;
        }

        game.setNumCafes((int) (cafeActual-costeCafe));

    }
    public void deleteGame() {

        sqlGameDAO.deleteGame(this.game);
    }
    public void deleteGameSelected(Game game) {
        sqlStatisticDAO.deleteEstadisticasByPartidaId(game.getId());
        sqlGameDAO.deleteGame(game);
    }
    public List<Game> getAllGames() {
        return sqlGameDAO.getAllGames();
    }
    public void addGame(Game game) {
        sqlGameDAO.addGame(game);
    }
    public Game getGameBaseDeDatos(Game newGame) {

        for (Game game : getAllGames()) {
            if(game.getNombre().equals(newGame.getNombre()) && game.getIdUser() == newGame.getIdUser()) {
                return game;
            }
        }
        return null;
    }
    public void updateGame() {

        sqlGameDAO.updateGame(getGame());
    }
    public void inicializarGeneradores(){
        for (Generator generator : game.getGeneradoresCafetera()) {
            generator.setGame(game);
            generator.start();
        }

        for (Generator generator : game.getGeneradoresChetas()) {
            generator.setGame(game);
            generator.start();
        }

        for (Generator generator : game.getGeneradoresGod()) {
            generator.setGame(game);
            generator.start();
        }
    }
    public void updatePriceCoffeCafeteriaBaseDatos(String cafetera) {

        sqlGeneratorDAO.updateGeneratorPrice(cafetera, game.getCafeteriaPrecio());

    }
    public void updatePriceCoffeChetaBaseDatos(String cafetera) {
        sqlGeneratorDAO.updateGeneratorPrice(cafetera, game.getCafeteriaChetaPrecio());
    }
    public void updatePriceCoffeGodBaseDatos(String cafetera) {
        sqlGeneratorDAO.updateGeneratorPrice(cafetera, game.getCafeteriaGodPrecio());
    }
    public ArrayList<String> getProduccionsUnitatBaseDatos(){
        ArrayList<String> proudccioUnitat = new ArrayList<>();
        boolean cafeteraOK = true;
        boolean chetaOK = true;
        boolean godOK = true;

        List<Generator> generators = sqlGeneratorDAO.getAllGenerators();
        for (Generator generator : generators) {
            double num_cafes = generator.getCafeSeg();
            double tiempoGeneracion = generator.getTiempoGeneracion();
            if (generator.getNombre().equals("Cafetera") && cafeteraOK) {

                proudccioUnitat.add(String.format("%.2f cafès / %.2f s", num_cafes, tiempoGeneracion));
                cafeteraOK = false;

            } else if (generator.getNombre().equals("Cheta") && chetaOK) {
                proudccioUnitat.add(String.format("%.2f cafès / %.2f s", num_cafes, tiempoGeneracion));
                chetaOK = false;
            } else if (generator.getNombre().equalsIgnoreCase("God") && godOK) {
                proudccioUnitat.add(String.format("%.2f cafès / %.2f s", num_cafes, tiempoGeneracion));
                godOK = false;
            }

        }
        return proudccioUnitat;
    }
    public void updateGenerators( ){

        if(!game.getGeneradoresCafetera().isEmpty()){
            double priceCafetera = game.getGeneradoresCafetera().get(0).getPrecio();
            double cafesSegCafetera = game.getGeneradoresCafetera().get(0).getCafeSeg();
            Integer multiplicador = game.getGeneradoresCafetera().get(0).getMultiplicador();
            double tiempoGeneracion = game.getGeneradoresCafetera().get(0).getTiempoGeneracion();
            Integer costMultiplicador = game.getGeneradoresCafetera().get(0).getCostMultiplicador();
            double incrementCost = game.getGeneradoresCafetera().get(0).getIncrementCost();
            Integer numeroCafeteras = game.getGeneradoresCafetera().size();

            sqlGeneratorDAO.updateGenerator("Cafetera",priceCafetera, cafesSegCafetera, multiplicador, tiempoGeneracion, costMultiplicador, incrementCost, numeroCafeteras, game.getId());
        }

        if(!game.getGeneradoresChetas().isEmpty()){
            double priceCafeteraCheta = game.getGeneradoresChetas().get(0).getPrecio();
            double cafesSegCafeteraCheta = game.getGeneradoresChetas().get(0).getCafeSeg();
            Integer multiplicadorCheta = game.getGeneradoresChetas().get(0).getMultiplicador();
            double tiempoGeneracionCheta = game.getGeneradoresChetas().get(0).getTiempoGeneracion();
            Integer costMultiplicadorCheta = game.getGeneradoresChetas().get(0).getCostMultiplicador();
            double incrementCostCheta = game.getGeneradoresChetas().get(0).getIncrementCost();
            Integer numeroCafeterasCheta = game.getGeneradoresChetas().size();

            sqlGeneratorDAO.updateGenerator("CafeCheta",priceCafeteraCheta, cafesSegCafeteraCheta, multiplicadorCheta, tiempoGeneracionCheta, costMultiplicadorCheta, incrementCostCheta, numeroCafeterasCheta, game.getId());
        }
        if(!game.getGeneradoresGod().isEmpty()){
            double priceGod = game.getGeneradoresGod().get(0).getPrecio();
            double cafesSegGod = game.getGeneradoresGod().get(0).getCafeSeg();
            Integer multiplicadorGod = game.getGeneradoresGod().get(0).getMultiplicador();
            double tiempoGeneracionGod = game.getGeneradoresGod().get(0).getTiempoGeneracion();
            Integer costMultiplicadorGod = game.getGeneradoresGod().get(0).getCostMultiplicador();
            double incrementCostGod = game.getGeneradoresGod().get(0).getIncrementCost();
            Integer numeroCafeterasGod = game.getGeneradoresGod().size();

            sqlGeneratorDAO.updateGenerator("CafeGod",priceGod, cafesSegGod, multiplicadorGod, tiempoGeneracionGod, costMultiplicadorGod, incrementCostGod, numeroCafeterasGod, game.getId());
        }
    }
    public void setGeneradores(){
        List<Generator> generatorsCafetera = new ArrayList<>();
        List<Generator> generatorsChetas = new ArrayList<>();
        List<Generator> generatorsGod = new ArrayList<>();
        boolean cafeteraAdded = false;
        boolean chetasAdded = false;
        boolean godadded = false;

        for(Generator generator : sqlGeneratorDAO.getAllGenerators()){
            if(generator.getIdGame() == game.getId()){
                if(generator.getNombre().equals("Cafetera") && !cafeteraAdded){
                    int cantidadGenerador = sqlGeneratorDAO.numeroGenerador(generator);

                    for(int i = 0; i< cantidadGenerador; i++){
                        Generator nuevo = new Generator((int) generator.getId(), generator.getNombre(), generator.getPrecio(), generator.getCafeSeg(), generator.getTiempoGeneracion(), generator.getIncrementCost(), generator.getCostMultiplicador(), generator.getMultiplicador(), game.getId());
                        nuevo.setGame(game);
                        nuevo.start();
                        generatorsCafetera.add(nuevo);
                    }
                    cafeteraAdded = true;
                }
                if(generator.getNombre().equals("CafeCheta") && !chetasAdded){
                    int cantidadGenerador = sqlGeneratorDAO.numeroGenerador(generator);

                    for(int i = 0; i< cantidadGenerador; i++){
                        Generator nuevo = new Generator((int) generator.getId(), generator.getNombre(), generator.getPrecio(), generator.getCafeSeg(), generator.getTiempoGeneracion(), generator.getIncrementCost(), generator.getCostMultiplicador(), generator.getMultiplicador(), game.getId());
                        nuevo.setGame(game);
                        nuevo.start();
                        generatorsChetas.add(nuevo);
                    }
                    chetasAdded = true;
                }
                if(generator.getNombre().equals("CafeGod") && !godadded){
                    int cantidadGenerador = sqlGeneratorDAO.numeroGenerador(generator);

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
}

