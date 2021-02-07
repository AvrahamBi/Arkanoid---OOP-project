// ID: 205937949

import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * The type Sprite collection.
 * sprite is all the objects on the screen
 */
public class SpriteCollection {
    private ArrayList<Sprite> spritesArray;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.spritesArray = new ArrayList<Sprite>();
    }

    /**
     * Instantiates a new Sprite collection.
     *
     * @param sprites the sprites
     */
    public SpriteCollection(ArrayList<Sprite> sprites) {
        this.spritesArray = sprites;
    }

    /**
     * Add sprite.
     * add sprite to the sprite array
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.spritesArray.add(s);
    }

    /**
     * Notify all time passed.
     * notify all the object in the list that time has passed
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.spritesArray.size(); i++) {
            spritesArray.get(i).timePassed();
        }
    }

    /**
     * Draw all on.
     * draw all the objects in the list on a given draw surface
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spritesArray.size(); i++) {
            spritesArray.get(i).drawOn(d);
        }
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.spritesArray.remove(s);
    }
}
