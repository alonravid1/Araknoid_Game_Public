
package gameplay.animation;

import biuoop.DrawSurface;

/**
 * @author Alon Ravid
 * @version 0.6.4
 * @since 0.6.1
 * Interface ensures the implementing object can draw a frame and stop running the animation. */
public interface Animation {
    /**
     * draws a single frame of the class on the given drawsurface.
     * @param d drawsurface */
    void doOneFrame(DrawSurface d);

    /**
     * tells the animation runner it needs to stop the animation.
     * @return false if it should continue to draw, true if not. */
    boolean shouldStop();
}
