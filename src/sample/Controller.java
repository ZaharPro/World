package sample;

import game.Game;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    @FXML
    private Canvas canvas;

    @FXML
    void mouse(MouseEvent event) {
        game.mouse(event);
    }

    @FXML
    void pressed(KeyEvent event) {
        game.pressed(event);
    }

    @FXML
    void released(KeyEvent event) {
        game.released(event);
    }

    @FXML
    void initialize() {
        game = new Game();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                update();
            }
        };
        timer.schedule(timerTask, 0,30);
    }

    private Game game;

    private final Font font = Font.font("Times new roman", FontWeight.BOLD, 10);

    private void update() {
        game.move();
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.setFont(font);
        graphicsContext2D.setFill(Color.WHITE);
        game.draw(graphicsContext2D);
    }
}