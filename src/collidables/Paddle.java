
package collidables;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

import shapes.Rectangle;
import shapes.Point;

import sprites.Velocity;
import sprites.Sprite;
import sprites.Ball;

import gameplay.GameLevel;
import gameplay.GameGlobals;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.3.3
 * Class represents a moving paddle on which the ball bounces.
 * <p>
 * Using the rectangle class this class enables us to describe a block on a 2D grid,
 * which implements the Collidable and Sprite interfaces in order to enable objects to bounce off of it and be drawn
 * on the gameLevel board.
 * In addition to being a block, the paddle can move according to user input, and has 5 different regions on its upper
 * edge, each giving a slightly different change to the ball's velocity as its bounces off of it. */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle paddle;
    private Color color;
    private int speed;

    /**
     * Constructs an instance with a keyboard sensor, the speed and width of a paddle.
     * @param keyboard listener which detects when a key is pressed
     * @param speed of the paddle
     * @param width of the paddle */
    public Paddle(KeyboardSensor keyboard, int width, int speed) {
        this.keyboard = keyboard;
        this.speed = speed;
        Point start = new Point((GameGlobals.guiWidth() - width) / 2,
                GameGlobals.guiHeight() - GameGlobals.borderThickness() - GameGlobals.paddleHeight());

        this.paddle = new Rectangle(start, width, GameGlobals.paddleHeight());
        this.color = Color.YELLOW;

    }

    /**
     * Whenever the user presses the left arrow key, this function is called and moves the paddle
     * left (towards the negative side of the X axis) on the screen. */
    public void moveLeft() {
        Point newUpperLeft = this.paddle.getUpperLeft();
        if (this.paddle.getUpperLeft().getX() - speed
                > GameGlobals.borderThickness()) {
            newUpperLeft.setX(newUpperLeft.getX() - speed);
        } else {
            newUpperLeft.setX(GameGlobals.borderThickness());

        }
        this.paddle.setUpperLeft(newUpperLeft);

    }

    /**
     * Whenever the user presses the right arrow key, this function is called and moves the paddle
     * right (towards the positive side of the X axis) on the screen. */
    public void moveRight() {
        Point newUpperLeft = this.paddle.getUpperLeft();
        if (this.paddle.getUpperLeft().getX() + this.paddle.getWidth() + speed
                < GameGlobals.guiWidth() - GameGlobals.borderThickness()) {
            newUpperLeft.setX(newUpperLeft.getX() + speed);

        } else {
            newUpperLeft.setX(GameGlobals.guiWidth() - GameGlobals.borderThickness() - this.paddle.getWidth());

        }
        this.paddle.setUpperLeft(newUpperLeft);

    }

    /**
     * Checks whether the user pressed the right or left key and move the paddle accordingly. */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            moveLeft();
        } else if (this.keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the gameLevel screen.
     * @param d the given draw surface */
    @Override
    public void drawOn(DrawSurface d) {
        this.paddle.drawOn(d, color);
    }

    /**
     * @return the paddle's collision rectangle */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * @return the the point set in the middle of the upper edge of the paddle */
    public Point getMiddle() {
        return new Point(getCollisionRectangle().getUpperLeft().getX() + (getCollisionRectangle().getWidth() / 2)
                , getCollisionRectangle().getUpperLeft().getY() - 1);
    }



    /**
     * Method is called after being notified by GameLevel Environment class that a collision
     * had occurred between the ball and the paddle.
     * The method then checks which region of the paddle was hit, and returns the velocity after reversing it
     * and changing its angle accordingly.
     * @param collisionPoint point of collision on the block's collidable shape
     * @param currentVelocity velocity of ball colliding with the block
     * @param hitter ball hitting the block
     * @return the changed velocity of the ball */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.getY() == this.paddle.getUpperLeft().getY()) {
            double x = this.paddle.getUpperLeft().getX();
            double increment = paddle.getWidth() / 5;
            if (x + 4 * increment <= collisionPoint.getX()) { //region 5
                return Velocity.setAngularVelocity(60, currentVelocity);

            } else if (x + 3 * increment <= collisionPoint.getX()) { //region 4
                return Velocity.setAngularVelocity(30, currentVelocity);

            } else if (x + 2 * increment <= collisionPoint.getX()) { //region 3
                currentVelocity.setDy((-1) * currentVelocity.getDy());
                return currentVelocity;

            } else if (x + increment <= collisionPoint.getX()) { //region 2
                return Velocity.setAngularVelocity(-30, currentVelocity);

            } else { //region 1
                return Velocity.setAngularVelocity(-60, currentVelocity);
            }
        } else {
            currentVelocity.setDx((-1) * currentVelocity.getDx());
            return currentVelocity;
        }

    }

    /**
     * Adds the paddle to the given gameLevel's gameLevel environment and sprite collection.
     * @param g given gameLevel */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}