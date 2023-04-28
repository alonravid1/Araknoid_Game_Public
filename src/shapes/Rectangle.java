package shapes;


import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;




/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.3.1
 * class is used to create a rectangle 2D plane.
 * <p>
 * Using the upper-left point, width and height this class
 * allows the program to describe a rectangle on a 2D grid. */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructs an instance using a point and two doubles.
     * @param upperLeft of the rectangle
     * @param width of the rectangle
     * @param height of the rectangle */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a list of points, fills it with all intersection points
     * of the rectangle with a given line.
     * @param line the given line.
     * @return a list of intersection points, or null if there are none */
    public List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<Point>();

        Point bottomLeft = new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height);

        Point upperRight = new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY());

        Point bottomRight = new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY() + this.height);

        Line check = new Line(this.upperLeft, upperRight);
        if (line.isIntersecting(check) && !line.isHorizontal()) {
            points.add(line.intersectionWith(check));
        }

        check = new Line(this.upperLeft, bottomLeft);
        if (line.isIntersecting(check) && !line.isVertical()) {
            points.add(line.intersectionWith(check));

        }
        check = new Line(bottomLeft, bottomRight);
        if (line.isIntersecting(check) && !line.isHorizontal()) {
            points.add(line.intersectionWith(check));

        }
        check = new Line(upperRight, bottomRight);
        if (line.isIntersecting(check) && !line.isVertical()) {
            points.add(line.intersectionWith(check));

        }

        return points;
    }

    /**
     * @return the rectangle's width */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the rectangle's height */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the rectangle's upper left point */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Sets the rectangle's width as a given width.
     * @param newWidth the given width */
    public void setWidth(double newWidth) {
        this.width = newWidth;
    }

    /**
     * Sets the rectangle's height as a given height.
     * @param newHeight the given height */
    public void setHeight(double newHeight) {
        this.height = newHeight;
    }

    /**
     * Sets the rectangle's upper-left corner point as a given point.
     * @param newUpperLeft the given point */
    public void setUpperLeft(Point newUpperLeft) {
        this.upperLeft = newUpperLeft;
    }

    /**
     * Draws the rectangle with a given color on a given draw surface.
     * @param d the given draw surface
     * @param color given color */
    public void drawOn(DrawSurface d, Color color) {
        d.setColor(color);
        d.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);

    }

    /**
     * Draws the rectangle with a default color of black on a given draw surface.
     * @param d the given draw surface */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);

    }

}
