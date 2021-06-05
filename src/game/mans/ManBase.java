package game.mans;

import game.Helper;
import game.bounds.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ManBase extends MovableBase implements Man {
    protected Image image;
    protected boolean up, down, left, right, moved;

    private final float speedX, speedY;

    public ManBase(Image image, Bounds bounds, float speedX, float speedY) {
        super(bounds);
        if (speedX < 0 || speedY < 0)
            throw new IllegalArgumentException();
        this.image = image;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void draw(GraphicsContext graphicsContext) {
        Helper.requireNonNull(graphicsContext);
        graphicsContext.drawImage(image, bounds.getX(), bounds.getY(),
                bounds.getWidth(), bounds.getHeight());
    }

    public Bounds getBounds() {
        return bounds;
    }
    private void setMoved() {
        moved = up || down || left || right;
    }

    public boolean isMoved() {
        return moved;
    }
    public void update() {
        if (left) {
            tryMoveX(-speedX);
        } else if (right) {
            tryMoveX(speedX);
        }
        if (up) {
            tryMoveY(-speedY);
        } else if (down) {
            tryMoveY(speedY);
        }
    }

    private void checkUpDown() {
        if (up && down) {
            up = false;
            down = false;
        }
    }
    public void setUp(boolean up) {
        this.up = up;
        checkUpDown();
        setMoved();
    }
    public void setDown(boolean down) {
        this.down = down;
        checkUpDown();
        setMoved();
    }

    private void checkLeftRight() {
        if (left && right) {
            left = false;
            right = false;
        }
    }
    public void setLeft(boolean left) {
        this.left = left;
        checkLeftRight();
        setMoved();
    }
    public void setRight(boolean right) {
        this.right = right;
        checkLeftRight();
        setMoved();
    }
}