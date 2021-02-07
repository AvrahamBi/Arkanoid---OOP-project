// ID: 205937949

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel      the game
     * @param remainingBalls the remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}
