package game;

import javafx.scene.image.Image;

import java.io.InputStream;

public class Helper {
    private Helper() {
    }

    public static void requireNonNull(Object o) {
        if (o == null)
        throw new NullPointerException();
    }

    public static void requireNonNull(Object o, String message) {
        if (o == null)
            throw new NullPointerException(message);
    }

    public static Image getImage(String name) {
        InputStream stream = Helper.class.getClassLoader().getResourceAsStream(name);
        requireNonNull(stream);
        return new Image(stream);
    }
}