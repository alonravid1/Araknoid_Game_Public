package shapes;

import java.util.Random;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.2.1
 * class represents a point on a 2D plane
 * <p>
 * Using an X value and a Y value this class
 * allows the program to describe a point on a 2D grid,
 * to check it's distance to other points
 * and to check if it is the same
 * as another point on the grid. */
public class Point {
    private double x;
    private double y;
    // constructors
    /**
     * constructor.
     * @param x coordinate
     * @param y coordinate */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other the point to which the distance is calculated
     * @return the distance of this point to the other point */
    public double distance(Point other) {
        return (Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2)));
    }

    /**
     * @param other the point checked against
     * @return true is the points are equal, false otherwise */
    public boolean equals(Point other) {
        if (other != null) {
            return this.x == other.x && this.y == other.y;
        }
        return false;
    }

    //getters
    /**
     * @return the x value of this point */
    public double getX() {
        return this.x;

    }
    /**
     * @return the y value of this point */
    public double getY() {
        return this.y;
    }

    //setters
    /**
     * sets the point's x value to a.
     * @param a value to insert into x
     */
    public void setX(double a) {
        this.x = a;
    }

    /**
     * sets the point's y value to a.
     * @param a value to insert into y
     */
    public void setY(double a) {
        this.y = a;
    }

    /**
     * generate random point within a given plane.
     * @param width of the plane, sets maximum x value
     * @param height of the plane, sets maximum y value
     * @return a point with random coordinates within the given plane. */
    public static Point generateRandomPoint(int width, int height) {
        Random rand = new Random();
        double x1 = rand.nextInt(width) + 1; // get integer in range 1-width
        double y1 = rand.nextInt(height) + 1; // get integer in range 1-height
        return new Point(x1, y1);
    }
}

