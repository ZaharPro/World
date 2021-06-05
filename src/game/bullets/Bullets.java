package game.bullets;

import game.Helper;
import javafx.scene.image.Image;

public class Bullets {
    public static final Image ONE = Helper.getImage("img\\bul.png");
    public static final Image TWO = Helper.getImage("img\\bullet1.png");
    public static final Image THR = Helper.getImage("img\\bullet2.png");
    public static final Image FOU = Helper.getImage("img\\rocet.png");
    public static final Image FIV = Helper.getImage("img\\bullet4.png");

    public static Bullet one() {
        return one(null);
    }
    public static Bullet one(Bullet bullet) {
        if (bullet == null) bullet = new Bullet();
        bullet.initialize(ONE, 5, 20, 10, 10);
        return bullet;
    }

    public static Bullet two() {
        return one(null);
    }
    public static Bullet two(Bullet bullet) {
        if (bullet == null) bullet = new Bullet();
        bullet.initialize(TWO, 5, 20, 12, 20);
        return bullet;
    }

    public static Bullet three() {
        return one(null);
    }
    public static Bullet three(Bullet bullet) {
        if (bullet == null) bullet = new Bullet();
        bullet.initialize(THR, 5, 20, 14, 30);
        return bullet;
    }

    public static Bullet four() {
        return one(null);
    }
    public static Bullet four(Bullet bullet) {
        if (bullet == null) bullet = new Bullet();
        bullet.initialize(FOU, 5, 20, 16, 40);
        return bullet;
    }

    public static Bullet five() {
        return one(null);
    }
    public static Bullet five(Bullet bullet) {
        if (bullet == null) bullet = new Bullet();
        bullet.initialize(FIV, 5, 20, 18, 50);
        return bullet;
    }
}