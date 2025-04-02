package Persistence;

import Business.entity.Game;

import java.util.List;

public interface GameDAO {
    void addGame(Game game);
    void updateGame(Game game);
    void deleteGame(Game game);
    Game getGame(int id);
    List<Game> getAllGames();
}
