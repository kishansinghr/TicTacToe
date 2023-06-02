package controllers;

import exceptions.GameNotFoundException;
import exceptions.InvalidInputException;
import models.Game;
import models.Player;
import models.Status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController {
    private int gameCount = 0;
    private Map<Integer, Game> games = new HashMap<>();

    private Game getGameById(int id) throws GameNotFoundException {
        if (games.get(id) == null) {
            throw new GameNotFoundException("No game available for id " + id);
        }
        return games.get(id);
    }

    public int createGame(int size, List<Player> playerList) throws InvalidInputException {
        Game game = Game.getBuilder()
                .setSize(size)
                .setPlayerList(playerList)
                .build();
        this.games.put(++this.gameCount, game);
        return this.gameCount;
    }

    public void makeMove(int gameId) throws GameNotFoundException {
        this.getGameById(gameId).makeMove();
    }

    public Status getGameStatus(int gameId) throws GameNotFoundException {
        return this.getGameById(gameId).getStatus();
    }

    public void printResult(int gameId) throws GameNotFoundException {
        this.getGameById(gameId).printResult();
    }
}
