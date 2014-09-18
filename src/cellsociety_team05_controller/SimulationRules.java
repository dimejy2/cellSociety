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
	protected CellController myCellController;
	protected static final int[][] neighbourMap = { { -1, -1 }, { 0, -1 },
			{ 1, -1 }, { -1, 0 }, { +1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 } };
	

	public Scene init(Stage s, int width, int height, GridPane grid, Board board) {

		// Create a place to see the shapes
		Scene scene = new Scene(grid, width, height, Color.WHITE);
		myCellController = new DummyCellController(); // Your simulation's
														// CellController
		myBoard = board;
		return scene;
	}
	

	private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent evt) {
			// checkCells(myBoard);
			updateBoard(myBoard);
		}
	};

	
	/*
	 * Adds alive neighbors to a list.  
	 */
	protected void checkCells(Cell cell) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		ArrayList<Cell> empty = new ArrayList<Cell>();
		if(cell.getRow()==0);
	}
	
	protected void checkNeighbors(Cell cell) {
		int[] xDelta = {1, -1, 0, 0};
		int[] yDelta = {0, 0, 1, -1};
		for(int xcoord : xDelta) {
			for(int ycoord : yDelta) {
				if (isOutOfBounds(cell, xcoord, ycoord)) continue;
				
			}
		}
	}
	
	
	
	private boolean isOutOfBounds(Cell cell, int xDelta, int yDelta) {
		
		return (cell.getRow() + xDelta < 0 || cell.getRow() + xDelta > myCells.length - 1) 
				||(cell.getColumn() + yDelta < 0 || cell.getColumn() + yDelta > myCells[0].length -1 ) ; 
	}
	
	public abstract int nextState(int i, List<Cell> aliveNeighbours);

	public void updateBoard(Board board) {
		// TODO Auto-generated method stub
		myCells = board.getCells();
		nextBoardCells = new Cell[board.getRow()][board.getColumn()];
		for (int i = 0; i < board.getRow(); i++) {
			for (int j = 0; j < board.getColumn(); j++) {
				Cell cell = myCells[i][j];
				checkCells(cell);
				int nextState = nextState(cell.getState(), aliveNeighbours);
				Color color = cell.getCellView().stateToColor(nextState);
				updateCell(cell, nextState, color);
				
			}
		}
	}
	
	/*
	 * Based on number of neighbors, potentially change the state. Updating the color will occur
	 * in this method
	 */
	public Cell updateCell(Cell c, int nextState, Color color){
			// TODO Auto-generated method stub
			c.setState(nextState);
			c.getCellView().setColor(color);		
			return null;
		}
	


	public KeyFrame start() {
		return new KeyFrame(Duration.millis(1000), oneFrame);
	}
}
