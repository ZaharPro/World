package game.containers.tester;

import game.containers.container.Container;
import game.containers.container.ContainerBase;

import java.util.Collection;

public class Conjunction<T> implements Testable<T>, Tester<T> {
    private final Container<Tester<T>> container;

    public Conjunction(Collection<Tester<T>> collection) {
        this.container = new ContainerBase<>(collection);
    }

    public boolean test(T value) {
        for (Tester<T> tester : container)
            if (!tester.test(value))
                return false;
        return true;
    }

    public void addTester(Tester<T> tester) {
        container.add(tester);
    }
    public void removeTester(Tester<T> tester) {
        container.remove(tester);
    }

    public int hashCode() {
        return container.hashCode();
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Conjunction) {
            Conjunction<?> conjunction = (Conjunction<?>) obj;
            return container.equals(conjunction.container);
        } else {
            return false;
        }
    }
    public String toString() {
        return "Conjunction{container=" + container + '}';
    }
}