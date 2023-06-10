package strategies;

import models.Board;
import models.Cell;
import models.Position;

public interface BotPlayingStrategy {
    Position makeMove(Board board);
}
