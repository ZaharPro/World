package game.mans;

import game.Helper;

import game.ImageArray;
import game.bounds.Bounds;
import javafx.scene.canvas.GraphicsContext;

public class SpriteMan extends ManBase {

    private final ImageArray UP, DOWN, LEFT, RIGHT;

    public SpriteMan(Bounds bounds,
                     ImageArray UP, ImageArray DOWN,
                     ImageArray LEFT, ImageArray RIGHT,
                     float speedX, float speedY) {
        super(null, bounds, speedX, speedY);
        Helper.requireNonNull(UP);
        Helper.requireNonNull(DOWN);
        Helper.requireNonNull(LEFT);
        Helper.requireNonNull(RIGHT);
        this.UP = UP;
        this.DOWN = DOWN;
        this.LEFT = LEFT;
        this.RIGHT = RIGHT;
        image = DOWN.getImage();
    }

    public void draw(GraphicsContext graphicsContext) {
        Helper.requireNonNull(graphicsContext);
        ImageArray TEMP = null;
        if (left) {
            TEMP = LEFT;
        } else if (right) {
            TEMP = RIGHT;
        } else if (up) {
            TEMP = UP;
        } else if (down) {
            TEMP = DOWN;
        }
        if (TEMP != null) {
            TEMP.next();
            image = TEMP.getImage();
        }
        graphicsContext.drawImage(image, bounds.getX(), bounds.getY(),
                bounds.getWidth(), bounds.getHeight());
    }
}