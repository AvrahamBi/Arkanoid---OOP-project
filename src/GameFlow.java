// ID: 205937949

import biuoop.KeyboardSensor;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private AnimationRunner runner;
    private KeyboardSensor sensor;
    private int gameWidth;
    private int gameHeight;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar         animation runner
     * @param ks         keyboardSensor
     * @param gameHeight the game height
     * @param gameWidth  the game width
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int gameHeight, int gameWidth) {
        runner = ar;
        sensor = ks;
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean isWin = true;
        Counter score = new Counter(0);
        for (int i = 0; i < levels.size(); i++) {
            GameLevel level = new GameLevel(levels.get(i), runner, sensor, score);
            level.initialize(levels.get(i));
            while (level.getRemainingBlocks() > 0) {
                // if all balls get out of screen
                if (level.getRemainingBalls() == 0) {
                    isWin = false;
                    break;
                }
                level.run();
            }
            if (!isWin) {
                runner.run(new EndScreen(isWin, score, sensor));
            }
        }
        runner.run(new EndScreen(isWin, score, sensor));
    }
}