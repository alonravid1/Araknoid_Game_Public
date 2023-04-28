package sprites;

import biuoop.DrawSurface;
import gameplay.GameLevel;
import shapes.Line;
import shapes.Point;
import collidables.GameEnvironment;
import collidables.handlers.CollisionInfo;
import java.awt.Color;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.2.3.1
 * class represents a ball on a 2D plane.
 * <p>
 * Using a center point, a circle's radius and a color this class
 * allows the program to describe a ball on a 2D grid,
 * using a filled circle with the given arguments. */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private Color color;
    private Velocity v;
    private GameEnvironment game;

    // constructor
    /**
     * Constructs an instance using a center point, radius and color.
     * @param center point of the ball
     * @param r radius(size) of the ball
     * @param color of the ball */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Constructs an instance using a center point, radius, color and velocity.
     * @param center point of the ball
     * @param r radius(size) of the ball
     * @param color of the ball
     * @param v velocity of the ball */
    public Ball(Point center, int r, Color color, Velocity v) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = v;
    }
    /**
     * Constructs an instance using a coordinate on a 2d grid, radius and color.
     * @param x coordinate of the ball's center point
     * @param y coordinate of the ball's center point
     * @param r radius(size) of the ball
     * @param color of the ball */
    public Ball(double x, double y, int r, Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }




    // accessors
    /**
     * @return x value of ball's center point. */
    public double getX() {
        return this.center.getX();
    }
    /**
     * @return y value of ball's center point. */
    public double getY() {
        return  this.center.getY();
    }

    /**
     * @return the ball's center point. */
    public Point getCenter() {
        return center;
    }

    /**
     * @return ball's radius. */
    public int getSize() {
        return this.r;
    }
    /**
     * @return ball's color. */
    public java.awt.Color getColor() {
        return this.color;
    }

    //setters
    /**
     * Sets the ball's center point to the given
     * new point.
     * @param newCenter to replace current center */
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }

    /**
     * Sets the ball's radius to the given new size.
     * @param newSize  to replace current radius */
    public void setSize(int newSize) {
        this.r = newSize;
    }

    /**
     * Sets the ball's color to the given new color.
     * @param newColor to replace current color */
    public void setColor(java.awt.Color newColor) {
        this.color = newColor;
    }

    /**
     * Sets the ball's velocity to the given new velocity.
     * @param newVelocity to replace current velocity */
    public void setVelocity(Velocity newVelocity) {
        this.v = newVelocity;
    }


    /**
     * Sets the ball's game environment to the given new game environment.
     * @param newGame given game environment */
    public void setGame(GameEnvironment newGame) {
        this.game = newGame;
    }



    /**
     * Draws the instance of a ball using its center point,
     * radius and color on a given draw surface.
     * @param surface draw surface with which the ball is drawn. */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.getX(), (int) this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.getX(), (int) this.getY(), this.getSize());

    }

    /**
     * @return ball's current velocity */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Function is called when a collision is detected,
     * moves the ball to a point just slightly before the collision point.
     * @param collisionPoint the point of collision, which the ball moves close to */
    public void squeezeMove(Point collisionPoint) {
        Velocity almostHit = new Velocity(this.getVelocity().getDx(), this.getVelocity().getDy());
        almostHit.setDx(-almostHit.getDx() * 4 / 5);
        almostHit.setDy(-almostHit.getDy() * 4 / 5);
        this.center = almostHit.applyToPoint(collisionPoint);
    }



    /**
     * Moves the ball one step according to it's velocity within a given frame.
     * due to the frame limit the ball reverses its direction one step before it
     * actually collides, complying with question 255 on piazza. */
    public void moveOneStep() {
        //this point has the velocity applied to it, and is used to then check
        //whether the ball left frame boundaries
        Point check = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.getCenter(), check);

        CollisionInfo collision = this.game.getClosestCollision(trajectory);
        if (collision != null) {
            if (collision.collisionObject() != null) {
                squeezeMove(collision.collisionPoint());
                this.setVelocity(collision.collisionObject().hit(this, collision.collisionPoint(),
                        this.getVelocity()));
            }
        }
        this.center = (this.getVelocity().applyToPoint(this.center));



    }


    /**
     * Notifies the ball that a measurement of time has
     * passed within the game, and it needs to move a step. */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Add a ball to the game as a Sprite.
     * @param g current game */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        this.game = g.getEnvironment();

    }

    /**
     * Remove a ball from the game.
     * @param g current game */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /*

    methods which were used in previous assignments or seemed useful
    at the time, currently their utility is in question but i keep them
    here in case they will be useful and to keep the code cleaner
    import java.util.Random;

    /**
     * Constructs an instance using using a coordinate on a 2d grid, radius, color and velocity.
     * @param x coordinate of the ball's center point
     * @param y coordinate of the ball's center point
     * @param r radius(size) of the ball
     * @param color of the ball
     * @param v velocity of the ball
    public Ball(double x, double y, int r, Color color, Velocity v) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.v = v;
    }

     /**
     * Sets the ball's velocity to the given new velocity.
     * @param dx difference in x value added on any step
     * @param dy  difference in y value added on any step
    public void setVelocity(double dx, double dy) {
        if (this.v == null) {
            this.v = new Velocity(dx, dy);
            return;
        }
        this.v.setDx(dx);
        this.v.setDy(dy);

    }
    /**
     * Generates a center point for a ball within a given frame's width and height constraints.
     * @param borders of reference for the ball
     * @param r ball's size
     * @return a new center point for a ball within the frame while making sure its
     * size doesn't exceed the given constraints
    public static Point generateRandomCenter(Rectangle borders, int r) {
        Random rand = new Random();
        Point reference = borders.getUpperLeft();

        // get integer in range r-(height-r)
        double x1 = rand.nextInt((int) borders.getWidth() - 2 * r - 1)
                + r + (int) reference.getX() + 1;

        // get integer in range r-(height-r)
        double y1 = rand.nextInt((int) borders.getHeight() - 2 * r - 1)
                + r + (int) reference.getY() + 1;

        return new Point(x1, y1);

    }


    /**
     * Generates a velocity with a random direction and
     * with random speed proportional to the ball's size to
     * prevent jumping animation.
     * The speed is randomized within a small range in order to
     * ensure that each ball starts with a different speed as requested.
    public void generateProportionalVelocity() {
        Random rand = new Random();
        double angle = rand.nextInt(360) + 1; // get integer in range 1-360
        double speed;
        if (this.r >= 50) {
            speed = 1;
        } else {
            speed = rand.nextInt(4) + 1; // get integer in range 1-5
            speed = speed * (30 / ((double) this.r));
        }
        setVelocity(Velocity.fromAngleAndSpeed(angle, speed));

    }

     */
}
