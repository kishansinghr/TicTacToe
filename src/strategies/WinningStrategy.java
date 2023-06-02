package strategies;

import models.Board;
import models.Cell;

public interface WinningStrategy {
    public boolean checkWinner(Board board, Cell cell);
}
