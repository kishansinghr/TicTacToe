package strategies.impl;

import enums.CellStatus;
import models.Board;
import models.Cell;
import models.Position;
import strategies.BotPlayingStrategy;

import java.util.List;
import java.util.Random;

public class BotPlayingStrategyRandom implements BotPlayingStrategy {

    private final Random random;

    public BotPlayingStrategyRandom() {
        random = new Random();
    }

    @Override
    public Position makeMove(Board board) {
        List<Cell> emptyCells = board.getMatrix().stream()
                .flatMap(List::stream)
                .filter(cell -> cell.getStatus() == CellStatus.EMPTY)
                .toList();

        return emptyCells.get(random.nextInt(emptyCells.size())).getPosition();
    }
}
