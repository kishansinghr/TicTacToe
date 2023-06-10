package strategies.impl;

import models.Board;
import models.Position;
import strategies.BotPlayingStrategy;

public class BotPlayingStrategyHard implements BotPlayingStrategy {
    @Override
    public Position makeMove(Board board) {
        throw new RuntimeException("Hard strategy not implemented");
    }
}
