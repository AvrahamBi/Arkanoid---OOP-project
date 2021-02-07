// ID: 205937949

/**
 * class that create velocity.
 * that defines by the delta
 * of the x axis and the delta of the y axis.
 */
public class Velocity {
    // delta of x axis in one step
    private double dx;
    // delta of y axis in one step
    private double dy;

    /**
     * constructor that construct velocity from x and y.
     *
     * @param dx delta in x axis.
     * @param dy delta in y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * defines velocity by angle and speed.
     *
     * @param angle angle of movement (in degrees).
     * @param speed speed of movement.
     * @return velocity object.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // delta in x axis
        double dx = Math.sin(Math.toRadians(angle));
        // delta in y axis, multiplication is to cause the angle 0 be upwards
        double dy = (-1) * Math.cos(Math.toRadians(angle));
        return new Velocity(speed * dx, speed * dy);
    }

    /**
     * make movement on a point.
     * Take a point with position (x,y) and return a new point
     * according to the dx and dy of the velocity.
     *
     * @param p point to be moved.
     * @return point new point after movement (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * sets dx value of velocity.
     *
     * @param dX new dx for velocity.
     */
    public void setDx(double dX) {
        this.dx = dX;
    }

    /**
     * sets dy value of velocity.
     *
     * @param dY new dy for velocity.
     */
    public void setDy(double dY) {
        this.dy = dY;
    }

    /**
     * gets dx value of velocity.
     *
     * @return dx of the velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * gets dy value of velocity.
     *
     * @return dy of the velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * defines velocity of a ball depends on its radius.
     * bigger ball is moving slower.
     *
     * @param r radius of a ball.
     * @return int that defines speed units.
     */
    public static int velocityBySizeOfBall(int r) {
        // balls with radius bigger than 50 have the same speed
        if (50 <= r) {
            return 3;
        }
        // balls with radius smaller than 2 have the same speed
        if (r <= 2) {
            return 85;
        }
        return (250 / r);
    }

    /**
     * Velocity by paddle region velocity.
     * change the angle of velocity according to the location of collision point on the paddle
     *
     * @param region the region of collision
     * @return the velocity after the collision
     */
    public Velocity velocityByPaddleRegion(int region) {
        double dX = (int) this.dx;
        double dY = (int) this.dy;
        // speed according to pythagorean theorem
        int speed = (int) Math.round(Math.sqrt((dX * dX) + (dY * dY)));
        if (region == 1) {
            return fromAngleAndSpeed(300, speed);
        }
        if (region == 2) {
            return fromAngleAndSpeed(330, speed);
        }
        if (region == 4) {
            return fromAngleAndSpeed(30, speed);
        }
        if (region == 5) {
            return fromAngleAndSpeed(60, speed);
        }
        return null;
    }
}