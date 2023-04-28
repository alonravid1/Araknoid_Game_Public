
package collidables;

import shapes.Rectangle;
import shapes.Point;

import sprites.Velocity;
import sprites.Ball;


/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.3.1
 * Interface ensures the implementing object has a rectangle describing its collision borders
 * and can be notified that it is hit. */
public interface Collidable {

    /**
     * @return the "collision shape" of the object */
    Rectangle getCollisionRectangle();


    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * @param collisionPoint point of collision of ball with the object's shape
     * @param currentVelocity velocity of the colliding ball
     * @param hitter ball hitting the block
     * @return he new velocity expected after the hit */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
