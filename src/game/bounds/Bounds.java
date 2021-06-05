package game.bounds;

import game.Helper;

public abstract class Bounds {

    public abstract float getX();
    public abstract float getY();
    public abstract float getWidth();
    public abstract float getHeight();

    public abstract void setX(float x);
    public abstract void setY(float y);
    public abstract void setWidth(float width);
    public abstract void setHeight(float height);

    public void setAll(Bounds bounds) {
        Helper.requireNonNull(bounds);
        setX(bounds.getX());
        setY(bounds.getY());
        setWidth(bounds.getWidth());
        setHeight(bounds.getHeight());
    }

    public float getCenterX() {
        return getX() + getWidth() / 2;
    }
    public float getCenterY() {
        return getY() + getHeight() / 2;
    }

    public void moveX(float dx) {
        setX(getX() + dx);
    }
    public void moveY(float dy) {
        setY(getY() + dy);
    }

    public boolean isFill() {
        return isFill(getWidth(), getHeight());
    }
    private boolean isFill(float width, float height) {
        return width > 0.0F && height > 0.0F;
    }

    private boolean containsPoint(float x, float y) {
        float x0 = getX();
        float y0 = getY();
        return !(x < x0 || y < y0 ||
                x > (x0 + getWidth()) ||
                y > (y0 + getHeight()));
    }
    public boolean contains(float x, float y) {
        return isFill() && containsPoint(x, y);
    }
    public boolean contains(float x, float y, float width, float height) {
        return isFill() && isFill(width, height) &&
                containsPoint(x, y) && containsPoint(x + width, y + height);
    }
    public boolean contains(Bounds bounds) {
        return bounds != null && contains(bounds.getX(), bounds.getY(),
                        bounds.getWidth(), bounds.getHeight());
    }

    public boolean intersects(float x, float y, float width, float height) {
        if ((!isFill() && !isFill(width, height)))
            return false;
        float x0 = getX();
        float y0 = getY();
        return !(x + width < x0 ||
                y + height < y0 ||
                x > x0 + getWidth() ||
                y > y0 + getHeight());
    }
    public boolean intersects(Bounds bounds) {
        return bounds != null && intersects(bounds.getX(), bounds.getY(),
                        bounds.getWidth(), bounds.getHeight());
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Bounds) {
            Bounds bounds = (Bounds) object;
            return getX() == bounds.getX() &&
                    getY() == bounds.getY() &&
                    getWidth() == bounds.getWidth() &&
                    getHeight() == bounds.getHeight();
        } else {
            return false;
        }
    }
    public int hashCode() {
        long hash = 7L;
        hash = 31L * hash + Float.floatToIntBits(getX());
        hash = 31L * hash + Float.floatToIntBits(getY());
        hash = 31L * hash + Float.floatToIntBits(getWidth());
        hash = 31L * hash + Float.floatToIntBits(getHeight());
        return (int) (hash ^ hash >> 32);
    }
    public String toString() {
        return "Bounds{" + "x=" + getX() + ", y=" + getY() +
                ", width=" + getWidth() + ", height=" + getHeight() + '}';
    }
}