package cellsociety_team05_controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Board;
import models.Cell;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.Random; 

public abstract class SimulationRules {

	protected Board myBoard;
	protected Cell[][] myCells;
	protected Cell[][] nextBoardCells;
	protected Board nextBoard;
	protected CellController myCellController;
	protected GridPane myGrid;
	protected Animation myAnimation;
	protected Slider mySpeedSlider;
	protected Map<Integer, Color> stateToColorMap;
	protected int myNumStates;
	protected int[] xDelta;
	protected int[] yDelta;
	protected Random chance; 

	public void init(GridPane grid, Board board, int numStates) {

		// Create a place to see the shapes
		myCellController = new DummyCellController(); // Your simulation's
														// CellController
		myBoard = board;
		myCells = myBoard.getCells();
		myGrid = grid;
		myNumStates = numStates;
		chance = new Random(); 
	}
	

	private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent evt) {
			checkCells();
			switchBoards();
		}
	};

	
	public abstract void updateNextBoard(Cell cell);

	
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
				myBoard.saveNeighborStates(cell);
			}
		}
		for (int row = 0; row < myCells.length; row++) {
			for (int column = 0; column < myCells[0].length; column++) {
				Cell cell = myCells[row][column];
				updateNextBoard(cell);				
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
	
	public void saveNeighborStates(Cell cell) {

		HashMap<Integer, ArrayList<Cell>> neighborStateMap = myBoard.genericStateMap(myNumStates);  
		for(int i=0;i<xDelta.length;i++) {
			if (!isOutOfBounds(cell, xDelta[i], yDelta[i])){	
				Cell neighborCell = myCells[cell.getRow() + xDelta[i]][cell.getColumn() + yDelta[i]];
				neighborStateMap.get(neighborCell.getState()).add(cell); 				
			}			
		}
		cell.setNeighborMap(neighborStateMap);
	}
	
	private boolean isOutOfBounds(Cell cell, int xDelta, int yDelta) {

		return (cell.getRow() + xDelta < 0 || cell.getRow() + xDelta > myCells.length - 1) 
				||(cell.getColumn() + yDelta < 0 || cell.getColumn() + yDelta > myCells[0].length -1 ) ; 
	}


	
}
