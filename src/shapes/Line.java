package shapes;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.2.1
 * class represents a line-segment on a 2D plane
 * <p>
 * Using a starting point and an end point this class
 * allows the program to describe a line-segment on a 2D grid,
 * to check it's length and to check if it intersects
 * with other lines on the grid. */
public class Line {
    private Point start;
    private Point end;

    // constructors
    /**
     * constructor.
     * @param start the coordinates of the line's starting point
     * @param end the coordinates of the line's end point */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     * @param x1 the line's starting point's x coordinates
     * @param y1 the line's starting point's y coordinates
     * @param x2 the line's end point's x coordinates
     * @param y2 the line's end point's y coordinates */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the distance between the line's starting point and
     * it's end point. */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return the point located in the middle of the segment. */
    public Point middle() {
        return (new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2));

    }

    //accessors
    /**
     * @return the start point of the line */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line */
    public Point end() {
        return this.end;
    }

    //setters
    /**
     *sets the line's starting point to the given
     * new point.
     * @param newStart the given point */
    public void setStart(Point newStart) {
        this.start = newStart;
    }

    /**
     *sets the line's end point to the given
     * new point.
     * @param newEnd the given point */
    public void setEnd(Point newEnd) {
        this.end = newEnd;
    }

    /**
     * @return the slope of the of the Line */
    public double slope() {
        double numerator = this.start.getY() - this.end.getY();
        double denominator = this.start.getX() - this.end.getX();
        if (denominator == 0) {
            return 0;
        }
        return numerator / denominator;
    }

    /**
     * @return the constant of the line equation */
    public double getConst() {
        return (this.start.getY() - this.slope() * this.start.getX());
    }

    /**
     * @return whether or not the line is vertical, i.e. parallel to the Y axis */
    public boolean isVertical() {
        return (this.start.getX() == this.end.getX());
    }
    /**
     * @return whether or not the line is horizontal, i.e. parallel to the X axis */
    public boolean isHorizontal() {
        return (this.start.getY() == this.end.getY());
    }
    /**
     * @return whether or not the line is a single point */
    private boolean isPoint() {
        return (this.start.equals(this.end));
    }

    /**
     * the function is called only when a point is derived from the general
     * line's equation.
     * @param point the point checked against
     * @return true if a point on a line is within the line-segment, false if not */
    public boolean pointInSegment(Point point) {
        if (this.isHorizontal()) {
            return ((point.getX() >= Math.min(this.start.getX(), this.end.getX()))
                    && (point.getX() <= Math.max(this.start.getX(), this.end.getX())));
        }

        //make sure that the point is within the segment's maximum and minimum y and x values
        if (point.getY() >= Math.min(this.start.getY(), this.end.getY())
                && point.getY() <= Math.max(this.start.getY(), this.end.getY())) {

            return ((point.getX() >= Math.min(this.start.getX(), this.end.getX()))
                    && (point.getX() <= Math.max(this.start.getX(), this.end.getX())));

        }
        return false;

    }

    /**
     * The function checks whether two line-segments are
     * intersecting.
     * @param other is the other segment against which intersection is checked.
     * @return returns true if the segments intersect, false otherwise. */
    public boolean isIntersecting(Line other) {
        double m1 = this.slope();
        double m2 = other.slope();
        double b1 = this.getConst();
        double b2 = other.getConst();
        double x1;
        double y1;

        if (this.isPoint() || other.isPoint()) {
            return false;
        }

        if (m1 == m2 && (this.isVertical() && other.isVertical())) {
            //compare slopes, if the lines are parallel then the lines have a single intersection point
            //only if there are 2 edge points whose distance equals to both lines length.
            if (this.start.distance(other.end()) == this.length() + other.length()) {
                return true;
            } else if (this.end.distance(other.end()) == this.length() + other.length()) {
                return true;
            } else if (this.start.distance(other.start()) == this.length() + other.length()) {
                return true;
            }
            return this.end.distance(other.start()) == this.length() + other.length();
        }


        if (this.isVertical()) {
            x1 = this.start.getX();
            y1 = m2 * x1 + b2;
        } else if (other.isVertical()) {
            x1 = other.start().getX();
            y1 = m1 * x1 + b1;
        } else {
            x1 = (b2 - b1) / (m1 - m2); //derived by comparing the two lines equations
            y1 = m1 * x1 + b1; // substituting x1 in first line's equation
        }
        Point temp = new Point(x1, y1);
        return (this.pointInSegment(temp) && other.pointInSegment(temp));

    }

    /**
     * The function checks whether or not the segments intersect
     * using the previously implemented method.
     * If the segments do intersect, using the line equation Y = (m * X) + b, where m is the slope
     * and b is some constant, we can derive that b = Y - (m * X).
     * By inserting each segment's starting point values and its slope we get a value
     * for b, and by comparing both line's equations we get that the intersection's
     * X coordinate equals (b1-b2)/(m1-m2).
     * @param other is the other segment against which intersection is checked.
     * @return Returns the intersection point if the lines intersect,
     * and null otherwise. */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        }

        double m1 = this.slope();
        double m2 = other.slope();
        double b1 = (this.start.getY() - this.slope() * this.start.getX());
        double b2 = (other.start.getY() - m2 * other.start.getX());

        if (this.isPoint() && other.isPoint()) {
            return this.start();
        }
        if (this.isPoint()) {
            return this.start;
        }
        if (other.isPoint()) {
            return other.start();
        }

        if (m1 == m2 && (this.isVertical() && other.isVertical())) {
            if (this.start.equals(other.end())) {
                return this.start;
            }
            if (this.start.equals(other.start())) {
                return this.start;
            }
            if (this.end.equals(other.end())) {
                return this.end;
            }
            if (this.end.equals(other.start())) {
                return this.end;
            }
        }


        Point interPoint = new Point(0, 0);

        if (this.isVertical()) {
            interPoint.setX(this.start.getX());
            interPoint.setY(m2 * interPoint.getX() + b2);
        } else if (other.isVertical()) {
            interPoint.setX(other.start().getX());
            interPoint.setY(m1 * interPoint.getX() + b1);
        } else if (this.isHorizontal()) {
            interPoint.setY(this.start.getY());
            interPoint.setX((this.start.getY() - other.getConst()) / m2);
        } else if (other.isHorizontal()) {
            interPoint.setY(other.start.getY());
            interPoint.setX((other.start.getY() - this.getConst()) / m1);
        } else {
            interPoint.setX((b2 - b1) / (m1 - m2));
            interPoint.setY(m1 * interPoint.getX() + b1);
        }
        return interPoint;
    }

    /**
     * @param other the line checked against
     * @return true if the lines are equal, false otherwise */
    public boolean equals(Line other) {
        if (this.start.equals(other.start())) {
            if (this.end.equals(other.end())) {
                return true;
            }
        }
        if (this.start.equals(other.end())) {
            if (this.end.equals(other.start())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param width of the drawing windows screen
     * @param height of the drawing windows screen
     * @return an instance of a Line with random start
     * and end points. */
    public static Line generateRandomLine(int width, int height) {
        return new Line(Point.generateRandomPoint(width, height) ,
                Point.generateRandomPoint(width, height));
    }

    /**
     * Find the intersection point closest to line's starting point
     * with a given rectangle.
     * @param rect the given rectangle
     * @return closest intersection point or null if none exist*/
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> points = rect.intersectionPoints(this);
        if (points.size() == 0) {
            return null;
        }
        Point min = points.get(0);
        for (Point x :points) {
            if (x.distance(this.start) < min.distance(this.start)) {
                min = x;
            }
        }
        return min;

    }

    /**
     * Draws the line with a default color of black on a given draw surface.
     * @param d the given draw surface */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) (this.start.getX()), (int) this.start.getY(),
                (int) this.end.getX(), (int) this.end.getY());
    }

    /**
     * Draws the line with a given color of on a given draw surface.
     * @param d the given draw surface
     * @param color given color */
    public void drawOn(DrawSurface d, Color color) {
        d.setColor(color);
        d.drawLine((int) (this.start.getX()), (int) this.start.getY(),
                (int) this.end.getX(), (int) this.end.getY());
    }

}