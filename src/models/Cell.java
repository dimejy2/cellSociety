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

	public void updateResources(int change) {
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
	
	public Cell nextCell(int row, int column, int nextState) {
		return new Cell(row, column, nextState, myCellDim);
	}

	public void setNeighborMap(HashMap<Integer, ArrayList<Cell>> neighborMapToSet){
		myNeighborStateMap = neighborMapToSet ; 
		
	}
	
	public HashMap<Integer, ArrayList<Cell>> getNeighborMap(){
		return myNeighborStateMap; 
	}
}
