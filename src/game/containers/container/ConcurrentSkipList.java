package game.containers.container;

import game.Helper;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ConcurrentSkipList<E> implements Container<E> {
    private static class Node<T> {
        Node<T> next, previous;
        T value;

        public Node(Node<T> previous, T value, Node<T> next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }
    private Node<E> first, last;

    public ConcurrentSkipList() {
    }

    public void add(E element) {
        Helper.requireNonNull(element);
        final Node<E> last = this.last;
        final Node<E> newNode = new Node<>(last, element, null);
        this.last = newNode;
        if (last == null)
            first = newNode;
        else
            last.next = newNode;
    }
    public void remove(E object) {
        if (object != null) {
            for (Node<E> node = first; node != null; node = node.next) {
                if (object.equals(node.value)) {
                    unlink(node);
                    return;
                }
            }
        }
    }

    private void unlink(Node<E> node) {
        final Node<E> next = node.next;
        final Node<E> prev = node.previous;

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

        node.value = null;
    }

    public Iterator<E> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<E> {
        private Node<E> lastReturned = null;
        private Node<E> next = first;

        public boolean hasNext() {
            return next != null;
        }

        public E next() {
            if (next == null)
                throw new NoSuchElementException();
            lastReturned = next;
            next = next.next;
            return lastReturned.value;
        }

        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();
            unlink(lastReturned);
            lastReturned = null;
        }
    }
}