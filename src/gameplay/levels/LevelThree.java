
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
import java.util.Random;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.6.2
 * First level of the game. Contains the information required to initialize a level.  */
public class LevelThree implements LevelInformation {
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
    public LevelThree() {
        this.numOfBalls = 2;
        this.numOfBlocks = 40;
        this.paddleSpeed = 10;
        this.paddleWidth = 90;
        this.stdWidth = 50;
        this.stdHeight = 30;
        this.levelName = "Green 3";

        this.initialBallVelocities = new ArrayList<Velocity>();
        this.initialBallVelocities().add(Velocity.fromAngleAndSpeed(-40, 5));
        this.initialBallVelocities().add(Velocity.fromAngleAndSpeed(40, 5));

        this.background = new Background(new Rectangle(new Point(0, 0),
                GameGlobals.guiWidth(), GameGlobals.guiHeight()), Color.GREEN);

        this.blocks = new ArrayList<Block>();

        this.addBlocks();

    }

    /**
     * @return the number of balls the players has at the beginning of the level.
     */
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    /**
     * @return the velocity of each ball at the beginning of the level.
     */
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    /**
     * @return the speed of the paddle in this level.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return the width of the paddle in this level.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * @return the name of the level.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * @return The Blocks that make up this level, each block contains, its size, color and location.
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * @return The number of blocks that need to be removed in order for the level
     * to be considered cleared. This number should be <= blocks.size()
     */
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }

    /**
     * Adds to the game a line of a number of blocks with a uniform random color,
     * using the game's standard height and width for measurement.
     * @param numberOfBlocks to be placed in the line
     * @param startY starting Y value for the first block in the line */
    public void addBlockLine(int numberOfBlocks, int startY) {
        //generate a random uniform color for all blocks in this line
        Color color = new Color(new Random().nextInt(256),
                new Random().nextInt(256), new Random().nextInt(256));

        for (int i = 1; i <= numberOfBlocks; i++) {
            Point upperLeft = new shapes.Point(GameGlobals.guiWidth()
                    - GameGlobals.borderThickness() - (i * this.stdWidth), startY);
            Rectangle rec = new Rectangle(upperLeft, this.stdWidth, this.stdHeight);
            this.blocks.add(new Block(rec, color));
        }

    }

    /**
     * Adds blocks to the game level in lines.
     */
    public void addBlocks() {
        for (int i = 10; 4 < i; i--) {
            //draw 6 lines of blocks, starting at 4/5 the height of the game window
            addBlockLine(i, 4 * GameGlobals.guiHeight() / 5 - (i) * stdHeight);
        }
    }
}
