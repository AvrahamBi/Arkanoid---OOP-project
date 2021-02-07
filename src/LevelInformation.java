// ID: 205937949

import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     * @return number of balls in the level
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     *
     * @return list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * Level name string.
     *
     * @return the string
     */
    String levelName();

    /**
     * Gets background.
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * Blocks list.
     *
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove.
     *
     * @return the int
     */
    int numberOfBlocksToRemove();
}