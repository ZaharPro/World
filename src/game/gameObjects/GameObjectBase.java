package game.gameObjects;

import game.Helper;
import game.bounds.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObjectBase implements GameObject {

    protected final Image image;
    protected final Bounds bounds;

    public GameObjectBase(Image image, Bounds bounds) {
        Helper.requireNonNull(image);
        Helper.requireNonNull(bounds);
        this.image = image;
        this.bounds = bounds;
    }

    public Bounds getBounds() {
        return bounds;
    }
    public void draw(GraphicsContext graphicsContext) {
        Helper.requireNonNull(graphicsContext);
        graphicsContext.drawImage(image, bounds.getX(), bounds.getY(),
                bounds.getWidth(), bounds.getHeight());
    }
}