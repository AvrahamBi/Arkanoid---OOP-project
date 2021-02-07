// ID: 205937949

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Expert level.
 */
public class ExpertLevel implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * Instantiates a new Expert level.
     */
    public ExpertLevel() {
        numberOfBalls = 3;
        ballsVelocities = new ArrayList<>();
        paddleSpeed = 10;
        paddleWidth = 100;
        levelName = "Expert";
        background = new Block(0, 0, 800, 600, Color.yellow);
        blocks = new ArrayList<>();
        numberOfBlocksToRemove = 105;

    }

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v1 = new Velocity(0, -8);
        Velocity v2 = new Velocity(-1, -8);
        Velocity v3 = new Velocity(-2, -8);
        ballsVelocities.add(v1);
        ballsVelocities.add(v2);
        ballsVelocities.add(v3);
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
        int yValueOfLine = 40;
        int xValueOfBlock = 0;
        for (int i = 0; i < 7; i++) {
            yValueOfLine += 25;
            xValueOfBlock = 20;
            for (int j = 0; j < 15; j++) {
                Point upperLeftOfBlock = new Point(xValueOfBlock, yValueOfLine);
                Block b = new Block(upperLeftOfBlock, 51, 25, colorToBlock(i));
                blocks.add(b);
                xValueOfBlock += 51;
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
     * define color to each line
     * @param i int
     * @return the color
     */
    public Color colorToBlock(int i) {
        if (i == 0) {
            return Color.gray;
        }
        if (i == 1) {
            return Color.red;
        }
        if (i == 2) {
            return Color.yellow;
        }
        if (i == 3) {
            return Color.GREEN;
        }
        if (i == 4) {
            return Color.white;
        }
        if (i == 5) {
            return Color.pink;
        }
        return Color.CYAN;
    }
}

