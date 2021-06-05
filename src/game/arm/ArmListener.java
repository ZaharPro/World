package game.arm;

import game.bounds.Bounds;
import game.bullets.Bullet;
import game.containers.tester.Tester;

public interface ArmListener {
    void invoke(Bullet bullet, Tester<Bounds> tester);
}