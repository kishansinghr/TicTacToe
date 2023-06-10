package strategies.impl;

import enums.CellStatus;
import models.Board;
import models.Cell;
import models.Position;
import strategies.BotPlayingStrategy;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class BotPlayingStrategyRandom implements BotPlayingStrategy {

    private Random random;

    public BotPlayingStrategyRandom() {
        random = new Random();
    }

    @Override
    public Position makeMove(Board board) {
        List<Cell> emptyCells = board.getMatrix().stream()
                .flatMap(List::stream)
                .filter(cell -> cell.getStatus() == CellStatus.EMPTY)
                .collect(Collectors.toList());

        return emptyCells.get(random.nextInt(emptyCells.size())).getPosition();
    }
}
