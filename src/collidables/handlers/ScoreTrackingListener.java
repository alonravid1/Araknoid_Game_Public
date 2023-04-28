
package collidables.handlers;

import collidables.Block;

import sprites.Ball;

import gameplay.Counter;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.5.3
 * Class enables the game to update the score counter whenever a regular block is removed. */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter the game's score counter which stores the current score. */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Function is called when a regular block within the game is hit. The player receives 5 points
     * for each block removed by them, and the function updates the score accordingly.
     * @param beingHit the block being hit
     * @param hitter the hitting ball */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}