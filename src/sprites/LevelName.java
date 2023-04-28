package sprites;

import biuoop.DrawSurface;
import gameplay.GameGlobals;
import gameplay.GameLevel;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.6.2
 * Class keeps the name of the current level and draws it on the upper right part of the screen. */
public class LevelName implements Sprite {
    private String levelName;

    /**
     * Constructor.
     * @param levelName the name of the level */
    public LevelName(String levelName) {
        this.levelName = levelName;
    }
    /**
     * Draws the sprite object on a given draw surface.
     * @param d the given draw surface */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(GameGlobals.guiWidth() * 3 / 5,
                GameGlobals.borderThickness() * 4 / 5, levelName, 20);
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
    }
}
