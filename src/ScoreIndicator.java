// ID: 205937949

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Score indicator.
 * show the scores of game
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private GameLevel gameLevel;

    /**
     * Instantiates a new Score indicator.
     *
     * @param g the game
     */
    public ScoreIndicator(GameLevel g) {
        this.score = g.getScore();
        this.gameLevel = g;
    }
    @Override
    public void drawOn(DrawSurface d) {
        // draw name level
        d.setColor(Color.lightGray);
        d.fillRectangle(500, 0, 180, 20);
        d.setColor(Color.black);
        d.drawRectangle(500, 0, 180, 20);
        String name = gameLevel.getName();
        d.setColor(Color.black);
        d.drawText(505, 17, "Level Name: " + name, 18);
        // draw score
        d.setColor(Color.lightGray);
        d.fillRectangle(200, 0, 100, 20);
        d.setColor(Color.black);
        d.drawRectangle(200, 0, 100, 20);
        String scores = Integer.toString(this.score.getValue());
        d.setColor(Color.black);
        d.drawText(205, 17, "score: " + scores, 18);
    }

    @Override
    public void timePassed() {
        this.score = this.gameLevel.getScore();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
