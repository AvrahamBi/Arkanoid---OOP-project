// ID: 205937949

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.List;

/**
 * class Game.
 * initialize the game and has fields of sprites and collidables.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCounter;
    private BlockRemover removeBlock;
    private Counter ballCounter;
    private BallRemover removeBall;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private String levelName;
    private  KeyboardSensor sensor;

    /**
     * Instantiates a new Game object.
     *
     * @param info   the info
     * @param runner the runner
     * @param ks     the ks
     * @param score  the score
     */
    public GameLevel(LevelInformation info, AnimationRunner runner, KeyboardSensor ks, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter(info.numberOfBlocksToRemove());
        this.removeBlock = new BlockRemover(this, this.blockCounter);
        this.ballCounter = new Counter(info.numberOfBalls());
        this.removeBall = new BallRemover(this, this.ballCounter);
        this.score = score;
        this.runner = runner;
        levelName = info.levelName();
        this.running = true;
        sensor = ks;

    }

    /**
     * Add collidable.
     * add collidable object
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     * add sprite object to the game
     *
     * @param s the sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize the game.
     * and create the pattern of blocks
     *
     * @param info the info
     */
    public void initialize(LevelInformation info) {
        List<Block> blocks = info.blocks();
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).addHitListener(this.removeBlock);
            blocks.get(i).addHitListener(new ScoreTrackingListener(this.score));
            blocks.get(i).addToGame(this);
        }
        for (int i = 0; i < info.numberOfBalls(); i++) {
            Ball ball = new Ball(400, 564, 6, Color.black, this.environment);
            ball.setVelocity(info.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
        Paddle p = new Paddle(sensor, info.paddleWidth());
        p.addToGame(this);
        // create four borders
        Block upperBorder = new Block(0, 0, 800, 20, Color.gray);
        Block rightBorder = new Block(780, 20, 20, 580, Color.gray);
        Block leftBorder = new Block(0, 20, 20, 580, Color.gray);
        // add the borders to game
        upperBorder.addToGame(this);
        rightBorder.addToGame(this);
        leftBorder.addToGame(this);
        // add death region
        Block deathRegion = new Block(0, 600, 800, 20, Color.gray);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(this.removeBall);
    }

    /**
     * Run the game.
     * using while loop and sleeper
     */
    public void run() {
        ScoreIndicator scores = new ScoreIndicator(this);
        scores.addToGame(this);
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Remove collidable from game.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Gets score of game.
     *
     * @return the score
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * Sets scores counter.
     *
     * @param scores the scores
     */
    public void setScoresCounter(Counter scores) {
        this.score = scores;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
            d.setColor(Color.lightGray);
            d.fillRectangle(0, 0, 800, 600);
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();
            this.running = true;
            if (this.ballCounter.getValue() == 0) {
                this.running = false;
            }
            if (this.blockCounter.getValue() == 0) {
                this.score.increase(100);
                this.running = false;
            }
            if (sensor.isPressed("p")) {
                this.runner.run(new PauseScreen(sensor));
            }
    }

    @Override public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return levelName;
    }

    /**
     * Gets remaining blocks.
     *
     * @return the remaining blocks
     */
    public int getRemainingBlocks() {
        return blockCounter.getValue();
    }

    /**
     * Gets remaining balls.
     *
     * @return the remaining balls
     */
    public int getRemainingBalls() {
        return ballCounter.getValue();
    }
}