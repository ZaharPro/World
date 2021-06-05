package game.gameObjects;

import game.bounds.Bounds;
import javafx.scene.canvas.GraphicsContext;

public interface GameObject extends Drawable {
    void draw(GraphicsContext graphicsContext);

    Bounds getBounds();
}