// ID: 205937949

import java.util.ArrayList;

/**
 * The type Rectangle.
 */
public class Rectangle {
    // upper left point
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left point
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * method put intersection points of line with rectangle into a list.
     *
     * @param line the line
     * @return list of intesection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> points = new ArrayList<Point>();
        Line upper = this.upperLine();
        Line right = this.rightLine();
        Line lower = this.lowerLine();
        Line left = this.leftLine();
        Line[] lines = {upper, right, lower, left};
        // iterates over the lines of the rectangle
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].isIntersecting(line)) {
                Point p = lines[i].intersectionWith(line);
                points.add(p);
            }
        }
        return points;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Sets upper left.
     *
     * @param upperLeftPoint the upper left point
     */
    public void setUpperLeft(Point upperLeftPoint) {
        this.upperLeft = upperLeftPoint;
    }

    /**
     * Upper line.
     * return upper line of rectangle
     *
     * @return upper line
     */
    public Line upperLine() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        return new Line(this.upperLeft, upperRight);
    }

    /**
     * right line.
     * return right line of rectangle
     *
     * @return right line
     */
    public Line rightLine() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerRight = new Point(upperRight.getX(), upperRight.getY() + this.height);
        return new Line(upperRight, lowerRight);
    }

    /**
     * lower line.
     * return lower line of rectangle
     *
     * @return lower line
     */
    public Line lowerLine() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerRight = new Point(upperRight.getX(), upperRight.getY() + this.height);
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return new Line(lowerRight, lowerLeft);
    }

    /**
     * left line.
     * return left line of rectangle
     *
     * @return left line
     */
    public Line leftLine() {
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return new Line(lowerLeft, this.upperLeft);
    }
}