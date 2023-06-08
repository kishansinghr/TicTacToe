package enums;

import models.Player;
import models.Symbol;

public class BotPlayer extends Player {
    private BotDifficultyLevel difficultyLevel;

    public BotPlayer(BotDifficultyLevel difficultyLevel) {
        super("Bot", new Symbol('B'), PlayerType.BOT);
        this.difficultyLevel = difficultyLevel;
    }

    public BotDifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
