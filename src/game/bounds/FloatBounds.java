package game.bounds;

public class FloatBounds extends Bounds {
    private float x, y, width, height;

    public FloatBounds() {
    }
    public FloatBounds(float x, float y, float width, float height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }
    public FloatBounds(Bounds bounds) {
        setAll(bounds);
    }

    public float getX() {
        return this.x;
    }
    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }
    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return this.width;
    }
    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return this.height;
    }
    public void setHeight(float height) {
        this.height = height;
    }
}