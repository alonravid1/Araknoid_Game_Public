
package gameplay.levels;

import collidables.Block;
import gameplay.Background;
import gameplay.GameGlobals;
import gameplay.LevelInformation;
import shapes.Point;
import shapes.Rectangle;
import sprites.Sprite;
import sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.6.2
 * First level of the game. Contains the information required to initialize a level.  */
public class LevelTwo implements LevelInformation {
    private int numOfBalls;
    private int numOfBlocks;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;

    private int stdWidth; //width of a standard game block
    private int stdHeight; //height of a standard game block

    /**
     * Constructor. */
    public LevelTwo() {
        this.numOfBalls = 10;
        this.numOfBlocks = 15;
        this.paddleSpeed = 10;
        this.paddleWidth = 500;
        this.stdWidth = 50;
        this.stdHeight = 25;
        this.levelName = "Wide Easy";

        this.initialBallVelocities = new ArrayList<Velocity>();
        for (int i = 0; i < 5; i++) {
            this.initialBallVelocities().add(Velocity.fromAngleAndSpeed(-60 + 10 * i, 6));
        }

        for (int i = 0; i < 5; i++) {
            this.initialBallVelocities().add(Velocity.fromAngleAndSpeed(20 + 10 * i, 6));
        }

        this.background = new Background(new Rectangle(new Point(0, 0), GameGlobals.guiWidth(),
                GameGlobals.guiHeight()), Color.WHITE);

        this.blocks = new ArrayList<Block>();
        this.addBlocks();
    }

    /**
     * Adds blocks to the game level in lines. */
    public void addBlocks() {
        for (int i = 0; i < this.numOfBlocks; i++) {
            Point upperLeft = new Point(GameGlobals.borderThickness() + (i * this.stdWidth),
                    GameGlobals.guiHeight() / 2);

            Rectangle rec = new Rectangle(upperLeft, this.stdWidth, this.stdHeight);
            this.blocks.add(new Block(rec, Color.BLUE));
        }
    }


    /**
     * @return the number of balls the players has at the beginning of the level. */
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    /**
     * @return the velocity of each ball at the beginning of the level. */
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    /**
     * @return the speed of the paddle in this level. */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return the width of the paddle in this level. */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * @return the name of the level. */
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return a sprite with the background of the level. */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * @return The Blocks that make up this level, each block contains, its size, color and location. */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * @return The number of blocks that need to be removed in order for the level
     * to be considered cleared. */
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }
}
