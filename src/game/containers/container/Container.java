package game.containers.container;

public interface Container<E> extends Iterable<E> {
    void add(E element);
    void remove(E element);
}