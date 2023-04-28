
package collidables.handlers;

import collidables.Block;

import gameplay.GameLevel;
import sprites.Ball;

import gameplay.Counter;
/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.5.2
 * Class enables each block to remove itself from the GameLevel class's objects collections
 * and from the gameLevel itself without having a field pointing directly to the gameLevel. */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param gameLevel current gameLevel
     * @param remainingBlocks Counter of number of block in the gameLevel */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Function handles the event of a ball hitting a block. In the case of the BlockRemover class
     * this function is called when a ball hits a regular gameLevel block.
     * It removes the hit block from the gameLevel and decreases the number of block in the counters.
     * @param beingHit the block being hit
     * @param hitter the hitting ball */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);

    }
}
