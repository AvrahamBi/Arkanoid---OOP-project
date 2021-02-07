// ID: 205937949

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Easy level.
 */
public class EasyLevel implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * Instantiates a new Easy level.
     */
    public EasyLevel() {
        numberOfBalls = 1;
        ballsVelocities = new ArrayList<>();
        paddleSpeed = 10;
        paddleWidth = 100;
        levelName = "Easy";
        background = new Block(0, 0, 800, 600, Color.yellow);
        blocks = new ArrayList<>();
        numberOfBlocksToRemove = 1;

    }
    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v1 = new Velocity(0, -6);
        ballsVelocities.add(v1);
        return ballsVelocities;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        Block b1 = new Block(385, 100, 30, 30, Color.red);
        blocks.add(b1);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }
}
