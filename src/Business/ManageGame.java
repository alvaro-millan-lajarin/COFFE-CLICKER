package Business;

import Business.Entidades.Game;
import Business.Entidades.Generator;
import Business.Entidades.User;
import Persistence.GameDAO;
import Persistence.sql.SQLGameDAO;
import Persistence.sql.SQLGeneratorDAO;
import Persistence.sql.SQLUserDAO;
import Presenstation.View.Scenes.Scenes;

import java.util.ArrayList;
import java.util.List;

public class ManageGame {
    private Game game;
    private SQLGameDAO sqlGameDAO = new SQLGameDAO();
    private SQLGeneratorDAO sqlGeneratorDAO = new SQLGeneratorDAO();



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
    public void addCafetera(String cafetera) {
        Generator cafeteria = new Generator(1,cafetera, game.getCafeteriaPrecio(), game.getCafeteriaCafeSeg(), game.getCafeteriaTiempoGeneracion(), 1.07, game.getCafeteriaCostMultiplicador(), game.getCafeteriaMultiplicador(),game.getId());
        game.addCafetera(cafetera);
        sqlGeneratorDAO.addGenerator(cafeteria);
    }
    public ArrayList<Integer> getQuantitas() {

        return game.getQuantitats();
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
        if(game.getNumCafes()>= game.getCafeteriaPrecio()){
            return true;
        }
        return false;
    }
    public boolean enughtCoffeCheta(){
        if(game.getNumCafes()>= game.getCafeteriaChetaPrecio()){
            return true;
        }
        return false;
    }
    public boolean enughtCoffeGod(){
        if(game.getNumCafes()>= game.getCafeteriaGodPrecio()){
            return true;
        }
        return false;
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
                game.setCafeteriaPrecio();
                break;
            case "CafeCheta":
                game.setCafeteriaChetaPrecio();
                break;
            case "CafeGod":
                game.setCafeteriaGodPrecio();
                break;
        }
    }
    public ArrayList<Integer> getPreciosBase() {
        return game.getPreciosBase();
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
        getGame().inicialitzarGeneradors();
    }
    public void updatePriceCoffeCafeteriaBaseDatos(String cafetera) {

        sqlGeneratorDAO.updateGeneratorPrice(cafetera, game.getCafeteriaPrecio());

    }
}
