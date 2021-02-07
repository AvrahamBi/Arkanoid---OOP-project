// ID: 205937949

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Medium level.
 */
public class MediumLevel implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * Instantiates a new Medium level.
     */
    public MediumLevel() {
        numberOfBalls = 10;
        ballsVelocities = new ArrayList<>();
        paddleSpeed = 10;
        paddleWidth = 600;
        levelName = "Medium";
        background = new Block(0, 0, 800, 600, Color.yellow);
        blocks = new ArrayList<>();
        numberOfBlocksToRemove = 15;

    }

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v1 = new Velocity(5, -7);
        Velocity v2 = new Velocity(4, -7);
        Velocity v3 = new Velocity(3, -7);
        Velocity v4 = new Velocity(2, -7);
        Velocity v5 = new Velocity(1, -7);
        Velocity v6 = new Velocity(-1, -7);
        Velocity v7 = new Velocity(-2, -7);
        Velocity v8 = new Velocity(-3, -7);
        Velocity v9 = new Velocity(-4, -7);
        Velocity v10 = new Velocity(-5, -7);
        ballsVelocities.add(v1);
        ballsVelocities.add(v2);
        ballsVelocities.add(v3);
        ballsVelocities.add(v4);
        ballsVelocities.add(v5);
        ballsVelocities.add(v6);
        ballsVelocities.add(v7);
        ballsVelocities.add(v8);
        ballsVelocities.add(v9);
        ballsVelocities.add(v10);
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
        Point p = new Point(20, 200);
        int x = 20;
        for (int i = 0; i < numberOfBlocksToRemove; i++) {
            Block b = new Block(x, 200, 52, 20, colorToBlock(i));
            blocks.add(b);
            x += 52;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }

    /**
     * Color to block color.
     *
     * @param i the
     * @return the color
     */
    public Color colorToBlock(int i) {
        if (i == 0 || i == 1) {
            return Color.red;
        }
        if (i == 2 || i == 3) {
            return Color.orange;
        }
        if (i == 4 || i == 5) {
            return Color.yellow;
        }
        if (i == 6 || i == 7 || i == 8) {
            return Color.green;
        }
        if (i == 9 || i == 10) {
            return Color.BLUE;
        }
        if (i == 11 || i == 12) {
            return Color.PINK;
        }
        if (i == 13 || i == 14) {
            return Color.red;
        }
        return Color.MAGENTA;
    }
}

