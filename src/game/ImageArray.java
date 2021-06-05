package game;

import javafx.scene.image.Image;

public class ImageArray {
    private final Image[] images;
    private int index = 0;

    public ImageArray(Image[] images) {
        if (images == null || images.length == 0)
            throw new IllegalArgumentException();
        this.images = images;
    }

    public void reset() {
        index = 0;
    }

    public void next() {
        index++;
        if (index == images.length)
            reset();
    }

    public Image getImage() {
        return images[index];
    }
}