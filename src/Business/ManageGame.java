package Business;

import Business.Entidades.Game;
import Persistence.GameDAO;
import Persistence.GeneratorDAO;
import Persistence.StatisticDAO;
import Persistence.sql.SQLGameDAO;
import Persistence.sql.SQLGeneratorDAO;
import Persistence.sql.SQLStatisticDAO;

import java.util.List;

public class ManageGame {
    private Game game;
    private final GameDAO gameDAO;
    private final GeneratorDAO generatorDAO;
    private final StatisticDAO statisticDAO;

    public ManageGame() {
        this.gameDAO = new SQLGameDAO();
        this.generatorDAO = new SQLGeneratorDAO();
        this.statisticDAO = new SQLStatisticDAO();
    }
    /**
     * Establece el juego actual cargándolo desde la base de datos.
     *
     * @param game Partida seleccionada.
     */
    public void setGame(Game game) {
        this.game = getGameBaseDeDatos(game);


    }
    /**
     * Busca en la base de datos una partida que coincida por nombre e ID de usuario.
     *
     * @param newGame Partida a buscar.
     * @return La partida encontrada o null si no existe.
     */
    public Game getGameBaseDeDatos(Game newGame) {
        for (Game game : getAllGames()) {
            if(game.getNombre().equals(newGame.getNombre()) && game.getIdUser() == newGame.getIdUser()) {
                return game;
            }
        }
        return null;
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
     * Incrementa en 1 el número de cafés del juego actual.
     */
    public void increaseNumCafes() {
        game.increaseNumCafes();
    }

    /**
     * Marca la partida como finalizada en la base de datos.
     */
    public void setFinish(){
        gameDAO.finishTrue(game);
    }

    /**
     * Resta el coste en cafés al jugador según el tipo de cafetera comprada.
     *
     * @param cafetera Nombre del tipo de cafetera ("cafetera", "CafeCheta", "cafeterGod").
     */
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
    /**
     * Resta el café correspondiente al coste de mejora del tipo de cafetera indicado.
     *
     * @param cafetera Nombre del tipo de cafetera ("cafetera", "CafeCheta", "cafeGod").
     */
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
    /**
     * Elimina la partida indicada y sus estadísticas asociadas de la base de datos.
     *
     * @param game Partida a eliminar.
     */
    public void deleteGameSelected(Game game) {
        statisticDAO.deleteEstadisticasByPartidaId(game.getId());
        gameDAO.deleteGame(game);
    }
    /**
     * Añade una nueva partida a la base de datos.
     *
     * @param game Partida a añadir.
     */
    public void addGame(Game game) {
        gameDAO.addGame(game);
    }


    /**
     * Actualiza los datos de la partida actual en la base de datos.
     */
    public void updateGame() {
        gameDAO.updateGame(getGame());
    }
    /**
     * Cierra la sesión actual, eliminando la referencia al juego en memoria.
     */
    public void logout(){
        game = null;
    }
    /**
     * Devuelve el juego actual en ejecución.
     *
     * @return Juego actual.
     */
    public Game getGame() {
        return game;
    }


}
