
package collidables.handlers;

import collidables.Block;

import sprites.Ball;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.5.1
 * Interface enables the Block class to use only one function to handle it being hit by a ball, by
 * notifying one of the two implementing classes which are held in the Block's HitListener collection field. */
public interface HitListener {
    /**
     * Function handles the event of a ball hitting a block depending on which listener called it.
     * @param beingHit the block being hit
     * @param hitter the hitting ball */
    void hitEvent(Block beingHit, Ball hitter);
}
