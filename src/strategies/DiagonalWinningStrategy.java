package strategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.Objects;

public class DiagonalWinningStrategy implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Move move) {
        Symbol symbol = move.getPlayer().getSymbol();

        boolean won;
        if (move.getRow() == move.getColumn()) { //upper left diagonal
            won = true;
            for (int i = 0; i < board.getSize(); i++) {
                if (!Objects.equals(symbol, board.getMatrix().get(i).get(i).getSymbol())) {
                    won = false;
                }
            }
            if(won) return true;
        }
        if (move.getRow() + move.getColumn() == board.getSize() - 1) { // Upper right diagonal
            won = true;
            for (int i = 0; i < board.getSize(); i++) {
                if (!Objects.equals(symbol, board.getMatrix().get(i).get(board.getSize() - 1 - i).getSymbol())) {
                    won = false;
                }
            }
            return won;
        }
        return false;
    }
}
