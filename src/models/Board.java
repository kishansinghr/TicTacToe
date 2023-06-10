package models;

import enums.CellStatus;
import exceptions.MoveNotAllowedException;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int size;
    private List<List<Cell>> matrix;

    public Board(int size) {
        this.size = size;
        matrix = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                row.add(new Cell(i, j));
            }
            matrix.add(row);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getMatrix() {
        return matrix;
    }

    public void setMatrix(List<List<Cell>> matrix) {
        this.matrix = matrix;
    }

    public void makeMove(Move move) throws MoveNotAllowedException {
        this.matrix.get(move.getRow())
                .get(move.getColumn()).setPlayer(move.getPlayer());
    }

    public void undoMove(Move move) throws MoveNotAllowedException {
        this.matrix.get(move.getRow())
                .get(move.getColumn()).clear();
    }

    public void print() {
        System.out.println("______________");
        for (List<Cell> row : matrix) {
            System.out.print("|");
            for (Cell cell : row) {
                if (cell.getStatus() == CellStatus.EMPTY) {
                    System.out.print(" - |");
                } else if (cell.getStatus() == CellStatus.BLOCKED) {
                    System.out.print(" . |");
                } else {
                    System.out.print(" " + cell.getSymbol() + " |");
                }
            }
            System.out.println();
        }
        System.out.println("______________");
    }
}
