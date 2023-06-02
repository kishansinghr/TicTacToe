package models;

import exceptions.InvalidInputException;
import strategies.ColumnWinningStrategy;
import strategies.DiagonalWinningStrategy;
import strategies.RowWinningStrategy;
import strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private Status status;
    private List<Player> playerList;
    private Board board;
    private List<Move> moveList;
    private int playerIndex;
    private Player winner;
    private List<WinningStrategy> winningStrategies;

    public static Builder getBuilder() {
        return new Builder();
    }

    private Game(int size, List<Player> playerList) {
        this.playerList = playerList;
        this.board = new Board(size);
        this.status = Status.IN_PROGRESS;
        moveList = new ArrayList<>();
        playerIndex = 0;
        this.winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColumnWinningStrategy(),
                new DiagonalWinningStrategy()
        );
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public List<Move> getMoveList() {
        return moveList;
    }

    public void setMoveList(List<Move> moveList) {
        this.moveList = moveList;
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
        if (this.status == Status.DRAW) {
            System.out.println("Match draw");
        } else if (this.status == Status.WON) {
            System.out.println("Hurray!!! "+winner.getName()+" is won");
            this.printBoard();
        } else {
            System.out.println("Something went wrong");
        }
    }

    public void makeMove() {
        Player currentPlayer = this.getPlayerList().get(this.playerIndex);
        this.printBoard();
        System.out.println("Turn of Player "+currentPlayer.getName());
        Cell cell = currentPlayer.makeMove();

        //add move in list
        this.moveList.add(new Move(cell, currentPlayer));

        this.board.makeMove(cell, currentPlayer.getSymbol());

        if (this.checkWinner(cell)) {
            this.winner = currentPlayer;
            this.status = Status.WON;
        } else if (this.moveList.size() == this.board.getSize() * this.board.getSize()) {
            this.status = Status.DRAW;
        }

        this.playerIndex += 1;
        this.playerIndex %= this.playerList.size();
    }

    private boolean checkWinner(Cell cell) {
        for (WinningStrategy winningStrategy : this.winningStrategies) {
            if (winningStrategy.checkWinner(this.board, cell)) {
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

            int uniqueSymbolCount = playerList.stream().map(p -> p.getSymbol().getCharacter()).collect(Collectors.toSet()).size();
            if (uniqueSymbolCount < size - 1) {
                throw new InvalidInputException("Duplicate symbol not allowed"); // there are duplicate symbols
            }

            return new Game(size, playerList);
        }
    }
}
