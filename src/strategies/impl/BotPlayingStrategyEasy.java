package strategies.impl;

import enums.CellStatus;
import models.Board;
import models.Cell;
import models.Position;
import strategies.BotPlayingStrategy;

import java.util.List;
import java.util.Optional;

public class BotPlayingStrategyEasy implements BotPlayingStrategy {
    @Override
    public Position makeMove(Board board) {
        Optional<Cell> emptyCell = board.getMatrix().stream()
                .flatMap(List::stream)
                .filter(cell -> cell.getStatus() == CellStatus.EMPTY)
                .findFirst();

        if (emptyCell.isPresent())
            return emptyCell.get().getPosition();
        else
            return null;
    }
}
