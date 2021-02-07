// ID: 205937949

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * class that defines a ball.
 * also class has several methods of ball like generate random ball
 * and move ball one step according to its velocity.
 */
public class Ball implements Sprite {
    // center point of the ball
    private Point center;
    // radius of the ball
    private int r;
    // color of the ball
    private java.awt.Color color;
    // velocity of the ball
    private Velocity v;
    // game environment
    private GameEnvironment gE;

    /**
     * constructor of ball object.
     *
     * @param center center point.
     * @param r      radius
     * @param color  color
     * @param gE     game environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gE) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.gE = gE;
    }

    /**
     * constructor of ball object.
     *
     * @param x     x value of center point
     * @param y     y value of center point
     * @param r     radius
     * @param color color
     * @param gE    game environment
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment gE) {
        // create new point object (for center point)
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.gE = gE;
    }

    /**
     * function returns Game Environment of the ball.
     *
     * @return gE GameEnvironment of the ball
     */
    public GameEnvironment getGe() {
        return this.gE;
    }

    /**
     * function returns x value of center point.
     *
     * @return x value
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * function returns y value of center point.
     *
     * @return y value
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * function returns radius of the ball.
     *
     * @return r radius
     */
    public int getSize() {
        return this.r;
    }

    /**
     * function returns color of the ball.
     *
     * @return color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * function sets color to the ball.
     *
     * @param givenColor color to the ball
     */
    public void setColor(java.awt.Color givenColor) {
        this.color = givenColor;
    }

    /**
     * method draws the ball on a given draw surface.
     * @param surface given surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        // set color to draw the ball
        surface.setColor(this.color);
        // draws the ball
        surface.fillCircle(this.getX(), this.getY(), this.r);
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * method tells the ball that time passed.
     * every 60 milliseconds the ball need to recalculate its
     * trajectory and this method does it
     */
    public void timePassed() {
        Point before = this.center;
        Point after = this.v.applyToPoint(this.center);
        // excpected trajectory of the ball
        Line trajectory = new Line(before, after);
        // if there is no collisions on the way
        if (this.gE.getClosestCollision(trajectory) == null) {
            // move the ball to the end of trajectory
            this.center = after;
            return;
        }
        // if there is collision
        CollisionInfo collisionInfo = this.gE.getClosestCollision(trajectory);
        // collision point
        Point collisionPoint = collisionInfo.collisionPoint();
        // collision object
        Collidable collisionObject = collisionInfo.collisionObject();
        // velocity after hit is
        this.v = collisionObject.hit(this, collisionPoint, this.v);
        // check the trajectory after the collision
        Point startOfFutureTrajectory = collisionPoint;
        // separate the collision object to four lines
        Line upper = collisionObject.getCollisionRectangle().upperLine();
        Line lower = collisionObject.getCollisionRectangle().lowerLine();
        Line left = collisionObject.getCollisionRectangle().leftLine();
        Line right = collisionObject.getCollisionRectangle().rightLine();
        /* moves the start of trajectory after the hit 1 pixel from the collision point
        to check another collisions on the way */
        if (upper.isInLine(collisionPoint)) {
            startOfFutureTrajectory.setY(this.center.getY() - 1);
        }
        if (lower.isInLine(collisionPoint)) {
            startOfFutureTrajectory.setY(this.center.getY() + 1);
        }
        if (left.isInLine(collisionPoint)) {
            startOfFutureTrajectory.setX(this.center.getX() - 1);
        }
        if (right.isInLine(collisionPoint)) {
            startOfFutureTrajectory.setX(this.center.getX() + 1);
        }
        Point endOfFutureTrajectory = this.v.applyToPoint(startOfFutureTrajectory);
        // the trajectory right after the collision
        Line futureTrajectory = new Line(startOfFutureTrajectory, endOfFutureTrajectory);
        // if there are more intersections on the way
        if (this.gE.getClosestCollision(futureTrajectory) == null) {
            this.center = this.v.applyToPoint(this.center);
            return;
        }
        CollisionInfo collisionInfo1 = this.gE.getClosestCollision(futureTrajectory);
        // collision point
        Point collisionPoint1 = collisionInfo1.collisionPoint();
        // collision object
        Collidable collisionObject1 = collisionInfo1.collisionObject();
        // velocity after hit is
        this.v = collisionObject1.hit(this, collisionPoint1, this.v);
        // new center location is
        this.center = this.v.applyToPoint(startOfFutureTrajectory);
    }
    /**
     * method adds the ball to a game.
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * method sets velocity to the ball.
     * method sets velocity using a given velocity object
     *
     * @param velocity new velocity for the ball
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * method sets velocity to the ball.
     * method sets velocity using a given delta x and delta y
     *
     * @param dx delta x
     * @param dy delta y
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * function returns velocity of a ball.
     *
     * @return velocity of the ball
     */
    public Velocity getVelocity() {
        return this.v;
    }


    /**
     * method moves the ball one step.
     * checks its trajectory and looking for collision points
     */
    public void moveOneStep() {
        Point before = this.center;
        Point after = this.v.applyToPoint(this.center);
        Line trajectory = new Line(before, after);
        if (this.gE.getClosestCollision(trajectory) == null) {
            this.center = after;
            return;
        }
        CollisionInfo collisionInfo = this.gE.getClosestCollision(trajectory);
        // collision point
        Point collisionPoint = collisionInfo.collisionPoint();
        // collision object
        Collidable collisionObject = collisionInfo.collisionObject();
        // velocity after hit is
        this.v = collisionObject.hit(this, collisionPoint, this.v);
        // new center location is
        this.center = this.v.applyToPoint(this.center);
    }

    /**
     * method generates a color using switch statements.
     *
     * @param i int to determine color
     * @return color to ball
     */
    public static java.awt.Color generateColor(int i) {
        switch (i) {
            case 12:
                // red
                return new Color(255, 0, 0);
            case 11:
                // orange
                return new Color(255, 127, 0);
            case 10:
                // yellow
                return new Color(255, 255, 0);
            case 9:
                // green
                return new Color(0, 255, 0);
            case 8:
                // blue
                return new Color(0, 0, 255);
            case 7:
                // purple
                return new Color(75, 0, 130);
            default:
                return Color.black;
        }
    }

    /**
     * Remove from game.
     * remove the ball from game
     *
     * @param g the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}