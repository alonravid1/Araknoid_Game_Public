
package gameplay;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.6.2
 * Interface enables the global use of standard gui,borders and paddle scales throughout all
 * levels and objects which require them for reference (such as paddle which need to know if it hit
 * the game border). */
public class GameGlobals {
    private static int guiWidth = 800;
    private static int guiHeight = 600;
    private static int borderThickness = 25;
    private static int paddleHeight = 20;

    /**
     * @return the global borderThickness */
    public static int borderThickness() {
        return borderThickness;
    }

    /**
     * @return the global guiHeight */
    public static int guiHeight() {
        return guiHeight;
    }

    /**
     * @return the global guiWidth */
    public static int guiWidth() {
        return guiWidth;
    }

    /**
     * @return the global paddleHeight */
    public static int paddleHeight() {
        return paddleHeight;
    }
}
