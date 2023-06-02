package strategies;

import models.Board;
import models.Cell;
import models.Symbol;

public class DiagonalWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Cell cell) {
        Symbol symbol = board.getMatrix().get(cell.getRow()).get(cell.getColumn());
        if (cell.getRow() == cell.getColumn()) { //upper left diagonal
            for (int i = 0; i < board.getSize(); i++) {
                if (symbol != board.getMatrix().get(i).get(i)) {
                    return false;
                }
            }
        } else if (cell.getRow() + cell.getColumn() == board.getSize() - 1) {
            for (int i = 0; i < board.getSize(); i++) {
                if (symbol != board.getMatrix().get(i).get(board.getSize() - 1 - i)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
