package game.gameObjects;

import game.Helper;
import javafx.scene.canvas.GraphicsContext;

public interface Drawable {
    void draw(GraphicsContext graphicsContext);

    default Drawable thenDraw(Drawable drawable) {
        Helper.requireNonNull(drawable);
        return context -> { this.draw(context); drawable.draw(context);};
    }
}