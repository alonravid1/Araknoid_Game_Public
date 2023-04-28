package sprites;

import gameplay.GameLevel;
import biuoop.DrawSurface;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.3.2
 */
public interface Sprite {
    // draw the sprite to the screen

    /**
     * Draws the sprite object on a given draw surface.
     * @param d the given draw surface */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a measurement of time has
     * passed within the game. */
    void timePassed();

    /**
     * Add an object to the game as a Sprite.
     * @param g current game */
    void addToGame(GameLevel g);

}
