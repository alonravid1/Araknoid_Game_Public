
package gameplay;

import biuoop.DrawSurface;

import biuoop.KeyboardSensor;
import gameplay.animation.Animation;
import gameplay.animation.AnimationRunner;

import gameplay.animation.KeyPressStoppableAnimation;
import shapes.Rectangle;
import shapes.Point;

import sprites.Sprite;
import sprites.SpriteCollection;
import sprites.Ball;
import sprites.ScoreIndicator;
import sprites.LevelName;

import collidables.Collidable;
import collidables.Block;
import collidables.BlockType;
import collidables.Paddle;
import collidables.GameEnvironment;

import java.util.List;
import java.awt.Color;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.3.2
 * Using a level information instance, the class initializes the starting state of the board, and
 * enables the animation runner to draw the frames of the game while notifying all objects that a time unit
 * had passed, essentially running the game itself. */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;

    private AnimationRunner runner;
    private KeyboardSensor keyboard;

    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter currentScore;

    private LevelInformation level;


    private boolean running;

    /**
     * Constructor. recieves parameters from the gameflow class and a Level Information object fom which
     * it pulls the layout for the level.
     * @param level template the level layout
     * @param keyboard sensor which detects when the arrows and "p" buttons are pressed
     * @param runner animation runner which manages the drawing of the frames and pausing the game
     * @param currentScore current score of the player */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard, AnimationRunner runner, Counter currentScore) {
        this.running = false;
        this.level = level;
        this.keyboard = keyboard;
        this.runner = runner;
        this.currentScore = currentScore;
    }

    /**
     * Add a given collidable to the game environment.
     *
     * @param c given collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add a given sprite to the environment.
     *
     * @param s given sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * @return the game's environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Add 4 blocks, one covering each edge of the GUI.
     * The border blocks's upper left point are derived from the GUI height and width,
     * and their thickness is determined in the constructor for later modularity.
     */
    public void addBorders() {
        Rectangle temp = new Rectangle(new Point(0, GameGlobals.borderThickness()),
                GameGlobals.guiWidth(), GameGlobals.borderThickness());

        Block upperBorder = new Block(temp, Color.GRAY, BlockType.BORDER);

        temp = new Rectangle(new Point(0, GameGlobals.borderThickness()), GameGlobals.borderThickness(),
                GameGlobals.guiHeight() - GameGlobals.borderThickness());
        Block leftBorder = new Block(temp, Color.GRAY, BlockType.BORDER);

        temp = new Rectangle(new Point(GameGlobals.guiWidth() - GameGlobals.borderThickness(),
                GameGlobals.borderThickness()), GameGlobals.borderThickness(),
                GameGlobals.guiHeight() - GameGlobals.borderThickness());

        Block rightBorder = new Block(temp, Color.GRAY, BlockType.BORDER);

        temp = new Rectangle(new Point(GameGlobals.borderThickness(), GameGlobals.guiHeight()
                - GameGlobals.borderThickness()), GameGlobals.guiWidth() - GameGlobals.borderThickness(),
                GameGlobals.borderThickness() / 4);

        Block bottomBorder = new Block(temp, Color.GRAY, BlockType.DEATHREGION);

        leftBorder.addToGame(this);
        rightBorder.addToGame(this);
        upperBorder.addToGame(this);
        bottomBorder.addToGame(this);
    }


    /**
     * Adds 3 balls to the game.
     *
     * @param paddle the paddle on top of which the first ball starts
     */
    public void addBalls(Paddle paddle) {
        int radius = 5;

        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball ball = new Ball(paddle.getMiddle(), radius, Color.WHITE);

            ball.setVelocity(level.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
    }


    /**
     * Adds blocks to the game level from the level information.
     * @param blocks to add to the level */
    public void addBlocks(List<Block> blocks) {
        for (Block block : blocks) {
            block.addToGame(this);
        }

    }

    /**
     * @return the game's block counter
     */
    public Counter getBlockCounter() {
        return this.remainingBlocks;
    }

    /**
     * @return the game's ball counter
     */
    public Counter getBallCounter() {
        return this.remainingBalls;
    }

    /**
     * @return the game's score counter
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * Removes a Collidable object from the game environment, effectively deleting it from the game.
     *
     * @param c Collidable object to be removed
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes a Sprite object from the game's sprite collection, effectively deleting it from the game.
     *
     * @param s Sprite object to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initializes the game's GUI window, adds a ball, 6 lines of standard blocks and border blocks
     * using variables which are assigned in the game's constructor, made to easily be modified later on. */
    public void initialize() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();

        this.remainingBlocks = new Counter();
        this.remainingBlocks.increase(this.level.numberOfBlocksToRemove());

        this.remainingBalls = new Counter();
        this.remainingBalls.increase(this.level.numberOfBalls());

        this.level.getBackground().addToGame(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(new Rectangle(new Point(0, 0),
                GameGlobals.guiWidth(), GameGlobals.borderThickness()), Color.white);
        scoreIndicator.addToGame(this);

        LevelName levelName = new LevelName(this.level.levelName());
        levelName.addToGame(this);

        Paddle paddle = new Paddle(keyboard,
                this.level.paddleWidth(), this.level.paddleSpeed());
        paddle.addToGame(this);

        addBorders();
        addBalls(paddle);
        addBlocks(this.level.blocks());
    }


    /**
     * Run the game, - start the animation loop.
     * Stops when either all block or balls have been removed from the game. */
    public void run() {
        this.running = true;
        this.runner.run(this);
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, keyboard.SPACE_KEY, new PauseScreen()));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
        if (this.remainingBlocks.getValue() == 0) {
            currentScore.increase(100);
            this.running = false;
        }

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
