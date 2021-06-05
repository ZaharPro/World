package game.containers.observable;

import game.Helper;
import game.containers.tester.Tester;

import java.util.Collection;

public class Filter<T> extends Notifier<T> {
    private final Tester<T> tester;

    public Filter(Collection<Listener<T>> collection, Tester<T> tester) {
        super(collection);
        Helper.requireNonNull(tester);
        this.tester = tester;
    }

    public void invoke(T action) {
        if (tester.test(action))
            super.invoke(action);
    }

    public int hashCode() {
        return container.hashCode();
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Filter) {
            Filter<?> notifier = (Filter<?>) obj;
            return container.equals(notifier.container) &&
                    tester.equals(notifier.tester);
        } else {
            return false;
        }
    }
    public String toString() {
        return "Filter{container=" + container + '}';
    }
}