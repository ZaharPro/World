package game.arm;

import game.bullets.BulletFunction;

public class ArmSized extends Arm {
    private final int limit;

    public ArmSized(BulletFunction bulletFunction, int limit) {
        super(bulletFunction);
        if (limit > 0)
            this.limit = limit;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public void shot(float x, float y, float x0, float y0) {
        if (size() < limit)
            super.shot(x, y, x0, y0);
    }
}