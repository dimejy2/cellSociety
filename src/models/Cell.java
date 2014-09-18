package models;

import java.util.ArrayList;

import views.CellView;

public class Cell  {

	private int myRow;
	private int myColumn;
	private int myState; 
	private CellView myCellView;
	private int myResources;
	private int framesAlive;



	public Cell ( int row, int column, int state){
		myRow = row; 
		myColumn = column; 
		myState = state; 
		myCellView = new CellView(50, 50, state);
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
}
