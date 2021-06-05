package game.mans;

import game.Helper;
import game.ImageArray;
import game.bounds.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Sprites {
    public static final Image ONE = Helper.getImage("img\\sprites.png");
    public static final Image TWO = Helper.getImage("img\\sprites.png");
    public static final Image THR = Helper.getImage("img\\sprites.png");
    public static final Image FOU = Helper.getImage("img\\sprites.png");
    public static final Image FIV = Helper.getImage("img\\sprites.png");

    public static Man one(Bounds bounds, float speedX, float speedY) {
        return spriteMan(bounds, speedX, speedY, ONE, 32, 64);
    }
    public static Man two(Bounds bounds, float speedX, float speedY) {
        return spriteMan(bounds, speedX, speedY, TWO, 32, 64);
    }
    public static Man thr(Bounds bounds, float speedX, float speedY) {
        return spriteMan(bounds, speedX, speedY, THR, 32, 64);
    }
    public static Man fou(Bounds bounds, float speedX, float speedY) {
        return spriteMan(bounds, speedX, speedY, FOU, 32, 64);
    }
    public static Man fiv(Bounds bounds, float speedX, float speedY) {
        return spriteMan(bounds, speedX, speedY, FIV, 32, 64);
    }

    public static Man spriteMan(Bounds bounds, float speedX, float speedY,
                                Image image, int imageW, int imageH) {
        ImageArray[] arrays = new ImageArray[4];
        for (int y = 0; y < 4; y++) {
            Image[] images = new Image[6];
            for (int x = 0; x < 6; x++) {
                images[x] = new WritableImage(image.getPixelReader(),
                        x * imageW, y * imageH, imageW, imageH);
            }
            arrays[y] = new ImageArray(images);
        }
        return new SpriteMan(bounds, arrays[2], arrays[0], arrays[1], arrays[3], speedX, speedY);
    }
}