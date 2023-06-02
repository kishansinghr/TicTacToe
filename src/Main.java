import controllers.GameController;
import exceptions.GameNotFoundException;
import exceptions.InvalidInputException;
import models.Player;
import models.PlayerType;
import models.Status;
import models.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws GameNotFoundException, InvalidInputException {
        GameController gameController = new GameController();
        System.out.println("Enter the size of board: ");
        Scanner scanner = new Scanner(System.in);

        List<Player> playerList = new ArrayList<>();

        int boardSize = scanner.nextInt();
        for (int i=0; i<boardSize-1; i++) {
            System.out.println("Enter name of player "+i+" : ");
            String name = scanner.next();
            System.out.println("Enter symbol for player "+i+" : ");
            char c = scanner.next().charAt(0);

            playerList.add(new Player(name, new Symbol(c), PlayerType.HUMAN));
        }
        int gameId = gameController.createGame(boardSize, playerList);

        while (gameController.getGameStatus(gameId) == Status.IN_PROGRESS) {
            gameController.makeMove(gameId);
            gameController.printResult(gameId);
        }

        gameController.printResult(gameId);
    }
}