// ID: 205937949

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Hard level.
 */
public class HardLevel implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * Instantiates a new Hard level.
     */
    public HardLevel() {
        numberOfBalls = 2;
        ballsVelocities = new ArrayList<>();
        paddleSpeed = 10;
        paddleWidth = 100;
        levelName = "Hard";
        background = new Block(0, 0, 800, 600, Color.yellow);
        blocks = new ArrayList<>();
        numberOfBlocksToRemove = 40;

    }

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v1 = new Velocity(1, -7);
        Velocity v2 = new Velocity(-7, -6);
        ballsVelocities.add(v1);
        ballsVelocities.add(v2);
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
        int xValueOfLine = 230;
        int yValueOfLine = 100;
        int xValueOfBlock = 0;
        for (int i = 10; 5 < i; i--) {
            xValueOfLine += 50;
            yValueOfLine += 25;
            xValueOfBlock = 0;
            for (int j = 0; j <= i; j++) {
                Point upperLeftOfBlock = new Point(xValueOfLine + xValueOfBlock, yValueOfLine);
                Block b = new Block(upperLeftOfBlock, 50, 25, colorToBlock(i));
                blocks.add(b);
                xValueOfBlock += 50;
            }
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
        if (i == 10) {
            return Color.gray;
        }
        if (i == 9) {
            return Color.red;
        }
        if (i == 8) {
            return Color.yellow;
        }
        if (i == 7) {
            return Color.BLUE;
        }
        return Color.white;
    }
}

