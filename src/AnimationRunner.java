// ID: 205937949

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Instantiates a new Animation runner.
     *
     * @param gui GUI
     */
//
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        framesPerSecond = 60;
        sleeper = new Sleeper();
    }

    /**
     * Run.
     * runs the animation
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Gets gui.
     *
     * @return gui
     */
    public GUI getGui() {
        return gui;
    }
}
