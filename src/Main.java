import controllers.GameController;
import enums.BotDifficultyLevel;
import exceptions.GameNotFoundException;
import exceptions.InvalidInputException;
import models.BotPlayer;
import models.Player;
import enums.PlayerType;
import models.GameStatus;
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

        System.out.println("Do you want to add bot in game. (y/n)");
        char c = scanner.next().charAt(0);

        int i = 0;
        if (c == 'Y' || c == 'y') {
            System.out.println("Select bot difficulty level (0: Easy, 1: Medium):");

            int diffLevel = scanner.nextInt();
            BotDifficultyLevel level = BotDifficultyLevel.EASY;

            if(diffLevel==1) {
                level = BotDifficultyLevel.MEDIUM;
            }
            playerList.add(new BotPlayer(level));
            i = 1;
        }

        for (; i<boardSize-1; i++) {
            System.out.println("Enter name of player "+i+" : ");
            String name = scanner.next();
            System.out.println("Enter symbol for player "+i+" : ");
            c = scanner.next().charAt(0);

            playerList.add(new Player(name, new Symbol(c), PlayerType.HUMAN));
        }
//        int boardSize = 3;
//        playerList.add(new Player("Kishan", new Symbol('O'), PlayerType.HUMAN));
//        playerList.add(new BotPlayer(BotDifficultyLevel.MEDIUM));

        int gameId = gameController.createGame(boardSize, playerList);

        while (gameController.getGameStatus(gameId) == GameStatus.IN_PROGRESS) {
            try {
                gameController.printBoard(gameId);
                System.out.println("Do you want to undo? (Y/N) : ");
                c = scanner.next().charAt(0);
                if (c == 'Y' || c == 'y') {
                    gameController.undoLastMove(gameId);
                    continue;
                }
                gameController.makeMove(gameId);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        gameController.printResult(gameId);
    }
}