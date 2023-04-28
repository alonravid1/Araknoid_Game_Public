
package gameplay;

import gameplay.animation.Animation;

import biuoop.DrawSurface;

import java.awt.Color;


/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.6.1
 * Class draws a window which tells the player they can resume by pressing space, and keeps drawing it until
 * space is pressed. */
public class PauseScreen implements Animation {

    /**
     * constructor. */
    public PauseScreen() {
    }

    /**
     * draws the pause screen.
     * @param d drawsurface on which the screen is drawn */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);

    }

    /**
     * tells the animation runner if it needs to stop drawing. In the case of the pause screen
     * it will always tell to stop, i.e. not continue the animation until the space key is pressed.
     * @return false */
    public boolean shouldStop() { return false; }
}
