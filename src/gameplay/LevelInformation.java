
package gameplay;

import sprites.Velocity;
import sprites.Sprite;

import collidables.Block;

import java.util.List;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.6.2
 * Interface sets the information a game level must have and be able to return. */
public interface LevelInformation {

    /**
     * @return the number of balls the players has at the beginning of the level. */
    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    /**
     * @return the velocity of each ball at the beginning of the level. */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle in this level. */
    int paddleSpeed();

    /**
     * @return the width of the paddle in this level. */
    int paddleWidth();

    /**
     * @return the name of the level. */
    String levelName();

    /**
     * @return a sprite with the background of the level. */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level, each block contains, its size, color and location. */
    List<Block> blocks();

    /**
    * @return The number of blocks that need to be removed in order for the level
     * to be considered cleared. This number should be <= blocks.size() */
    int numberOfBlocksToRemove();
}
