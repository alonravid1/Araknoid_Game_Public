import biuoop.GUI;
import gameplay.GameFlow;
import gameplay.GameGlobals;
import gameplay.LevelInformation;
import gameplay.animation.AnimationRunner;
import gameplay.levels.LevelOne;
import gameplay.levels.LevelTwo;
import gameplay.levels.LevelThree;
import gameplay.levels.LevelFour;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.6.3
 * class which runs the program's main, receives user arguments and creates a game with levels
 * as determined by user arguments. */
public class Ass6Game {

    /**
     * Main function, initializes a game object and runs it.
     * @param args user input */
    public static void main(String[] args) {

        GUI gui = new GUI("Arkanoid", GameGlobals.guiWidth(), GameGlobals.guiHeight());
        List<LevelInformation> levels = new ArrayList<>();
        for (String arg : args) {
            switch (arg) {
                case "1":  levels.add(new LevelOne());
                    break;
                case "2": levels.add(new LevelTwo());
                    break;
                case "3": levels.add(new LevelThree());
                    break;
                case "4": levels.add(new LevelFour());
                        break;
                default: break;
            }
        }
        if (levels.size() == 0) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
            levels.add(new LevelFour());
        }
        AnimationRunner ar = new AnimationRunner(gui);
        GameFlow game = new GameFlow(ar, gui.getKeyboardSensor());
        game.runLevels(levels);

    }
}