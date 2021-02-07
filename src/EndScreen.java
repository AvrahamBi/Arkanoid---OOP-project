// ID: 205937949

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private boolean shouldStop;
    private boolean isWin;
    private Counter score;
    private KeyboardSensor sensor;

    /**
     * Instantiates a new End screen.
     *
     * @param isWin  boolean variable
     * @param score  the score
     * @param sensor the sensor
     */
    public EndScreen(boolean isWin, Counter score, KeyboardSensor sensor) {
        this.isWin = isWin;
        this.score = score;
        this.sensor = sensor;
        shouldStop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String finalScore = String.valueOf(score.getValue());
        String result;
        if (this.isWin) {
            d.setColor(Color.GREEN);
            d.fillRectangle(0, 0, 800, 600);
            result = "You Win! ";
        } else {
            d.setColor(Color.red);
            d.fillRectangle(0, 0, 800, 600);
            result = "Game Over. ";
        }
        String finalMessage = result + "Your score is " + finalScore;
        d.setColor(Color.BLACK);
        d.drawText(50, 300, finalMessage, 50);
        if (sensor.isPressed("space")) {
            shouldStop = true;
            System.exit(0);
        }

    }

    @Override
    public boolean shouldStop() {
        return shouldStop;
    }
}