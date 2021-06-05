package game.bullets;

import game.Helper;
import game.bounds.Bounds;
import game.gameObjects.*;
import game.mans.MovableBase;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet extends MovableBase implements GameObject, Update {
    private float dx, dy, angle;
    private byte damage, speed;

    private Image image;
    private final BulletBounds bounds;

    public Bullet() {
        super(new BulletBounds());
        this.bounds = (BulletBounds) super.bounds;
    }

    public void initialize(Image image, int width, int height, int speed, int damage) {
        this.image = image;
        bounds.setWidth(width);
        bounds.setHeight(height);
        this.speed = (byte) speed;
        this.damage = (byte) damage;
    }

    public void draw(GraphicsContext graphicsContext) {
        Helper.requireNonNull(graphicsContext);
        double centerX = bounds.getCenterX();
        double centerY = bounds.getCenterY();
        graphicsContext.translate(centerX, centerY);
        graphicsContext.rotate(angle);
        graphicsContext.drawImage(image, -bounds.halfW, -bounds.halfH,
                bounds.getWidth(), bounds.getHeight());
        graphicsContext.rotate(-angle);
        graphicsContext.translate(-centerX, -centerY);
    }
    public Bounds getBounds() {
        return bounds;
    }

    public void update() {
        tryMoveX(dx);
        tryMoveY(dy);
    }

    public void shot(float x, float y) {
        x -= bounds.getX();
        y -= bounds.getY();

        double distance = Math.sqrt(x * x + y * y);
        double temp = Math.acos(y / distance);
        angle = (float) Math.toDegrees(x > 0 ? -temp : temp);
        dx = (float) (speed * x / distance);
        dy = (float) (speed * y / distance);
    }

    public byte getDamage() {
        return damage;
    }

    private static class BulletBounds extends Bounds {
        private float x, y;
        private byte width, height, halfW, halfH;

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
            this.width = (byte) width;
            this.halfW = (byte) (width / 2.0F);
        }

        public float getHeight() {
            return this.height;
        }
        public void setHeight(float height) {
            this.height = (byte) height;
            this.halfH = (byte) (height / 2.0F);
        }

        public float getCenterX() {
            return x + halfW;
        }
        public float getCenterY() {
            return y + halfH;
        }
    }
}
/*
package game.bullets;

import game.Helper;
import game.bounds.Bounds;
import game.gameObjects.*;
import game.mans.MovableBase;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet extends MovableBase implements GameObject, Update {
    private float speed, dx, dy;
    private double angle;
    private int damage;

    private Image image;
    private final BulletBounds bounds;

    public Bullet() {
        super(new BulletBounds());
        this.bounds = (BulletBounds) super.bounds;
    }

    public void initialize(Image image, int width, int height, float speed, int damage) {
        this.image = image;
        bounds.setWidth(width);
        bounds.setHeight(height);
        this.speed = speed;
        this.damage = damage;
    }

    public void draw(GraphicsContext graphicsContext) {
        Helper.requireNonNull(graphicsContext);
        double centerX = bounds.getCenterX();
        double centerY = bounds.getCenterY();
        graphicsContext.translate(centerX, centerY);
        graphicsContext.rotate(angle);
        graphicsContext.drawImage(image, -bounds.getHalfW(), -bounds.getHalfH(),
                bounds.getWidth(), bounds.getHeight());
        graphicsContext.rotate(-angle);
        graphicsContext.translate(-centerX, -centerY);
    }
    public Bounds getBounds() {
        return bounds;
    }

    public void update() {
        tryMoveX(dx);
        tryMoveY(dy);
    }

    public void shot(float x, float y) {
        x -= bounds.getX();
        y -= bounds.getY();

        double distance = (Math.sqrt(x * x + y * y));
        double temp = Math.acos(y / distance);
        angle = Math.toDegrees(x > 0 ? -temp : temp);
        dx = (float) (speed * x / distance);
        dy = (float) (speed * y / distance);
    }

    public int getDamage() {
        return damage;
    }

    static class BulletBounds extends Bounds {
        private float x, y;
        private int width, height, halfW, halfH;

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
            this.width = (int) width;
            this.halfW = (int) (width / 2.0F);
        }

        public float getHeight() {
            return this.height;
        }
        public void setHeight(float height) {
            this.height = (int) height;
            this.halfH = (int) (height / 2.0F);
        }

        public float getCenterX() {
            return x + halfW;
        }
        public float getCenterY() {
            return y + halfH;
        }

        public int getHalfW() {
            return halfW;
        }
        public int getHalfH() {
            return halfH;
        }
    }
}*/