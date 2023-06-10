package models;

import enums.BotDifficultyLevel;
import enums.PlayerType;
import exceptions.MoveNotAllowedException;
import strategies.BotPlayingStrategy;
import strategies.BotPlayingStrategyFactory;

public class BotPlayer extends Player {
    private final BotDifficultyLevel difficultyLevel;
    private final BotPlayingStrategy playingStrategy;

    public BotPlayer(BotDifficultyLevel difficultyLevel) {
        super("Bot", new Symbol('B'), PlayerType.BOT);
        this.difficultyLevel = difficultyLevel;
        this.playingStrategy = BotPlayingStrategyFactory.getInstance(this.difficultyLevel);
    }

    public BotDifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    @Override
    public Move makeMove(Board board) throws MoveNotAllowedException {
        Position cell = this.playingStrategy.makeMove(board);
        if(cell == null) {
            throw new MoveNotAllowedException("No empty cell found");
        }

        return new Move(cell.row(), cell.col(), this);
    }
}
