
package gameplay;

import gameplay.animation.Animation;

import biuoop.DrawSurface;

import java.awt.Color;


/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.6.3
 * Class draws the end screen. */
public class EndScreen implements Animation {
    private boolean victory;
    private int endScore;

    /**
     * Constructor.
     * @param endScore score to present on screen
     * @param victory tells the end screen whether the players won or not */
    public EndScreen(int endScore, boolean victory) {
        this.victory = victory;
        this.endScore = endScore;
    }

    /**
     * draws the end screen.
     * @param d drawsurface to draw it on */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        if (this.victory) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.endScore, 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.endScore, 32);
        }

    }

    /**
     * since this is the end screen, the animation loop should not continue.
     * @return false */
    public boolean shouldStop() { return false; }
}
