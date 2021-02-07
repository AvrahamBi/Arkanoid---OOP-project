// ID: 205937949

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     * animation does one frame
     * @param d drawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     * return true if the animation should stop
     * @return true if animation should stop
     */
    boolean shouldStop();
}