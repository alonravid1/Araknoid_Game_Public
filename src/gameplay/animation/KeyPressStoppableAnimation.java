
package gameplay.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.6.4
 * Class acts as a decorator, facilitating the utilization of a paused animation and unpause button, while
 * drawing the screen according to the animation. */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     * @param sensor checks whether the key was pressed to unpause
     * @param key which the player needs to press to unpause
     * @param animation the current level which is paused */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * draws a single frame of the animation on the given drawsurface, while checking
     * if the designated key was pressed to unpause the game.
     * @param d drawsurface */
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboard.isPressed(key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        this.isAlreadyPressed = false;

    }

    /**
     * tells the animation runner it needs to stop the animation.
     * @return false if it should continue to draw, true if not. */
    public boolean shouldStop() {
        return this.stop;
    }
}
