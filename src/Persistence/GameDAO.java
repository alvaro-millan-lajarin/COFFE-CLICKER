package Persistence;



import Business.Entidades.Game;
import Business.Entidades.Generator;

import java.util.List;

public interface GameDAO {
    void addGame(Game game);
    void updateGame(Game game);
    void deleteGame(Game game);
    List<Game> getAllGames();
    void finishTrue(Game game);

}
