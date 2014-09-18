package cellsociety_team05_controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import views.BoardView;
import models.Board;
import models.Cell;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class SimulationRules {

	protected Board myBoard;
	protected Cell[][] myCells;
	protected Cell[][] nextBoardCells;
	protected Board nextBoard;
	protected CellController myCellController;
	protected static final int[][] neighbourMap = { { -1, -1 }, { 0, -1 },
			{ 1, -1 }, { -1, 0 }, { +1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 } };
	protected GridPane myGrid;
	protected Animation myAnimation;
	protected Slider mySpeedSlider;
	protected Map<Integer, Color> stateToColorMap;
	

	public void init(GridPane grid, Board board) {

		// Create a place to see the shapes
		myCellController = new DummyCellController(); // Your simulation's
														// CellController
		myBoard = board;
		myCells = myBoard.getCells();
		myGrid = grid;
	}
	

	private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent evt) {
			checkCells();
			switchBoards();
		}
	};

	
	public abstract void updateNextBoard(Cell cell, HashMap<Integer, ArrayList<Cell>> aliveNeighbours);

	
	public void switchBoards() {
		myGrid.getChildren().clear();
		for(Cell[] subCellArray : nextBoardCells) {
			for(Cell cell : subCellArray ){
				myGrid.add(cell.getCellView().getRectangle(), cell.getColumn(),cell.getRow());
			}
		}
		myCells = nextBoardCells;
		myBoard.setCells(nextBoardCells);
	}
	
	public void checkCells() {
		nextBoardCells = new Cell[myCells.length][myCells[0].length];
		for (int row = 0; row < myCells.length; row++) {
			for (int column = 0; column < myCells[0].length; column++) {
				Cell cell = myCells[row][column];
				HashMap<Integer, ArrayList<Cell>> neighborMap = myBoard.saveNeighborStates(cell);
				updateNextBoard(cell, neighborMap);				
			}
		}
	}
	
	public void setAnimation(Animation animation) {
		myAnimation = animation;
	}
	
	public void stop() {
		myAnimation.stop();
	}
	
	public void play() {
		myAnimation.play();
	}
	
	public void pause() {
		myAnimation.pause();
	}

	public KeyFrame frame() {
		return new KeyFrame(Duration.millis(1000), oneFrame);
	}
	
	public void setSpeedSlider(Slider slider) {
		mySpeedSlider = slider;
	}
	
	public void setColorMap(Map<Integer, Color> colorMap) {
		stateToColorMap = colorMap;
	}
}