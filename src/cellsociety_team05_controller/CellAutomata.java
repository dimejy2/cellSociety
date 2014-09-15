package cellsociety_team05_controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class CellAutomata extends Application {
	SimulationRules mySimulation;
	
	private void setUpGrid(GridPane grid, int width, int height) {
		for(int i=0; i < width; i++) {
			grid.getColumnConstraints().add(new ColumnConstraints(400/width));
		}
		for(int i=0; i< height; i++) {
			grid.getRowConstraints().add(new RowConstraints(400/height));
		}
	}
	
	@Override
	public void start(Stage s) throws Exception {
		// TODO Auto-generated method stub
		s.setTitle("Cellular Automata");
		mySimulation = new SimulationRules(); // Your simulation's class
//		Board board = createBoard(xml); For when we get XML working
		GridPane grid = new GridPane();
		setUpGrid(grid, 8, 8);
		Board board = new Board(grid);
		Scene scene = mySimulation.init(s, 400, 400, board, grid);
		s.setScene(scene);
		s.show();
		
		// sets the game's loop 
		KeyFrame frame = mySimulation.start();
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

}
