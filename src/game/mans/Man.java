package game.mans;

import game.gameObjects.GameObject;
import game.gameObjects.Update;

public interface Man extends GameObject, Movable, Update {
    boolean isMoved();
    void setUp(boolean up);
    void setDown(boolean down);
    void setLeft(boolean left);
    void setRight(boolean right);
}