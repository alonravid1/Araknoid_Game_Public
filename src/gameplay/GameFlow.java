
package gameplay;


import biuoop.KeyboardSensor;
import gameplay.animation.AnimationRunner;

import gameplay.animation.KeyPressStoppableAnimation;

import java.util.List;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.6.2
 * Class keeps the game score across levels, and initialize them one at a time, giving them to the animation runner
 * and exits the program when the game is over and the player presses the space button. */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter currentScore;
    private boolean victory;

    /**
     * Constructor.
     * @param ar animation runner
     * @param ks keyboard sensor */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.currentScore = new Counter();
        this.victory = true;

    }

    /**
     * Initialize Game levels according to the levels list, one at a time, until the player either clears all of them
     * or lose all lives.
     * @param levels which were set according to user input in the main function */
    public void runLevels(List<LevelInformation> levels) {
        // ...
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, currentScore);

            level.initialize();

            while (level.getBallCounter().getValue() > 0 && level.getBlockCounter().getValue() > 0) {
                level.run();
            }

            if (level.getBallCounter().getValue() == 0) {
                this.victory = false;
                break;
            }

        }

        KeyPressStoppableAnimation end = new KeyPressStoppableAnimation(this.keyboardSensor, keyboardSensor.SPACE_KEY,
                new EndScreen(this.currentScore.getValue(), this.victory));

        this.animationRunner.run(end);
        System.exit(0);

    }
}
