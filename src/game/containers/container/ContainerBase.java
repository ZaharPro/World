package game.containers.container;

import game.Helper;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ContainerBase<E> implements Container<E> {
    protected Collection<E> collection;

    public ContainerBase(Collection<E> collection) {
        Helper.requireNonNull(collection);
        this.collection = collection;
    }

    public void add(E element) {
        Helper.requireNonNull(element);
        collection.add(element);
    }
    public void remove(E element) {
        Helper.requireNonNull(element);
        collection.remove(element);
    }

    public Iterator<E> iterator() {
        return collection.iterator();
    }
    public void forEach(Consumer<? super E> action) {
        collection.iterator();
    }
    public Spliterator<E> spliterator() {
        return collection.spliterator();
    }

    public int hashCode() {
        return collection.hashCode();
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ContainerBase) {
            ContainerBase<?> containerBase = (ContainerBase<?>) obj;
            return collection.equals(containerBase.collection);
        } else {
            return false;
        }
    }
    public String toString() {
        return "ContainerBase{collection=" + collection + '}';
    }
}