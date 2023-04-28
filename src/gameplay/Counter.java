
package gameplay;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.5.2
 * Class keeps count of blocks, balls and score while preventing classes from unnecessarily have
 * a field of the game. */
public class Counter {
    private int value;

    /**
     * Constructor. */
    public Counter() {
        this.value = 0;
    }

    /**
     * Increases the counter by the given number.
     * @param number to increase the counter by */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Decreases the counter by the given number.
     * @param number to decrease the counter by */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * @return the current value stored in the counter */
    public int getValue() {
        return this.value;
    }
}
