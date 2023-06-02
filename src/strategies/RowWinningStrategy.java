package strategies;

import models.Board;
import models.Cell;
import models.Symbol;

import java.util.List;

public class RowWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Cell cell) {
        List<Symbol> row = board.getMatrix().get(cell.getRow());
        Symbol s = row.get(cell.getColumn());
        for (Symbol symbol : row) {
            if (symbol != s) {
                return false;
            }
        }
        return true;
    }
}
