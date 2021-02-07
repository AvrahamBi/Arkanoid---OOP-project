// ID: 205937949

/**
 * The interface Hit listener.
 * listen to hit notifiers to know when being hit
 */
public interface HitListener {
    /**
     * Hit event.
     * This method is called whenever the beingHit object is hit
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}