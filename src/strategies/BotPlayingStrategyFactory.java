package strategies;

import enums.BotDifficultyLevel;
import strategies.impl.BotPlayingStrategyEasy;
import strategies.impl.BotPlayingStrategyHard;
import strategies.impl.BotPlayingStrategyRandom;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getInstance(BotDifficultyLevel difficultyLevel) {
        return switch (difficultyLevel) {
            case EASY -> new BotPlayingStrategyEasy();
            case MEDIUM -> new BotPlayingStrategyRandom();
            case HARD -> new BotPlayingStrategyHard();
        };
    }
}
