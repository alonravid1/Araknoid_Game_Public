package sprites;

import shapes.Point;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.2.3.2
 * class specifies the change in position on the `x` and the `y` axes with
 * each step of a ball within an animation. */
public class Velocity {
    private double dx;
    private double dy;

    // constructor

    /**
     * constructs a new velocity using differance in coordinates per step.
     * @param dx difference in x value added on any step
     * @param dy difference in y value added on any step */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * constructs a new velocity using a movement angle with 0 being straight up, and speed.
     * The GUI package uses a matrix axis, where it's base vectors are (1,0), (0,-1) in
     * regular axis base.
     * The Math package uses the regular unit circle, and so by going -90 degrees on a given angle
     * we actually get for the angle 0 degrees and speed 1, dx = 0 and dy = -1, which
     * compensates for the y axis inversion of the GUI package.
     * For the angle 90 degrees and speed 1, dx = 1 and dy = 0, which will move
     * us on the x axis as specified on task description.
     * @param angle of movement
     * @param speed of movement
     * @return new velocity with the differance of coordinates derived from given angle and speed */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle - 90); // set the 0 angle to be straight up and turn degrees to radians
        double dx;
        double dy;
        dx = speed * Math.cos(angle); // since the GUI package uses a base where b2 = -[0,1],
        dy = speed * Math.sin(angle);

        return new Velocity(dx, dy);
    }


    /**
     * Takes a given velocity and a required change in angle, extracts the velocity's
     * speed and applies the change in angle with the same speed and returns the velocity.
     * @param newAngle to be applied to the velocity
     * @param v current velocity
     * @return the new velocity */
    public static Velocity setAngularVelocity(double newAngle, Velocity v) {
        double speed = (new Point(0, 0).distance(new Point(v.getDx(), v.getDy())));
        return (Velocity.fromAngleAndSpeed(newAngle, speed));

    }

    /**
     * Method was created for a non-related check, it is kept here as it might be useful later on.
     * Takes a given velocity and a required change in angle, converts the velocity from its
     * coordinates representation to polar representation, applies the change in angle and
     * returns the velocity after converting it back to coordinates.
     * @param change to be applied to the velocity
     * @param v current velocity
     * @return the changed velocity */
    public static Velocity changeAngularVelocity(double change, Velocity v) {
        double speed = (new Point(0, 0).distance(new Point(v.getDx(), v.getDy())));
        System.out.println(speed);
        double angle = -Math.asin(v.getDy() / speed);
        angle = Math.toDegrees(angle);
        angle = angle + change + 90;
        return (Velocity.fromAngleAndSpeed(angle, speed));

    }

    //accessors

    /**
     * @return difference in x value with each step. */
    public double getDx() {
        return dx;
    }

    /**
     * @return difference in y value with each step. */
    public double getDy() {
        return dy;
    }

    //setters

    /**
     * set difference in x value with each step.
     * @param newDx the difference */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * set difference in y value with each step.
     * @param newDy the difference */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * @param p point with position (x,y)
     * @return a point with position (x+dx, y+dy) */
    public Point applyToPoint(Point p) {
       return new Point((p.getX() + this.dx), (p.getY() + this.dy));
    }

}
