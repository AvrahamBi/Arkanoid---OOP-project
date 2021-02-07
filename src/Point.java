// ID: 205937949

/**
 * A class that defines a point/
 * the class support basics function like instructors
 * check distance between points, if two points are equal
 * and more.
 */
public class Point {
    // x value
    private double x;
    // y value of the point
    private double y;

    /**
     * constructor returns new point from x and y.
     *
     * @param x x value of point.
     * @param y y value of point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * constructor returns new point initialized with (0, 0).
     */
    public Point() {
        this(0, 0);
    }

    /**
     * check distance between two points.
     * using pythagoras theorem.
     *
     * @param other point object
     * @return distance between two points
     */
    public double distance(Point other) {
        // pythagoras theorem
        double distance = Math.sqrt((other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y));
        return distance;
    }

    /**
     * check if two points are equal.
     *
     * @param other point object.
     * @return true if two points have the same values, else returns false.
     */
    public boolean equals(Point other) {
        return ((this.x == other.x) && (this.y == other.y));
    }

    /**
     * returns x value of point.
     *
     * @return x x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * returns y value of point.
     *
     * @return y y value.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets x.
     *
     * @param xVal the x val
     */
    public void setX(double xVal) {
        this.x = xVal;
    }

    /**
     * Sets y.
     *
     * @param yVal the y val
     */
    public void setY(double yVal) {
        this.y = yVal;
    }
}