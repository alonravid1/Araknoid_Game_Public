package sprites;

import biuoop.DrawSurface;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.3.2
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructs an instance of the class, sets its sprites list as
     * a new empty list of sprites. */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }
    /**
     * Add a given sprite to the environment.
     * @param s the given sprite object */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * remove a given sprite from the environment.
     * @param s the given sprite object */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Notifies all of the sprites within this collection's list
     * that a measurement of time has passed within the game. */
    public void notifyAllTimePassed() {
        List<Sprite> tempSprites = new ArrayList<Sprite>(this.sprites);
        for (Sprite x : tempSprites) {
            x.timePassed();
        }

    }

    /**
     * Draws all of the sprites within this collection's liston a given draw surface.
     * @param d the given draw surface */
    public void drawAllOn(DrawSurface d) {
        for (Sprite x : this.sprites) {
            x.drawOn(d);
        }

    }
}
