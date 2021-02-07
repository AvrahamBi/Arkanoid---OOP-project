// ID: 205937949

/**
 * object that gives details about a collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the object that collided by the ball
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * returns the collision point.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return this.collisionObject;

    }
}
