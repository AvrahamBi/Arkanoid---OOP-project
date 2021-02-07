// ID: 205937949

import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * The type Game environment.
 * has list of all collidable objects in the screen
 */
public class GameEnvironment {

    private ArrayList<Collidable> collidables;

    /**
     * Instantiates a new Game environment.
     *
     * @param collidables list of collidables
     */
// constructors
    public GameEnvironment(ArrayList<Collidable> collidables) {
        this.collidables = collidables;

    }

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Add collidable.
     * add collidable to the list
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Gets closest collision.
     * method iterates over the collidables list and check if there is a collision on the trajectory
     * of the ball
     *
     * @param trajectory the trajectory of a ball
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int notIntersects = 0;
        // list of collision info
        ArrayList<CollisionInfo> collisionPointsInfo = new ArrayList<CollisionInfo>();
        // for loop iterates over the collidables list
        for (int i = 0; i < this.collidables.size(); i++) {
            Rectangle shape = this.collidables.get(i).getCollisionRectangle();
            // if there is a collision on the trajectory
            if (trajectory.closestIntersectionToStartOfLine(shape) != null) {
               Point collisionPoint =  trajectory.closestIntersectionToStartOfLine(shape);
               // add the collision point to the collision info list
               collisionPointsInfo.add(new CollisionInfo(collisionPoint, this.collidables.get(i)));
            } else {
                notIntersects++;
            }
        }
        // if the trajectory doesnt have collision with the blocks
        if (notIntersects == this.collidables.size()) {
            return null;
        }
        // check what is the closest collision
        int index = 0;
        Point closestCollisionPoint = collisionPointsInfo.get(0).collisionPoint();
        for (int i = 1; i < collisionPointsInfo.size(); i++) {
            Point currentPoint = collisionPointsInfo.get(i).collisionPoint();
            if (trajectory.start().distance(currentPoint) < trajectory.start().distance(closestCollisionPoint)) {
                closestCollisionPoint = currentPoint;
                index = i;
            }
        }
        return collisionPointsInfo.get(index);
    }

    /**
     * method draws the game environment on a given draw surface.
     *
     * @param surface given surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        for (int i = 0; i < this.collidables.size(); i++) {
            Collidable collidable = this.collidables.get(i);
            surface.setColor(collidable.getColor());
            int x = (int) collidable.getCollisionRectangle().getUpperLeft().getX();
            int y = (int) collidable.getCollisionRectangle().getUpperLeft().getY();
            int width = (int) collidable.getCollisionRectangle().getWidth();
            int height = (int) collidable.getCollisionRectangle().getHeight();
            surface.fillRectangle(x, y, width, height);
        }
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}
