// ID: 205937949

/**
 * The interface Hit notifier.
 * defines objects that announce when being hit
 */
public interface HitNotifier {
    /**
     * Add hit listener.
     *
     * @param hl the hit listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     * Remove hit listener from the list of listeners to hit events.
     *
     * @param hl the hit listener
     */
    void removeHitListener(HitListener hl);
}