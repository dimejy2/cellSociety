package models;

import java.util.ArrayList;
import java.util.HashMap;

import views.CellView;

public class Cell  {


	private int myRow;
	private int myColumn;
	private int myState; 
	private CellView myCellView;
	private int myResources;
	private int framesAlive;
	private double myCellDim;
	private HashMap<Integer, ArrayList<Cell>> myNeighborStateMap; 
	private int decrementValue;
	private int incrementValue;
	private int myReproductionTime;
	

	public Cell ( int row, int column, int state, double cellDim){
		myRow = row; 
		myColumn = column; 
		myState = state; 
		myCellDim = cellDim;
		myCellView = new CellView(myCellDim, myCellDim, state);
		framesAlive = 0;
	}

	public CellView getCellView() {
		return myCellView;
	}
	

	public void createCellView(int height, int state) {
		myCellView = new CellView(height, height, state);
	}

	public int getRow(){
		return myRow; 
	}

	public int getColumn(){
		return myColumn; 
	}

	public void setResources(int resources) {
		myResources = resources;
	}
	
	public void incrementResources(int change) {
		myResources += change;
	}

	public boolean isAlive() {
		if(myResources > 0) {
			return true;
		}
		return false;
	}

	public int getFramesAlive() {
		return framesAlive;
	}

	public void incrementFramesAlive() {
		framesAlive++;
	}

	public void resetFramesAlive() {
		framesAlive = 0;
	}

	public int getState(){
		return myState;
	}  

	public void setState(int state){
		myState = state;
	}
	
	public void setNeighborMap(HashMap<Integer, ArrayList<Cell>> neighborMapToSet){
		myNeighborStateMap = neighborMapToSet ; 
		
	}
	
	public int getResources() {
		return myResources;
	}
	
	public HashMap<Integer, ArrayList<Cell>> getNeighborMap(){
		return myNeighborStateMap; 
	}
	
	public double getCellDim() {
		return myCellDim;
	}
	public void setIncrementDecrementValues(int increment, int decrement) {
		incrementValue = increment;
		decrementValue = decrement;
	}
	
	public void setRow(int row) {
		myRow = row;
	}
	
	public void setColumn(int column) {
		myColumn = column;
	}
	
	public void setReproductionTime(int reproductionTime) {
		myReproductionTime = reproductionTime;
	}

	public Cell replicateCell(Cell cell, int state) {
		Cell newCell =  new Cell(cell.getRow(), cell.getColumn(), state, cell.getCellDim());
		return newCell;
	}
}
