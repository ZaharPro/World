package game.containers.observable;

import game.containers.container.Container;
import game.containers.container.ContainerBase;

import java.util.Collection;

public class Notifier<T> implements Observable<T>, Listener<T> {
    protected final Container<Listener<T>> container;

    public Notifier(Collection<Listener<T>> collection) {
        container = new ContainerBase<>(collection);
    }

    public void invoke(T action) {
        for (Listener<T> listener : container)
            listener.invoke(action);
    }

    public void addListener(Listener<T> listener) {
        container.add(listener);
    }
    public void removeListener(Listener<T> listener) {
        container.remove(listener);
    }

    public int hashCode() {
        return container.hashCode();
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Notifier) {
            Notifier<?> notifier = (Notifier<?>) obj;
            return container.equals(notifier.container);
        } else {
            return false;
        }
    }
    public String toString() {
        return "Notifier{container=" + container + '}';
    }
}