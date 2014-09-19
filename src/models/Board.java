package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

public class Board{


	private int numRows;
	private int numColumns;
	private GridPane gridPane;
	private Cell[][] myCells;
	private static final int WINDOW_SIZE = 400;
	private ArrayList<Cell> emptyCells;
	private int cellDim;
	private int numStates;
	private double myProbability;
	private Map<Integer, ArrayList<Cell>> myStateMap;

	public Board(int row, int column, GridPane gridPane, int states) {
		numRows = row;
		numColumns = column;
		this.gridPane = gridPane;
		myCells = new Cell[row][column];
		numStates = states;
		cellDim = WINDOW_SIZE/Math.max(row, column);
		setUpGrid();

	}

	public void setProbability(double probability) {
		myProbability = probability;
	}
	
	public void addCell(Cell cell) {
		myCells[cell.getRow()][cell.getColumn()] = cell;
		gridPane.add(cell.getCellView().getRectangle(), cell.getColumn(),cell.getRow());
	}

	public void setUpGrid() {
		for(int i=0; i < numRows; i++) {
			gridPane.getColumnConstraints().add(new ColumnConstraints(cellDim));
		}
		for(int i=0; i< numColumns; i++) {
			gridPane.getRowConstraints().add(new RowConstraints(cellDim));
		}
	}

	public void checkEmptyCells() {
		emptyCells = new ArrayList<Cell>();
		for(int i=0; i<myCells.length; i++) {
			for(int j=0; j<myCells[1].length; i++) {
				if(myCells[i][j].getState() == 0) {
					emptyCells.add(myCells[i][j]);
				}
			}
		}
	}

	public ArrayList<Cell> getEmptyCells() {
		return emptyCells;
	}

	public Cell[][] getCells() {
		return myCells;
	}

	public void setCells(Cell[][] newCells) {
		myCells = newCells;
	}

	public void generateMyStateMap() {
		myStateMap = genericStateMap(numStates); 
		for(Cell[] subCellArray : myCells) {
			for(Cell cell : subCellArray ){
				myStateMap.get(cell.getState()).add(cell);
			}
		}

	}

	public int getNumStates() {
		return numStates;
	}

	public Map<Integer, ArrayList<Cell>> getStateMap(){
		return myStateMap; 		
	}

	public HashMap<Integer, ArrayList<Cell>> genericStateMap(int n){

		HashMap<Integer, ArrayList<Cell>> toReturn = new HashMap<>(); 
		for(int i =0; i < n; i++){
			toReturn.put(i, new ArrayList<Cell>()); 

		}

		return toReturn; 
	}

	public void saveNeighborStates(Cell cell) {
		
		HashMap<Integer, ArrayList<Cell>> neighborStateMap = genericStateMap(numStates);  
		int[] xDelta = {-1, 0 , 1, -1, 1, -1, 0 ,1};
		int[] yDelta = {-1, -1, -1, 0, 0, 1, 1, 1};
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
	
	public double getProbablity(){
		return  myProbability;
	}
}
