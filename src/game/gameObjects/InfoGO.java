package game.gameObjects;

import game.Helper;
import game.bounds.Bounds;
import game.containers.observable.Listener;
import javafx.scene.canvas.GraphicsContext;

public class InfoGO implements GameObject, Listener<Bounds> {

    private boolean showText = false;
    private String text;
    private Bounds bounds;
    private int x, y;

    public InfoGO(Bounds bounds, String text, int x, int y) {
        setBounds(bounds);
        setText(text);
        setX(x);
        setY(y);
    }

    public void draw(GraphicsContext graphicsContext) {
        if (showText) {
            Helper.requireNonNull(graphicsContext);
            graphicsContext.fillText(text, x, y);
        }
    }
    public Bounds getBounds() {
        return bounds;
    }

    public void invoke(Bounds action) {
        showText = bounds.intersects(action);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setBounds(Bounds bounds) {
        Helper.requireNonNull(bounds);
        this.bounds = bounds;
    }
}