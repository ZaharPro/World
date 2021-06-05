package game.arm;

import game.Helper;
import game.World;
import game.bounds.Bounds;
import game.bullets.Bullet;
import game.bullets.BulletFunction;
import game.containers.container.ConcurrentSkipList;
import game.containers.container.Container;
import game.containers.observable.Listener;
import game.containers.tester.Testable;
import game.containers.tester.Tester;
import game.gameObjects.Drawable;
import game.gameObjects.Update;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collection;

public class Arm implements Drawable, Update, Testable<Bounds> {
    private final BulletList enabled, disabled;

    private final Container<Tester<Bounds>> testers;
    private final Container<ArmListener> listeners;

    private BulletFunction bulletFunction;

    public Arm(BulletFunction bulletFunction) {
        setBulletFunction(bulletFunction);
        enabled = new BulletList();
        disabled = new BulletList();
        testers = new ConcurrentSkipList<>();
        listeners = new ConcurrentSkipList<>();
    }

    public void setBulletFunction(BulletFunction bulletFunction) {
        Helper.requireNonNull(bulletFunction);
        this.bulletFunction = bulletFunction;
    }
    public BulletFunction getBulletFunction() {
        return bulletFunction;
    }

    public void draw(GraphicsContext graphicsContext) {
        Node node = enabled.first;
        while (node != null) {
            node.value.draw(graphicsContext);
            node = node.next;
        }
    }
    public void update() {
        Node node = enabled.first;
        while (node != null) {
            node.value.update();
            node = node.next;
        }
    }

    public int size() {
        return enabled.size;
    }

    public void shot(float x, float y, float x0, float y0) {
        Bullet bullet = createBullet();
        Bounds bounds = bullet.getBounds();
        bounds.setX(x);
        bounds.setY(y);
        bullet.shot(x0, y0);
    }

    private Bullet createBullet() {
        Bullet bullet;
        Node node;
        if (disabled.size == 0) {
            node = new Node();
            bullet = bulletFunction.accept(null);
            for (Tester<Bounds> tester : testers)
                bullet.addTester(tester);
            bullet.addListener(getTesterListener(bullet));
        } else {
            node = disabled.last;
            bullet = bulletFunction.accept(node.value);
            disabled.unlinkLast();
        }
        node.value = bullet;
        enabled.linkLast(node);
        return bullet;
    }

    private Listener<Tester<Bounds>> getTesterListener(Bullet bullet) {
        final byte[] countFail = {0};
        return action -> {
            for (Node node = enabled.first; node != null; node = node.next) {
                if (bullet == node.value) {
                    countFail[0]++;
                    if (action == World.BOUNDS_TESTER_2 || countFail[0] > 126) {
                        enabled.unlink(node);
                        disabled.linkLast(node);
                        countFail[0] = 0;
                    }
                    /*enabled.unlink(node);
                    disabled.linkLast(node);*/
                    for (ArmListener armListener : listeners)
                        armListener.invoke(bullet, action);
                    return;
                }
            }
        };
    }

    private void forEach(Listener<Bullet> listener) {
        forEach(enabled.first, listener);
        forEach(disabled.first, listener);
    }
    private void forEach(Node node, Listener<Bullet> listener) {
        while (node != null) {
            listener.invoke(node.value);
            node = node.next;
        }
    }

    public void addTester(Tester<Bounds> tester) {
        Helper.requireNonNull(tester);
        testers.add(tester);
        forEach(bullet -> bullet.addTester(tester));
    }
    public void removeTester(Tester<Bounds> tester) {
        Helper.requireNonNull(tester);
        testers.remove(tester);
        forEach(bullet -> bullet.removeTester(tester));
    }

    public void addListener(ArmListener failListener) {
        listeners.add(failListener);
    }
    public void removeListener(ArmListener failListener) {
        listeners.remove(failListener);
    }

    private static class BulletList {
        Node first, last;
        int size = 0;

        public void linkLast(Node newNode) {
            final Node last = this.last;
            newNode.initialize(last, null);
            this.last = newNode;
            if (last == null)
                first = newNode;
            else
                last.next = newNode;
            size++;
        }

        public void unlinkLast() {
            final Node prev = last.previous;
            last.previous = null;
            last = prev;
            if (prev == null)
                first = null;
            else
                prev.next = null;
            size--;
        }

        public void unlink(Node node) {
            final Node next = node.next;
            final Node prev = node.previous;

            if (prev == null) {
                first = next;
            } else {
                prev.next = next;
                node.previous = null;
            }

            if (next == null) {
                last = prev;
            } else {
                next.previous = prev;
                node.next = null;
            }
            size--;
        }

        public Node getNode(Object object) {
            for (Node node = first; node != null; node = node.next)
                if (object.equals(node.value))
                    return node;
            return null;
        }
    }
    private static class Node {
        public Node next, previous;
        public Bullet value;

        public void initialize(Node previous, Node next) {
            this.previous = previous;
            this.next = next;
        }
    }
}