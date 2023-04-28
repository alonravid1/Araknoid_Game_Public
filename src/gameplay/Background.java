
package gameplay;

import biuoop.DrawSurface;
import shapes.Rectangle;
import sprites.Sprite;
import java.awt.Color;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.5.3
 * Class holds a rectangle which acts as the background of the game board, and as a sprite
 * which is drawn when the draw all functions is called. */
public class Background implements Sprite {
    private Rectangle rectangle;
    private Color color;

    /**
     * Constructor.
     * @param rectangle given rectangle which is the size of the game board
     * @param color of the background */
    public Background(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * Draws the sprite object on a given draw surface.
     *
     * @param d the given draw surface */
    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d, this.color);
    }

    /**
     * Notifies the sprite that a measurement of time has
     * passed within the game. */
    @Override
    public void timePassed() {

    }

    /**
     * Add an object to the game as a Sprite.
     *
     * @param g current game */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
