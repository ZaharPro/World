package game;

import game.arm.Arm;
import game.arm.ArmSized;
import game.arm.ArmListener;
import game.bounds.Bounds;
import game.bounds.IntBounds;
import game.bullets.Bullet;
import game.bullets.Bullets;
import game.containers.observable.Listener;
import game.containers.tester.Tester;
import game.gameObjects.Drawable;
import game.gameObjects.Update;
import game.mans.Man;
import game.mans.Mans;
import game.mans.Sprites;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Man player;
    private final Arm arm;
    private final List<Enemy> enemies;

    public Game() {
        Bounds playerBounds = startManBounds(1, 1);
        Man player = Sprites.one(playerBounds, 5, 5);
        World.addAllTesters(player);
        player.addTester(World.BOUNDS_TESTER_1);
        this.player = player;

        Tester<Bounds> playerIntersection = playerBounds::intersects;
        ArmListener onPlayerHit = new ArmListener() {
            int heal = 500;
            @Override
            public void invoke(Bullet bullet, Tester<Bounds> tester) {
                if (playerIntersection == tester) {
                    heal -= bullet.getDamage();
                    System.out.println("" + heal);
                    if (heal <= 0)
                        System.out.println("GameOver");
                }
            }
        };

        List<Enemy> enemies = new ArrayList<>(12);
        for (int i = 0; i < 3; i++) {
            Man man = Mans.one(startManBounds(3, 9), 2, 2);
            Arm arm = new ArmSized(Bullets::one, 3);
            enemies.add(new Enemy(man, arm, playerBounds));
        }
        for (int i = 0; i < 5; i++) {
            Man man = Mans.two(startManBounds(11, 6), 3, 3);
            Arm arm = new ArmSized(Bullets::three, 4);
            enemies.add(new Enemy(man, arm, playerBounds));
        }
        for (int i = 0; i < 4; i++) {
            Man man = Mans.fiv(startManBounds(3, 3), 4, 4);
            Arm arm = new ArmSized(Bullets::five, 5);
            enemies.add(new Enemy(man, arm, playerBounds));
        }
        for (Enemy enemy : enemies) {
            Man manEnemy = enemy.man;
            World.addAllTesters(manEnemy);
            manEnemy.addTester(World.BOUNDS_TESTER_1);
            manEnemy.addListener(action -> enemy.changeDirection());

            Arm enemyArm = enemy.arm;
            enemyArm.addListener(onPlayerHit);
            World.addAllTesters(enemyArm);
            enemyArm.addTester(World.BOUNDS_TESTER_2);
            enemyArm.addTester(playerIntersection);
        }
        this.enemies = enemies;

        Arm playerArm = new ArmSized(Bullets::one, 5);
        World.addAllTesters(playerArm);
        playerArm.addTester(World.BOUNDS_TESTER_2);
        for (Enemy enemy : enemies) {
            Bounds bounds = enemy.man.getBounds();
            Tester<Bounds> enemyIntersection = bounds::intersects;
            playerArm.addTester(enemyIntersection);

            ArmListener onEnemyHit = new ArmListener() {
                @Override
                public void invoke(Bullet bullet, Tester<Bounds> action) {
                    if (enemyIntersection == action) {
                        enemies.remove(enemy);
                        playerArm.removeTester(enemyIntersection);
                        playerArm.removeListener(this);
                    }
                }
            };
            playerArm.addListener(onEnemyHit);
        }
        this.arm = playerArm;
    }

    public static Bounds startManBounds(int x, int y) {
        return new IntBounds(World.toX(x) + 1, World.toY(y) + 1,
                World.SIZE_X * 2 / 3, World.SIZE_Y * 2 / 3);
    }
    private static boolean nextBoolean(int current) {
        return (int) (Math.random() * current) == 3;
    }

    public void move() {
        player.update();
        arm.update();
        for (Enemy enemy : enemies) {
            enemy.update();
            if (nextBoolean(50))
                enemy.shot();
            if (nextBoolean(60))
                enemy.changeDirection();
        }
    }
    public void draw(GraphicsContext graphicsContext) {
        World.draw(graphicsContext);
        player.draw(graphicsContext);
        arm.draw(graphicsContext);
        for (Enemy enemy : enemies)
            enemy.draw(graphicsContext);
    }

    public void pressed(KeyEvent event) {
        setDirection(event, true);
    }
    public void released(KeyEvent event) {
        setDirection(event, false);
    }
    private void setDirection(KeyEvent event, boolean value) {
        switch (event.getCode()) {
            case W:
                player.setUp(value);
                break;
            case S:
                player.setDown(value);
                break;
            case A:
                player.setLeft(value);
                break;
            case D:
                player.setRight(value);
                break;
        }
    }

    public void mouse(MouseEvent event) {
        Bounds bounds = player.getBounds();
        float x0 = (float) event.getX();
        float y0 = (float) event.getY();
        arm.shot(bounds.getCenterX(), bounds.getCenterY(), x0, y0);
    }

    private static class Enemy implements Drawable, Update {
        Bounds target;
        Man man;
        Arm arm;

        public Enemy(Man man, Arm arm, Bounds bounds) {
            this.man = man;
            this.arm = arm;
            this.target = bounds;
        }

        public void shot() {
            Bounds bounds = man.getBounds();
            float x = bounds.getCenterX();
            float y = bounds.getCenterY();
            arm.shot(x, y, target.getCenterX(), target.getCenterY());
        }
        public void changeDirection() {
            int index = (int) (Math.random() * 4);
            switch (index) {
                case 0:
                    man.setUp(true);
                    break;
                case 1:
                    man.setDown(true);
                    break;
                case 2:
                    man.setLeft(true);
                    break;
                case 3:
                    man.setRight(true);
                    break;
            }
        }

        public void update() {
            man.update();
            arm.update();
        }
        public void draw(GraphicsContext graphicsContext) {
            man.draw(graphicsContext);
            arm.draw(graphicsContext);
        }
    }
}