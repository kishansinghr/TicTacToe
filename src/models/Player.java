package models;

import enums.PlayerType;
import exceptions.MoveNotAllowedException;

import java.util.Scanner;

public class Player {
    private final String name;
    private final Symbol symbol;
    private final PlayerType playerType;

    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public Move makeMove(Board board) throws MoveNotAllowedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter row number: ");
        int row = scanner.nextInt();
        System.out.println("Enter column number: ");
        int col = scanner.nextInt();
        return new Move(row, col, this);
    }
}
