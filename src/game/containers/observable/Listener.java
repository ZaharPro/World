package game.containers.observable;

public interface Listener<T> {
    void invoke(T action);
}