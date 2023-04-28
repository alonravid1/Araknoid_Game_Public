
package collidables;

import biuoop.DrawSurface;

import shapes.Point;
import shapes.Rectangle;

import collidables.handlers.HitNotifier;
import collidables.handlers.HitListener;
import collidables.handlers.BallRemover;
import collidables.handlers.BlockRemover;
import collidables.handlers.ScoreTrackingListener;

import sprites.Velocity;
import sprites.Sprite;
import sprites.Ball;
import gameplay.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.3.1
 * Class is used to create a block on a 2D plane.
 * <p>
 * Using the rectangle class this class enables us to describe a block on a 2D grid,
 * which implements the Collidable and Sprite interfaces in order to enable objects to bounce off of it and be drawn
 * on the game board. */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle shape;
    private Color color;
    private List<HitListener> hitListeners;
    private BlockType type;


    /**
     * Constructs an instance using a given shape and color.
     * Defaults to set block as a regular block.
     * @param shape given shape
     * @param color given color */
    public Block(Rectangle shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.type = BlockType.REGULAR;
    }

    /**
     * Constructs an instance using a given shape and color.
     * @param shape given shape
     * @param color given color
     * @param type of the block, either a border block or the death region block which is the bottom area */
    public Block(Rectangle shape, Color color, BlockType type) {
        this.shape = shape;
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.type = type;
    }

    /**
     * @return the block's color */
    public Color getColor() {
        return color;
    }

    /**
     * @return a rectangle describing the area in which objects collide with the block*/
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * Method is called after being notified by GameLevel Environment class that a collision
     * had occurred between the ball and this block.
     * The method then checks which vertices was hit, notifies the block remover hit listener
     * and returns the velocity after reversing it accordingly.
     * @param collisionPoint point of collision on the block's collidable shape
     * @param currentVelocity velocity of ball colliding with the block
     * @param hitter ball hitting the block
     * @return the appropriately reversed velocity of the ball */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.getX() == this.shape.getUpperLeft().getX()
        || collisionPoint.getX() == this.shape.getUpperLeft().getX() + this.shape.getWidth()) {
            currentVelocity.setDx((-1) * currentVelocity.getDx());
        }
        if (collisionPoint.getY() == this.shape.getUpperLeft().getY()
                || collisionPoint.getY() == this.shape.getUpperLeft().getY() + this.shape.getHeight()) {
            currentVelocity.setDy((-1) * currentVelocity.getDy());

        }
        if (this.type != BlockType.BORDER) {
            this.notifyHit(hitter);
        }
        return currentVelocity;
    }

    /**
     * Calls for the block's collidable shape drawOn method, which draws it
     * on a given draw surface.
     * The bottom border block, the death region, is not drawn at all.
     * @param d given draw surface */
    @Override
    public void drawOn(DrawSurface d) {
        if (this.type != BlockType.DEATHREGION) {
            this.shape.drawOn(d, this.color);
        }
    }

    /**
     *Notifies the block that a measurement of
     *time has passed within the game. */
    @Override
    public void timePassed() {

    }

    /**
     * Add a block to the game, both as a Sprite and a Collidable.
     * @param g current game */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
        if (this.type == BlockType.REGULAR) {
            addHitListener(new BlockRemover(g, g.getBlockCounter()));
            addHitListener(new ScoreTrackingListener(g.getCurrentScore()));

        } else if (this.type == BlockType.DEATHREGION) {
            addHitListener(new BallRemover(g, g.getBallCounter()));

        }
    }
    /**
     * Remove a block to the game, from both the Sprite and Collidable collections.
     * @param g current game */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);

    }

    /**
     * Notifies the block remover hit listener that a hit had occurred, which will cause him to
     * remove this block from the game.
     * @param hitter the ball which hit the block */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);

    }
}
