

package collidables.handlers;

import shapes.Point;
import collidables.Collidable;
/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.3.1
 * class describes a collision of a moving object and a collidable object by storing
 * within it which object was collided and at which point. */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs an instance using given collidable object and a collision point.
     * @param collisionObject given object
     * @param collisionPoint given point */
    public CollisionInfo(Collidable collisionObject, Point collisionPoint) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Constructs an instance of CollisionInfo. */
    public CollisionInfo() {
    }

    /**
     * @return collision point */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return collision object */
    public Collidable collisionObject() {
        return this.collisionObject;

    }
}
