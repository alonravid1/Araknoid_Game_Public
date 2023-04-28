
package gameplay.levels;

import collidables.Block;
import collidables.BlockType;
import gameplay.Background;
import gameplay.GameGlobals;
import gameplay.LevelInformation;
import shapes.Point;
import shapes.Rectangle;
import sprites.Sprite;
import sprites.Velocity;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.6.2
 * First level of the game. Contains the information required to initialize a level.  */
public class LevelOne implements LevelInformation {
    private int numOfBalls;
    private int numOfBlocks;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;


    /**
     * Constructor. */
    public LevelOne() {
        this.numOfBalls = 1;
        this.numOfBlocks = 1;
        this.paddleSpeed = 10;
        this.paddleWidth = 100;
        this.levelName = "Direct Hit";

        this.initialBallVelocities = new ArrayList<Velocity>();
        this.initialBallVelocities().add(Velocity.fromAngleAndSpeed(0, 6));

        this.background = new Background(new Rectangle(new Point(0, 0),
                GameGlobals.guiWidth(), GameGlobals.guiHeight()), Color.BLACK);

        this.blocks = new ArrayList<Block>();
        this.addBlocks();
    }


    /**
     * Adds to the game a single block as required. */
    public void addBlocks() {
        Rectangle temp = new Rectangle(new Point(380, 200), 40
                , 40);

        this.blocks.add(new Block(temp, Color.RED, BlockType.REGULAR));

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
