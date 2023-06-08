package models;

import enums.PlayerType;
import exceptions.InvalidInputException;
import exceptions.MoveNotAllowedException;
import strategies.ColumnWinningStrategy;
import strategies.DiagonalWinningStrategy;
import strategies.RowWinningStrategy;
import strategies.WinningStrategy;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Game {
    private GameStatus gameStatus;
    private List<Player> playerList;
    private Board board;
    private final Stack<Move> moveList;
    private int playerIndex;
    private Player winner;
    private final List<WinningStrategy> winningStrategies;

    public static Builder getBuilder() {
        return new Builder();
    }

    private Game(int size, List<Player> playerList) {
        this.playerList = playerList;
        this.board = new Board(size);
        this.gameStatus = GameStatus.IN_PROGRESS;
        moveList = new Stack<>();
        playerIndex = 0;
        this.winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColumnWinningStrategy(),
                new DiagonalWinningStrategy()
        );
    }

    public GameStatus getStatus() {
        return gameStatus;
    }

    public void setStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public void printBoard() {
        this.board.print();
    }

    public void printResult() {
        if (this.gameStatus == GameStatus.DRAW) {
            System.out.println("Match draw");
        } else if (this.gameStatus == GameStatus.WON) {
            System.out.println("Hurray!!! "+winner.getName()+" is won");
            this.printBoard();
        } else {
            System.out.println("Something went wrong");
        }
    }

    public void makeMove() {
        Player currentPlayer = this.getPlayerList().get(this.playerIndex);
        System.out.println("Turn of Player "+currentPlayer.getName());
        Move move = currentPlayer.makeMove();

        try {
            this.board.makeMove(move);
        } catch (MoveNotAllowedException e) {
            System.out.println(e.getMessage());
            return;
        }

        //add move in list
        this.moveList.push(move);

        if (this.checkWinner(move)) {
            this.winner = currentPlayer;
            this.gameStatus = GameStatus.WON;
        } else if (this.moveList.size() == this.board.getSize() * this.board.getSize()) {
            this.gameStatus = GameStatus.DRAW;
        }

        this.playerIndex += 1;
        this.playerIndex %= this.playerList.size();
    }

    public void undoLastMove() {
        Move lastMove = this.moveList.pop();
        try {
            this.board.undoMove(lastMove);
        } catch (MoveNotAllowedException e) {
            throw new RuntimeException(e);
        }

        this.playerIndex += (this.playerList.size()-1);
        this.playerIndex %= this.playerList.size();
    }
    private boolean checkWinner(Move move) {
        for (WinningStrategy winningStrategy : this.winningStrategies) {
            if (winningStrategy.checkWinner(this.board, move)) {
                return true;
            }
        }
        return false;
    }

    public static class Builder {
        private int size;
        private List<Player> playerList;

        private Builder() {
        }

        public int getSize() {
            return size;
        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public List<Player> getPlayerList() {
            return playerList;
        }

        public Builder setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
            return this;
        }

        public Game build() throws InvalidInputException {
            if (size != this.playerList.size() + 1) {
                throw new InvalidInputException("Player count is not matching with board size."); // player count is not matching with board size
            }

            long botCount = playerList.stream().filter(p -> p.getPlayerType() == PlayerType.BOT).count();
            if (botCount > 1) {
                throw new InvalidInputException("More than one bots not allowed."); //more than one bot
            }

            int uniqueSymbolCount = playerList.stream().map(p -> p.getSymbol().character()).collect(Collectors.toSet()).size();
            if (uniqueSymbolCount < size - 1) {
                throw new InvalidInputException("Duplicate symbol not allowed"); // there are duplicate symbols
            }

            return new Game(size, playerList);
        }
    }
}
