package cellsociety_team05_controller;

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
	SimulationRules mySimulation;

	
	@Override
	public void start(Stage s) throws Exception {
		// TODO Auto-generated method stub
		GridPane grid = new GridPane();
		s.setTitle("Cellular Automata");
		BoardView boardView = new BoardView(s, grid);
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
