package controllers;

import java.util.Random;

import views.BoardView;
import models.Board;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CellAutomata extends Application {
	private SimulationRules mySimulation;



	@Override
	public void start(Stage s) throws Exception {
		s.setTitle("Cellular Automata");
		BoardView boardView = new BoardView(s);
		Scene scene = boardView.getScene();
		s.setScene(scene);
		s.show();
	}

	/**
	 * Start the program.
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
}
