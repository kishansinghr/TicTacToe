package strategies.impl;

import models.Board;
import models.Cell;
import models.Move;
import models.Symbol;
import strategies.WinningStrategy;

import java.util.List;
import java.util.Objects;

public class ColumnWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Move move) {
        Symbol symbol = move.getPlayer().getSymbol();
        for (List<Cell> row : board.getMatrix()) {
            Symbol s = row.get(move.getColumn()).getSymbol();
            if (!Objects.equals(symbol, s)) {
                return false;
            }
        }
        return true;
    }
}
