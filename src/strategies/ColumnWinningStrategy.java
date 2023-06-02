package strategies;

import models.Board;
import models.Cell;
import models.Symbol;
import strategies.WinningStrategy;

import java.util.List;
import java.util.Objects;

public class ColumnWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Cell cell) {
        Symbol s = board.getMatrix().get(cell.getRow()).get(cell.getColumn());
        for (List<Symbol> row : board.getMatrix()) {
            Symbol symbol = row.get(cell.getColumn());
            if (Objects.equals(symbol, s)) {
                return false;
            }
        }
        return true;
    }
}
