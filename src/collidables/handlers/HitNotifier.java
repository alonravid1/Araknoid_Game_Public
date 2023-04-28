package collidables.handlers;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.5.1
 * Interface enables the Block class to use only one function to handle it being hit by a ball, by
 * notifying one of the two implementing classes which are held in the Block's HitListener collection field. */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl listener to be added */
    void addHitListener(HitListener hl);

    /**
     * Remove hl as a listener to hit events.
     * @param hl listener to be removed */
    void removeHitListener(HitListener hl);
}
