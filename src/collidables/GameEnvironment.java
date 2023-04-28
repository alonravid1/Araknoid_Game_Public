
package collidables;

import java.util.ArrayList;
import java.util.List;

import shapes.Rectangle;
import shapes.Line;
import shapes.Point;

import collidables.handlers.CollisionInfo;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.3.1
 * class describes the current environment of the game via collidable objects and their
 * interactions.
 * <p>
 * The class describes the current environment by holding a list
 * of all collidable objects within it, as well as describing the movement of
 * collidable objects and their interactions by going over said list
 * and checking for intersection points on the moving object's trajectory. */
public class GameEnvironment {

    private List<Collidable> objects;

    /**
     * constructs an instance with a given list of collidable objects.
     * @param objects given list of collidable objects */
    public GameEnvironment(List<Collidable> objects) {
        this.objects = objects;

    }

    /**
     * constructs an instance with a new empty list of collidable objects. */
    public GameEnvironment() {
        this.objects = new ArrayList<Collidable>();

    }

    /**
     * @return the list of objects in the game environment */
    public List<Collidable> getObjects() {
        return objects;
    }

    /**
     * Add the given collidable to the environment.
     * @param c given collidable object */
    public void addCollidable(Collidable c) {
        this.objects.add(c);

    }
    /**
     * remove the given collidable from the environment.
     * @param c given collidable object */
    public void removeCollidable(Collidable c) {
        this.objects.remove(c);

    }

    /**
     * If a ball is detected within a paddle then it means the player has moved the paddle on it,
     * in this case the ball will spawn at an arbitrary point in the bottom of the screen.
     * @param point center of the ball
     * @param rectangle the collision shape of current object in game environment
     * @return whether or not the ball is within the paddle */
    private boolean isWithinPaddle(Point point, Rectangle rectangle) {
        if (point.getX() > rectangle.getUpperLeft().getX()
                && point.getX() < rectangle.getUpperLeft().getX() + rectangle.getWidth()) {
            return (point.getY() > rectangle.getUpperLeft().getY()
                    && point.getY() < rectangle.getUpperLeft().getY() + rectangle.getHeight());
        }
        return false;
    }

    /**
     * Goes through every collidable object currently within the game, checks whether
     * there is an intersection point with the given trajectory of the ball, saves the
     * collision point and object in collision if its distance is closer to the start point.
     * @param trajectory of the ball, described by a line
     * @return a CollisionInfo instance with the closest collision point to the start
     * of the line and its object */
    public CollisionInfo getClosestCollision(Line trajectory) {
        boolean first = true;

        if (this.objects.size() == 0) {
            return null;
        }

        CollisionInfo collision = new CollisionInfo();
        List<Collidable> tempObjects = new ArrayList<Collidable>(this.objects);

        for (Collidable object : tempObjects) {
            if (isWithinPaddle(trajectory.start(), object.getCollisionRectangle())) {
                return new CollisionInfo(object, new Point(450, 500));
            }
            Point temp = trajectory.closestIntersectionToStartOfLine(object.getCollisionRectangle());
            if (temp != null) {
                if (first) {
                    collision = new CollisionInfo(object, temp);
                    first = false;

                } else if (trajectory.start().distance(temp)
                        < trajectory.start().distance(collision.collisionPoint())) {
                    collision = new CollisionInfo(object, temp);
                }
            }
        }

        return collision;


    }
}
