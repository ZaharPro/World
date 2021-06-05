package game.mans;

import game.Helper;
import game.bounds.Bounds;
import javafx.scene.image.Image;

public class Mans {
    public static final Image ONE = Helper.getImage("img\\enem.png");
    public static final Image TWO = Helper.getImage("img\\image.png");
    public static final Image THR = Helper.getImage("img\\pause.png");
    public static final Image FOU = Helper.getImage("img\\player.png");
    public static final Image FIV = Helper.getImage("img\\iron.png");

    public static Man one(Bounds bounds, float speedX, float speedY) {
        return new ManBase(ONE, bounds, speedX, speedY);
    }
    public static Man two(Bounds bounds, float speedX, float speedY) {
        return new ManBase(TWO, bounds, speedX, speedY);
    }
    public static Man thr(Bounds bounds, float speedX, float speedY) {
        return new ManBase(THR, bounds, speedX, speedY);
    }
    public static Man fou(Bounds bounds, float speedX, float speedY) {
        return new ManBase(FOU, bounds, speedX, speedY);
    }
    public static Man fiv(Bounds bounds, float speedX, float speedY) {
        return new ManBase(FIV, bounds, speedX, speedY);
    }
}