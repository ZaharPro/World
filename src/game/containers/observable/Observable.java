package game.containers.observable;

public interface Observable<T> {
    void addListener(Listener<T> listener);
    void removeListener(Listener<T> listener);
}