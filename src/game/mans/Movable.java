package game.mans;

import game.bounds.Bounds;
import game.containers.observable.Listener;
import game.containers.observable.Observable;
import game.containers.tester.Testable;
import game.containers.tester.Tester;

public interface Movable extends Testable<Bounds>, Observable<Tester<Bounds>> {
    void trySetX(float x);
    void trySetY(float y);

    void tryMoveX(float dx);
    void tryMoveY(float dy);
}