// ID: 205937949

import java.util.ArrayList;

/**
 * A class defines a line.
 * defines segment of line between two points
 * using point class, and has basics function
 * that check if two lines are intersecting, and if a given
 * point is on line and more.
 */
public class Line {
    // start point
    private Point start;
    // end point
    private Point end;

    /**
     * constructor of line that using two given points.
     *
     * @param start start point.
     * @param end   end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor of line using 2 x values and 2 y values.
     *
     * @param x1 x value of start point.
     * @param y1 y value of start point.
     * @param x2 x value of start point.
     * @param y2 y value of end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        // initiates new points for start and end
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * check length of a line.
     * using distance function of point class.
     *
     * @return length of line.
     */
    public double length() {
        Point p1 = this.start;
        Point p2 = this.end;
        return p1.distance(p2);
    }

    /**
     * check the middle point of a line.
     *
     * @return middle point of a line.
     */
    public Point middle() {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        // evaluate the average of the values and returns a new point
        return new Point((x1 + x2) / 2, (y1 + y2) / 2);
    }

    /**
     * returns the start point.
     *
     * @return start point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * returns the end point.
     *
     * @return end point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * check if a given point is in the line.
     *
     * @param p a point.
     * @return true if the point is in line, else returns false.
     */
    public boolean isInLine(Point p) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        // y = mx + b
        double m;
        if (x1 == x2) {
            // check if the point in the range of the line values
            return p.getX() == x1 && p.getY() <= Math.max(y1, y2) && Math.min(y1, y2) <= p.getY();
        }
        m = (y2 - y1) / (x2 - x1);
        double b = y1 - m * x1;
        // epsilon to prevent errors causes from the double form
        double epsilon = Math.pow(10, -8);
        // five conditions to determine if the point is in line
        boolean condition1 = Math.abs(p.getY() - (m * p.getX() + b)) < epsilon;
        boolean condition2 = p.getY() + epsilon <= Math.max(y1, y2) || p.getY() - epsilon <= Math.max(y1, y2);
        boolean condition3 = p.getX() + epsilon <= Math.max(x1, x2) || p.getX() - epsilon <= Math.max(x1, x2);
        boolean condition4 = Math.min(x1, x2) <= p.getX() + epsilon || Math.min(x1, x2) <= p.getX() - epsilon;
        boolean condition5 = Math.min(y1, y2) <= p.getY() + epsilon || Math.min(y1, y2) <= p.getY() - epsilon;
        return condition1 && condition2 && condition3 && condition4 && condition5;
    }

    /**
     * check if two lines are intersecting in one and only one point.
     *
     * @param other other line.
     * @return true if they intersect, else returns false.
     */
    public boolean isIntersecting(Line other) {
        // if two lines are equals they are not intersecting
        if (this.equals(other)) {
            return false;
        }
        Point p = this.intersectionWith(other);
        // if they are not intersecting
        if (p == null) {
            return false;
        }
        // if the point is in the two lines
        return this.isInLine(p) && other.isInLine(p);
    }

    /**
     * function checks if two lines are equals.
     *
     * @param other other line.
     * @return true if they are the same, else returns false.
     */
    public boolean equals(Line other) {
        return (this.start == other.start) && (this.end == other.end)
                || (this.end == other.start) && (this.start == other.end);
    }

    /**
     * check where is the intersection point of two lines.
     * using algebra and linear equations.
     *
     * @param other other line.
     * @return point of intersection.
     */
    public Point intersectionWith(Line other) {
        // this line start point
        double startX = this.start.getX();
        double startY = this.start.getY();
        // this line end point
        double endX = this.end.getX();
        double endY = this.end.getY();
        // other line start point
        double startXOther = other.start.getX();
        double startYOther = other.start.getY();
        // other line end point
        double endXOther = other.end.getX();
        double endYOther = other.end.getY();
       // if the 2 lines are vertical
        if (endX == startX && startXOther == endXOther) {
            // check if their edges are touching
            return this.isTouching(other);
        }
        // linear equations in the form y = mx + b
        double m1;
        double m2;
        double b1;
        double b2;
        double intersectionX;
        double intersectionY;
        // if this line is vertical
        if (startX == endX) {
            // if the edges of the lines are touching
            if (this.isTouching(other) != null) {
                return this.isTouching(other);
            }
            // using algebra to solve
            m2 = (endYOther - startYOther) / (endXOther - startXOther);
            b2 = startYOther - (m2 * startXOther);
            intersectionX = startX;
            intersectionY = m2 * startX + b2;
            Point intersectionPoint = new Point(intersectionX, intersectionY);
            // check if the intersection point is on the two lines
            if (this.isInLine(intersectionPoint) && other.isInLine(intersectionPoint)) {
                return intersectionPoint;
            }
            return null;
        }
        // if other line is vertical
        if (startXOther == endXOther) {
            // if the lines are touching the edges
            if (this.isTouching(other) != null) {
                return this.isTouching(other);
            }
            m1 = (endY - startY) / (endX - startX);
            b1 = startY - (m1 * startX);
            intersectionX = startXOther;
            intersectionY = m1 * startXOther + b1;
            Point intersectionPoint = new Point(intersectionX, intersectionY);
            if (this.isInLine(intersectionPoint) && other.isInLine(intersectionPoint)) {
                return intersectionPoint;
            }
            return null;
        }
        // line 1 as linear equation y1 = m1*x1 + b1
        m1 = (endY - startY) / (endX - startX);
        b1 = startY - (m1 * startX);
        // line 2 as linear equation y2 = m2*x2 + b2
        m2 = (endYOther - startYOther) / (endXOther - startXOther);
        b2 = startYOther - (m2 * startXOther);
        // if lines are parallel
        if (m1 == m2) {
            return this.isTouching(other);
        }
        intersectionX = (b2 - b1) / (m1 - m2);
        intersectionY = m1 * intersectionX + b1;
        Point intersectionPoint = new Point(intersectionX, intersectionY);
        if (this.isInLine(intersectionPoint) && other.isInLine(intersectionPoint)) {
            return intersectionPoint;
        }
        return null;
    }

    /**
     * check if the lines have touching edges.
     * and if they are touching function returns the edge point.
     * if they dont touch in one edge returns null.
     *
     * @param other other line.
     * @return point that they are touching there.
     */
    public Point isTouching(Line other) {
        // if the lines are the same line
        if (this.equals(other)) {
            return null;
        }
        if (this.start == other.start) {
            return this.start;
        }
        if (this.start == other.end) {
            return this.start;
        }
        if (this.end == other.start) {
            return this.end;
        }
        if (this.end == other.end) {
            return this.end;
        }
        return null;
    }

    /**
     * Closest intersection to start of line point.
     * method gets a rectangle a return the closest (to start point) intersection point with the line
     *
     * @param rect the rectangle
     * @return point the intersection point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line upper = rect.upperLine();
        Line right = rect.rightLine();
        Line lower = rect.lowerLine();
        Line left = rect.leftLine();
        // if line doesnt intersect with the rectangle
        if (!this.isIntersecting(upper) && !this.isIntersecting(right)
                && !this.isIntersecting(lower) && !this.isIntersecting(left)) {
            return null;
        }
        // if line intersects
        Line[] lines = {upper, right, lower, left};
        // list for intersections point
        ArrayList<Point> intersections = new ArrayList<Point>();
        // list for distances
        ArrayList distances = new ArrayList();
        for (int i = 0; i < lines.length; i++) {
            if (this.isIntersecting(lines[i])) {
                Point intersectionPoint = this.intersectionWith(lines[i]);
                int distance = (int) this.start.distance(intersectionPoint);
                intersections.add(intersectionPoint);
                distances.add(distance);
            }
        }
        // check what is the collision point with the smallest distance to start of line
        int index = 0;
        int min = (int) distances.get(0);
        for (int i = 1; i < distances.size(); i++) {
            if ((int) distances.get(i) < min) {
                min = (int) distances.get(i);
                index = i;
            }
        }
        return intersections.get(index);
    }
}