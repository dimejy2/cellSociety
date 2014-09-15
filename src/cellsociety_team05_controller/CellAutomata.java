package cellsociety_team05_controller;

import java.util.Random;

import models.Board;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CellAutomata extends Application{
    Board board;
    AnimationTimer animationTimer;
    GridPane gridPane;

    public static void main(String... args) throws InterruptedException {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        board = new Board(100, 100, 10, gridPane);
        Group game = new Group(board);
        stage.setScene(new Scene(game));
        stage.show();

        animationTimer = new AnimationTimer() {
            public void handle(long arg0) {
                gameLoop();
            }
        };

        animationTimer.start();
    }

    private void gameLoop() {
        board.drawBoard();
    }

}
