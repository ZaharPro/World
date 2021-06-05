package game.mans;

import game.Helper;
import game.bounds.Bounds;
import game.bounds.FloatConsumer;

import game.containers.container.ConcurrentSkipList;
import game.containers.container.Container;
import game.containers.observable.Listener;
import game.containers.observable.Notifier;
import game.containers.tester.Tester;

import java.util.ArrayList;

public class MovableBase implements Movable {
    private final Container<Tester<Bounds>> container;
    private final Container<Listener<Tester<Bounds>>> listeners;

    protected final Bounds bounds;
    private final FloatConsumer setterX, setterY;

    public MovableBase(Bounds bounds) {
        Helper.requireNonNull(bounds);
        this.bounds = bounds;
        this.setterX = bounds::setX;
        this.setterY = bounds::setY;
        this.container = new ConcurrentSkipList<>();
        this.listeners = new ConcurrentSkipList<>();
    }

    private void trySetValue(float oldValue, float newValue, FloatConsumer setter) {
        setter.accept(newValue);
        for (Tester<Bounds> tester : container) {
            if (tester.test(bounds)) {
                setter.accept(oldValue);
                for (Listener<Tester<Bounds>> listener : listeners)
                    listener.invoke(tester);
                return;
            }
        }
    }

    public void trySetX(float x) {
        trySetValue(bounds.getX(), x, setterX);
    }
    public void trySetY(float y) {
        trySetValue(bounds.getY(), y, setterY);
    }
    public void tryMoveX(float dx) {
        float oldValue = bounds.getX();
        float newValue = oldValue + dx;
        trySetValue(oldValue, newValue, setterX);
    }
    public void tryMoveY(float dy) {
        float oldValue = bounds.getY();
        float newValue = oldValue + dy;
        trySetValue(oldValue, newValue, setterY);
    }

    public void addTester(Tester<Bounds> tester) {
        container.add(tester);
    }
    public void removeTester(Tester<Bounds> tester) {
        container.remove(tester);
    }

    public void addListener(Listener<Tester<Bounds>> listener) {
        this.listeners.add(listener);
    }
    public void removeListener(Listener<Tester<Bounds>> listener) {
        this.listeners.remove(listener);
    }
}