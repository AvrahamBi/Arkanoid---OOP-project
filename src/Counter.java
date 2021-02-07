// ID: 205937949

/**
 * The type Counter.
 */
public class Counter {
    private int counter;

    /**
     * Instantiates a new Counter.
     *
     * @param number the first number of counter
     */
    public Counter(int number) {
        this.counter = number;
    }

    /**
     * Increase the counter.
     *
     * @param number to add to counter
     */
// add number to current count.
    public void increase(int number) {
        counter = counter + number;
    }

    /**
     * Decrease th counter.
     *
     * @param number to decrease from counter
     */
// subtract number from current count.
    public void decrease(int number) {
        counter = counter - number;
    }

    /**
     * Gets value of counter.
     *
     * @return the value
     */
// get current count.
    public int getValue() {
        return this.counter;
    }
}