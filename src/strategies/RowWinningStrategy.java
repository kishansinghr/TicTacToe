package strategies;

import models.Board;
import models.Cell;
import models.Move;
import models.Symbol;

import java.util.List;
import java.util.Objects;

public class RowWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Move move) {
        List<Cell> row = board.getMatrix().get(move.getRow());
        Symbol symbol = move.getPlayer().getSymbol();
        for (Cell cell : row) {
            if (!Objects.equals(symbol, cell.getSymbol())) {
                return false;
            }
        }
        return true;
    }
}
