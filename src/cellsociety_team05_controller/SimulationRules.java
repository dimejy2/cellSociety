package cellsociety_team05_controller;

import java.util.ArrayList;

import models.Cell;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

abstract class SimulationRules {
	
	private Board myBoard;
	private ArrayList<Cell> nextBoardCells;
	private CellController myCellController;
	
	public Scene init (Stage s, int width, int height, GridPane grid) {

		// Create a place to see the shapes
		Scene scene = new Scene(grid, width, height, Color.WHITE);
		myCellController = new CellController(); // Your simulation's CellController
		myBoard = board;

		return scene;
	}
	
    private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent evt) {
			updateCells(myBoard);
		}
	};
	
	protected abstract void checkCells(Board board);
	
	protected abstract void updateBoard(Board board);
	
	public KeyFrame start () {
		return new KeyFrame(Duration.millis(1000/60), oneFrame);
	}
}
