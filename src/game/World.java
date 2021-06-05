package game;

import game.bounds.Bounds;
import game.bounds.IntBounds;
import game.containers.tester.Testable;
import game.containers.tester.Tester;
import game.gameObjects.Drawable;
import game.gameObjects.GameObject;
import game.gameObjects.GameObjectBase;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class World  {
    public static final Image NONE = Helper.getImage("field\\none.png");

    public static final Image ONE_0 = Helper.getImage("field\\one0.png");
    public static final Image ONE_1 = Helper.getImage("field\\one1.png");
    public static final Image ONE_2 = Helper.getImage("field\\one2.png");
    public static final Image ONE_3 = Helper.getImage("field\\one3.png");

    public static final Image TWO_0 = Helper.getImage("field\\two0.png");
    public static final Image TWO_1 = Helper.getImage("field\\two1.png");
    public static final Image TWO_2 = Helper.getImage("field\\two2.png");
    public static final Image TWO_3 = Helper.getImage("field\\two3.png");

    public static final Image THR_0 = Helper.getImage("field\\thr0.png");
    public static final Image THR_1 = Helper.getImage("field\\thr1.png");
    public static final Image THR_2 = Helper.getImage("field\\thr2.png");
    public static final Image THR_3 = Helper.getImage("field\\thr3.png");

    public static final Image FOU_0 = Helper.getImage("field\\four0.png");
    public static final Image FOU_1 = Helper.getImage("field\\four1.png");

    public static final Image FIVE = Helper.getImage("field\\five.png");

    public static Collection<Drawable> DRAWABLES = new ArrayList<>();
    public static Collection<Tester<Bounds>> TESTERS = new ArrayList<>();

    public static final Tester<Bounds> BOUNDS_TESTER_1;
    public static final Tester<Bounds> BOUNDS_TESTER_2;

    public static final Bounds GAME_FIELD;

    public static final int SIZE_X = 60;
    public static final int SIZE_Y = 60;

    public static int toX(int x) {
        return x * SIZE_X;
    }
    public static int toY(int y) {
        return y * SIZE_Y;
    }

    private World() {
    }

    static {
        GAME_FIELD = new IntBounds(0, 0, toX(15), toY(12));
        BOUNDS_TESTER_1 = value -> !GAME_FIELD.contains(value);
        BOUNDS_TESTER_2 = value -> !GAME_FIELD.intersects(value);

        DRAWABLES.add(new GameObjectBase(Helper.getImage("img\\background.png"), GAME_FIELD));
        createField();
        DRAWABLES = Collections.unmodifiableCollection(DRAWABLES);
        TESTERS = Collections.unmodifiableCollection(TESTERS);
    }

    private static void createField() {
        int width = toX(5) + 10;
        int height = toY(4) + 10;
//1
        addTester(nodeBounds(0, 0, width, height),
                createGameObject(TWO_0, 0, 0),
                createGameObject(FOU_1, 1, 0),
                createGameObject(THR_1, 2, 0),
                createGameObject(THR_3, 4, 0),
                createGameObject(THR_2, 0, 1),
                createGameObject(TWO_0, 2, 2),
                createGameObject(FOU_1, 3, 2),
                createGameObject(THR_1, 4, 2),
                createGameObject(THR_3, 1, 3),
                createGameObject(TWO_2, 2, 3)
        );
//2
        addTester(nodeBounds(5, 0, width, height),
                createGameObject(FOU_1, 5, 0),
                createGameObject(TWO_1, 6, 0),
                createGameObject(THR_2, 6, 1),
                createGameObject(THR_0, 8, 1),
                createGameObject(TWO_3, 8, 2),
                createGameObject(TWO_1, 9, 2),
                createGameObject(THR_0, 6, 3),
                createGameObject(THR_2, 9, 3)
        );
//3
        addTester(nodeBounds(10, 0, width, height),
                createGameObject(THR_3, 10, 0),
                createGameObject(ONE_0, 11, 0),
                createGameObject(FOU_1, 12, 0),
                createGameObject(THR_1, 13, 0),
                createGameObject(FOU_0, 11, 1),
                createGameObject(FOU_0, 11, 2),
                createGameObject(THR_0, 13, 2),
                createGameObject(THR_2, 11, 3),
                createGameObject(FOU_0, 13, 3)
        );
//4
        addTester(nodeBounds(0, 4, width, height),
                createGameObject(THR_3, 4, 4),
                createGameObject(THR_0, 0, 5),
                createGameObject(THR_0, 2, 5),
                createGameObject(THR_2, 0, 6),
                createGameObject(THR_2, 2, 6),
                createGameObject(THR_3, 4, 6)
        );
//5
        addTester(nodeBounds(5, 4, width, height),
                createGameObject(FOU_1, 5, 4),
                createGameObject(ONE_2, 6, 4),
                createGameObject(TWO_1, 7, 4),
                createGameObject(FOU_0, 7, 5),
                createGameObject(THR_3, 9, 5),
                createGameObject(THR_1, 5, 6),
                createGameObject(THR_2, 7, 6),
                createGameObject(FIVE, 9, 7)
        );
//6
        addTester(nodeBounds(10, 4, width, height),
                createGameObject(FOU_0, 13, 4),
                createGameObject(THR_1, 10, 5),
                createGameObject(TWO_3, 13, 5),
                createGameObject(THR_1, 14, 5),
                createGameObject(THR_3, 11, 7),
                createGameObject(THR_1, 12, 7),
                createGameObject(THR_0, 14, 7)
        );
//7
        addTester(nodeBounds(0, 8, width, height),
                createGameObject(THR_3, 1, 8),
                createGameObject(FOU_1, 2, 8),
                createGameObject(THR_1, 3, 8),
                createGameObject(THR_0, 0, 10),
                createGameObject(THR_3, 2, 10),
                createGameObject(FOU_1, 3, 10),
                createGameObject(FOU_1, 4, 10),
                createGameObject(THR_2, 0, 11)
        );
//8
        addTester(nodeBounds(5, 8, width, height),
                createGameObject(THR_3, 5, 8),
                createGameObject(FOU_1, 6, 8),
                createGameObject(TWO_1, 7, 8),
                createGameObject(ONE_3, 7, 9),
                createGameObject(THR_1, 8, 9),
                createGameObject(THR_1, 5, 10),
                createGameObject(THR_2, 7, 10),
                createGameObject(THR_3, 9, 11)
        );
//9
        addTester(nodeBounds(10, 8, width, height),
                createGameObject(THR_2, 14, 8),
                createGameObject(THR_3, 10, 9),
                createGameObject(FOU_1, 11, 9),
                createGameObject(THR_1, 12, 9),
                createGameObject(THR_0, 14, 10),
                createGameObject(FOU_1, 10, 11),
                createGameObject(THR_1, 11, 11),
                createGameObject(THR_3, 13, 11),
                createGameObject(TWO_2, 14, 11)
        );
    }
    private static Bounds nodeBounds(int x, int y, int width, int height) {
        return new IntBounds(toX(x) - 5, toY(y) - 5, width, height);
    }
    private static GameObject createGameObject(Image image, int x, int y) {
        return new GameObjectBase(image, new IntBounds(toX(x), toY(y), SIZE_X, SIZE_Y));
    }
    private static void addTester(Bounds nodeBounds, GameObject... gameObjects) {
        Bounds[] collection = new Bounds[gameObjects.length];
        Tester<Bounds> tester = value -> {
            if (nodeBounds.intersects(value))
                for (Bounds bounds : collection)
                    if (value.intersects(bounds))
                        return true;
            return false;
        };
        for (int i = 0; i < gameObjects.length; i++) {
            GameObject gameObject = gameObjects[i];
            collection[i] = gameObject.getBounds();
            DRAWABLES.add(gameObject);
        }
        TESTERS.add(tester);
        /*final boolean[] val = new boolean[1];
        Tester<Bounds> tester = value -> {
            if (nodeBounds.intersects(value)) {
                for (Bounds bounds : collection)
                    if (value.intersects(bounds))
                        return true;
                val[0] = true;
            } else {
                val[0] = false;
            }
            return false;
        };
        for (int i = 0; i < gameObjects.length; i++) {
            GameObject gameObject = gameObjects[i];
            Bounds bounds = gameObject.getBounds();
            collection[i] = bounds;
            Drawable drawable = context -> {
                if (val[0]) {
                    context.fillText("Hello", bounds.getX(), bounds.getCenterY());
                }
            };
            DRAWABLES.add(gameObject.thenDraw(drawable));
        }
        TESTERS.add(tester);*/
    }

    public static void draw(GraphicsContext graphicsContext) {
        for (Drawable drawable : DRAWABLES)
            drawable.draw(graphicsContext);
    }

    public static void addAllTesters(Testable<Bounds> testable) {
        for (Tester<Bounds> tester : TESTERS)
            testable.addTester(tester);
    }
}