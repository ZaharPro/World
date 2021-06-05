package game.containers.tester;

public interface Testable<T>  {
    void addTester(Tester<T> tester);
    void removeTester(Tester<T> tester);
}