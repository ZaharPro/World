package game.bounds;

public class IntBounds extends Bounds {
    private int x, y, width, height;

    public IntBounds() {
    }
    public IntBounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public IntBounds(Bounds bounds) {
        setAll(bounds);
    }

    public float getX() {
        return this.x;
    }
    public void setX(float x) {
        this.x = (int) x;
    }

    public float getY() {
        return this.y;
    }
    public void setY(float y) {
        this.y = (int) y;
    }

    public float getWidth() {
        return this.width;
    }
    public void setWidth(float width) {
        this.width = (int) width;
    }

    public float getHeight() {
        return this.height;
    }
    public void setHeight(float height) {
        this.height = (int) height;
    }
}