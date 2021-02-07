// ID: 205937949

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Paddle.
 * defines a paddle in the game
 */
public class Paddle implements Sprite, Collidable {
    // the shape of paddle
    private Rectangle paddleShape;
    // its color
    private Color color;
    // keyboard sensor to control the paddle
    private biuoop.KeyboardSensor keyboard;

    /**
     * Instantiates a new Paddle.
     *
     * @param keyboard the keyboard sensor
     * @param width    the width
     */
    public Paddle(biuoop.KeyboardSensor keyboard, int width) {
        Point p = new Point(400 - width / 2, 565);
        Rectangle r = new Rectangle(p, width, 10);
        this.paddleShape = r;
        this.color = Color.YELLOW;
        this.keyboard = keyboard;
    }

    /**
     * Move left.
     * when user hit the left button the paddle moves left
     */
    public void moveLeft() {
        Point p = this.paddleShape.getUpperLeft();
        p.setX(p.getX() - 10);
        this.paddleShape.setUpperLeft(p);
    }

    /**
     * Move right.
     * when user hit the right button the paddle moves right
     */
    public void moveRight() {
        Point p = this.paddleShape.getUpperLeft();
        p.setX(p.getX() + 10);
        this.paddleShape.setUpperLeft(p);
    }

    /**
     * tells the paddle that 60 milliseconds past.
     * method checks if user hit the left or right button
     */
    public void timePassed() {
        Point paddleUpperLeft = this.paddleShape.getUpperLeft();
        int paddleShapeWidth = (int) this.paddleShape.getWidth();
        if (this.keyboard.isPressed(this.keyboard.RIGHT_KEY) && paddleUpperLeft.getX() + paddleShapeWidth < 780) {
            this.moveRight();
        }
        if (this.keyboard.isPressed(this.keyboard.LEFT_KEY) && 20 < paddleUpperLeft.getX()) {
            this.moveLeft();
        }
    }

    /**
     * method draws the paddle on a given DrawSurface.
     * @param d the DrawSurface
     */
    public void drawOn(DrawSurface d) {
        int x = (int) paddleShape.getUpperLeft().getX();
        int y = (int) paddleShape.getUpperLeft().getY();
        int width = (int) paddleShape.getWidth();
        int height = (int) paddleShape.getHeight();
        d.setColor(this.color);
        d.fillRectangle(x, y, width, height);
        // the frame of the block
        d.setColor(Color.black);
        d.drawRectangle(x, y, width, height);

    }

    /**
     * returns the shape of paddle.
     * @return rectangle of Block
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleShape;
    }

    /**
     * method returns new velocity after hitting a paddle.
     * @param hitter the hitter ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity of the ball
     * @return velocity after collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Rectangle paddleRectangle = this.paddleShape;
        // separates the block to four lines
        Line upper = paddleRectangle.upperLine();
        Line right = paddleRectangle.rightLine();
        Line lower = paddleRectangle.lowerLine();
        Line left = paddleRectangle.leftLine();
        // if ball hit the upper line of paddle
        if (upper.isInLine(collisionPoint)) {
            // check in which region of paddle the ball hit
            int region = this.paddleRegionOfCollision(collisionPoint);
            // if ball hit in the middle of the paddle
            if (region == 3) {
                currentVelocity.setDy(currentVelocity.getDy() * -1);
                return currentVelocity;
            }
            // else
            currentVelocity = currentVelocity.velocityByPaddleRegion(region);
        }
        // if ball hit in the lower line
        if (lower.isInLine(collisionPoint)) {
            currentVelocity.setDy(currentVelocity.getDy() * -1);
        }
        if (right.isInLine(collisionPoint) || left.isInLine(collisionPoint)) {
            currentVelocity.setDx(currentVelocity.getDx() * -1);
        }
        return currentVelocity;
    }

    /**
     * Paddle region of collision int.
     * method return in int value in whick region the ball hit the paddle
     * 1 is the most leftmost, 5 is the rightmost
     *
     * @param p collision point
     * @return the region
     */
    public int paddleRegionOfCollision(Point p) {
        // the edges of the upper line
        Point upperLeft = this.paddleShape.getUpperLeft();
        Point upperRight = new Point(upperLeft.getX() + paddleShape.getWidth(), upperLeft.getY());
        // points between the regions
        int fifthOfPaddle = (int) (paddleShape.getWidth() / 5);
        Point pointBetween1To2 = new Point(upperLeft.getX() + fifthOfPaddle, upperLeft.getY());
        Point pointBetween2To3 = new Point(upperLeft.getX() + fifthOfPaddle * 2, upperLeft.getY());
        Point pointBetween3To4 = new Point(upperLeft.getX() + fifthOfPaddle * 3, upperLeft.getY());
        Point pointBetween4To5 = new Point(upperLeft.getX() + fifthOfPaddle * 4, upperLeft.getY());
        // the regions
        Line region1 = new Line(upperLeft, pointBetween1To2);
        Line region2 = new Line(pointBetween1To2, pointBetween2To3);
        Line region3 = new Line(pointBetween2To3, pointBetween3To4);
        Line region4 = new Line(pointBetween3To4, pointBetween4To5);
        Line region5 = new Line(pointBetween4To5, upperRight);
        if (region1.isInLine(p)) {
            return 1;
        }
        if (region2.isInLine(p)) {
            return 2;
        }
        if (region3.isInLine(p)) {
            return 3;
        }
        if (region4.isInLine(p)) {
            return 4;
        }
        if (region5.isInLine(p)) {
            return 5;
        }
        // if the point is not in the upper line
        return -1;
    }

    /**
     * method returns color.
     * @return color of the paddle
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * add the paddle to the game.
     * @param g Game object
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
