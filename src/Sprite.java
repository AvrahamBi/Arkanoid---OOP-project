// ID: 205937949

import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Draw on a given DrawSurface.
     *
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     * tells the object that 60 milliseconds have past
     */
    void timePassed();

    /**
     * Add to game.
     * add the object to a game
     *
     * @param g the g
     */
    void addToGame(GameLevel g);
}
