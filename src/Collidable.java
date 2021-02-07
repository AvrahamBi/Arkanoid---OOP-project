// ID: 205937949

import java.awt.Color;

/**
 * The interface Collidable.
 * Block  and other collidables objects implements it.
 */
public interface Collidable {
    /**
     * Gets rectangle of the object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     * method returns new velocity after hitting a collidable object
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity of the ball
     * @return velocity, new velocity for the ball
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Gets color.
     *
     * @return color, the color of the object
     */
    Color getColor();
}
