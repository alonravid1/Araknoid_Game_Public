package collidables.handlers;

import gameplay.GameLevel;
import gameplay.Counter;

import sprites.Ball;

import collidables.Block;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.5.1
 * Class enables the death region block to remove balls from the GameLevel class's Sprite collection to remove it
 * from the gameLevel completely, without having a field pointing directly to the gameLevel. */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param gameLevel being played
     * @param remainingBalls in the gameLevel */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Function handles the event of a ball hitting a block. In the case of the BallRemover class
     * this function is called when a ball hits the bottom border which is the "death region".
     * It removes the hitting ball from the gameLevel and decreases the number of balls in the counters.
     * @param beingHit the block being hit
     * @param hitter the hitting ball */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.decrease(1);
    }
}
