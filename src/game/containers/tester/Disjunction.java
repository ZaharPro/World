package game.containers.tester;

import game.containers.container.Container;
import game.containers.container.ContainerBase;

import java.util.Collection;

public class Disjunction<T> implements Testable<T>, Tester<T> {
    private final Container<Tester<T>> container;

    public Disjunction(Collection<Tester<T>> collection) {
        this.container = new ContainerBase<>(collection);
    }

    public boolean test(T value) {
        for (Tester<T> tester : container)
            if (tester.test(value))
                return true;
        return false;
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
        } else if (obj instanceof Disjunction) {
            Disjunction<?> disjunction = (Disjunction<?>) obj;
            return container.equals(disjunction.container);
        } else {
            return false;
        }
    }
    public String toString() {
        return "Disjunction{container=" + container + '}';
    }
}