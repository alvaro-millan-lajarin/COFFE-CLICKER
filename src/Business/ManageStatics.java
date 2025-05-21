package Business;

import Business.Entidades.Game;
import Business.Entidades.Pair;
import Persistence.GameDAO;
import Persistence.StatisticDAO;
import Persistence.sql.SQLGameDAO;
import Persistence.sql.SQLStatisticDAO;
import Presenstation.Messages;
import Presenstation.View.Grafica.Grafica;


import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase encargada de gestionar la visualización de estadísticas del juego,
 * como el histórico de cafés generados.
 */
public class ManageStatics {
    private Game game;
    private final StatisticDAO statisticDAO;
    private final GameDAO gameDAO;
    private final Messages messages = new Messages();

    /**
     * Constructor de ManageStatics.
     *
     * @param statisticDAO DAO que permite acceder a los datos estadísticos desde la base de datos.
     */
    public ManageStatics(StatisticDAO statisticDAO) {
        this.statisticDAO = statisticDAO;
        this.gameDAO = new SQLGameDAO();
    }

    /**
     * Muestra una gráfica con el histórico de cafés generados por una partida.
     * Si no hay datos, muestra un mensaje informativo.
     *
     * @param idPartida ID de la partida para la que se desea ver la gráfica.
     */
    public void mostrarGraficaCafes(int idPartida) {
        List<Pair<LocalDateTime, Integer>> historico = statisticDAO.getHistoricoCafes(idPartida);

        if (historico.isEmpty()) {
            messages.noHayDatosGraficar();
            return;
        }

        Grafica grafica = new Grafica(historico);

        JFrame frame = new JFrame("Histórico de Cafés");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(grafica);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    /**
     * Registra el número actual de cafés como parte del histórico en la base de datos.
     *
     */
    public void logCafeHistorico(){
        statisticDAO.logCafeHistorico(game.getId(), game.getNumCafes());
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

}
