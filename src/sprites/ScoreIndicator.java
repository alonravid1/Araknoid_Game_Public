package sprites;

import biuoop.DrawSurface;
import shapes.Rectangle;
import gameplay.GameLevel;
import gameplay.Counter;
import java.awt.Color;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.5.3
 * Class keeps count of the current score and draws it on the upper part of the screen. */
public class ScoreIndicator implements Sprite {
    private Rectangle rectangle;
    private Color color;
    private Counter currentScore;

    /**
     * Constructor.
     * @param rectangle background for the score itself
     * @param color of the background rectangle */
    public ScoreIndicator(Rectangle rectangle, Color color) {
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
        String score = "Score: " + this.currentScore.getValue();
        d.drawText((int) this.rectangle.getWidth() * 2 / 5,
                (int) this.rectangle.getHeight() * 4 / 5, score, 20);
    }

    /**
     * Notifies the sprite that a measurement of time has
     * passed within the game.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Add an object to the game as a Sprite.
     * @param g current game */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        this.currentScore = g.getCurrentScore();
    }
}
