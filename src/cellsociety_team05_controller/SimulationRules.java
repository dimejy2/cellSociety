package cellsociety_team05_controller;

import java.util.ArrayList;

import models.Board;
import models.Cell;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

abstract class SimulationRules {
	
	private Board myBoard;
	protected Cell[][] myCells; //i added
	protected Cell[][] nextBoardCells;
	protected CellController myCellController;
	protected static final int[][] neighbourMap = { { -1, -1 }, { 0, -1 },
		{ 1, -1 }, { -1, 0 }, { +1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 } };

	
	public Scene init (Stage s, int width, int height, GridPane grid) {

		// Create a place to see the shapes
		Scene scene = new Scene(grid, width, height, Color.WHITE);
		myCellController = new DummyCellController(); // Your simulation's CellController
		return scene;
	}
	
    private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent evt) {
//			checkCells(myBoard);
			updateBoard(myBoard);
		}
	};
	
	protected abstract int checkCells(Cell cell, Cell neighbour);
	
	public abstract int nextState(int alikeNeighbours);
	
	protected abstract void updateBoard(Board board);
	
	public KeyFrame start () {
		return new KeyFrame(Duration.millis(1000/60), oneFrame);
	}
}
