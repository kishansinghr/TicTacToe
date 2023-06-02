package models;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int size;
    private List<List<Symbol>> matrix;

    public Board(int size) {
        this.size = size;
        matrix = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Symbol> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                row.add(null);
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

    public List<List<Symbol>> getMatrix() {
        return matrix;
    }

    public void setMatrix(List<List<Symbol>> matrix) {
        this.matrix = matrix;
    }

    public void makeMove(Cell cell, Symbol symbol) {
        this.matrix.get(cell.getRow())
                .set(cell.getColumn(), symbol);
    }

    public void undoMove(Cell cell) {
        this.matrix.get(cell.getRow())
                .set(cell.getColumn(), null);
    }

    public void print() {
        System.out.println("______________");
        for (List<Symbol> row : matrix) {
            System.out.print("|");
            for (Symbol symbol : row) {
                if (symbol == null) {
                    System.out.print(" - |");
                } else {
                    System.out.print(" " + symbol.getCharacter() + " |");
                }
            }
            System.out.println();
        }
        System.out.println("______________");
    }
}
