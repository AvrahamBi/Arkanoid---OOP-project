// ID: 205937949

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class defines a block object.
 * constructs it and moves it.
 * class also implements Collidable and Sprite interfaces/
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle shape;
    private java.awt.Color color;
    /**
     * The Hit listeners.
     */
    private List<HitListener> hitListeners;


    /**
     * construct a new Block.
     *
     * @param shape the shape of the ball (in rectangle object)
     * @param color the color
     */
    public Block(Rectangle shape, java.awt.Color color) {
        this.shape = shape;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * instructs a new Block.
     *
     * @param x      the x value of upper left point.
     * @param y      the y value of upper left point
     * @param width  the width
     * @param height the height
     * @param color  the color
     */
    public Block(int x, int y, int width, int height, Color color) {
        Point p = new Point(x, y);
        this.shape = new Rectangle(p, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Instantiates a new Block.
     *
     * @param p      the p upper left point
     * @param width  the width
     * @param height the height
     * @param color  the color
     */
    public Block(Point p, int width, int height, Color color) {
        this.shape = new Rectangle(p, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * methos returns shape of the block.
     *
     * @return rectangle of the block
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * method returns color of the ball.
     *
     * @return color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * moves block.
     * move block to a given new point
     *
     * @param p new Point for upperLeft point
     */
    public void moveBlock(Point p) {
        this.shape.setUpperLeft(p);
    }

    /**
     * method returns new velocity after hitting a block.
     *
     * @param hitter the hitter ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity of the ball
     * @return velocity after collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // separates the block to four lines
        Line upper = this.shape.upperLine();
        Line right = this.shape.rightLine();
        Line lower = this.shape.lowerLine();
        Line left = this.shape.leftLine();
        this.notifyHit(hitter);
        // if collision point is on upper or lower lines of the block
        if (upper.isInLine(collisionPoint) || lower.isInLine(collisionPoint)) {
            // change velocity of y axis
            currentVelocity.setDy(currentVelocity.getDy() * -1);
        }
        // if collision point is on right or left lines of the block
        if (right.isInLine(collisionPoint) || left.isInLine(collisionPoint)) {
            // change velocity of x axis
            currentVelocity.setDx(currentVelocity.getDx() * -1);
        }
        return currentVelocity;
    }

    /**
     * method draws the block on a given draw surface.
     *
     * @param surface given surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        // set color to draw the block
        surface.setColor(this.color);
        // draws the block
        int x = (int) this.shape.getUpperLeft().getX();
        int y = (int) this.shape.getUpperLeft().getY();
        int width = (int) this.shape.getWidth();
        int height = (int) this.shape.getHeight();
        surface.fillRectangle(x, y, width, height);
        // draw a frame
        surface.setColor(Color.black);
        surface.drawRectangle(x, y, width, height);
    }

    /**
     * method tells block that 60 milliseconds passed.
     */
    public void timePassed() {

    }

    /**
     * adds the block to the game object.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removes the block from the game object.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * Notify hit.
     * announce the hit listeners that hit happened
     *
     * @param hitter the hitter
     */
    public void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
