package cellsociety_team05_controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	protected Cell[][] myCells;
	protected Cell[][] nextBoardCells;
	protected Board nextBoard;
	protected CellController myCellController;
	protected static final int[][] neighbourMap = { { -1, -1 }, { 0, -1 },
			{ 1, -1 }, { -1, 0 }, { +1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 } };
	protected GridPane myGrid;
	

	public Scene init(Stage s, int width, int height, GridPane grid, Board board) {

		// Create a place to see the shapes
		Scene scene = new Scene(grid, width, height, Color.WHITE);
		myCellController = new DummyCellController(); // Your simulation's
														// CellController
		myBoard = board;
		myCells = myBoard.getCells();
		myGrid = grid;
		return scene;
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
//		public Board(int row, int column, GridPane gridPane, int states) {
		myGrid.getChildren().clear();
		for(Cell[] subCellArray : nextBoardCells) {
			for(Cell cell : subCellArray ){
				myGrid.add(cell.getCellView().getRectangle(),cell.getRow(), cell.getColumn());
			}
		}
		myCells = nextBoardCells;
		myBoard.setCells(nextBoardCells);
	}
	
	public void checkCells() {
		// TODO Auto-generated method stub
		nextBoardCells = new Cell[myCells.length][myCells[0].length];
		for (int row = 0; row < myCells.length; row++) {
			for (int column = 0; column < myCells[0].length; column++) {
				Cell cell = myCells[row][column];
				HashMap<Integer, ArrayList<Cell>> neighborMap = myBoard.saveNeighborStates(cell);
				updateNextBoard(cell, neighborMap);				
			}
		}
	}
	
	/*
	 * Based on number of neighbors, potentially change the state. Updating the color will occur
	 * in this method
	 */
	


	public KeyFrame start() {
		return new KeyFrame(Duration.millis(1000), oneFrame);
	}
}
