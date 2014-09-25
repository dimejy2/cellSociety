package models;

public abstract class GridObject {
	protected int myRow;
	protected int myColumn;
	
	public int getRow(){
		return myRow; 
	}

	public int getColumn(){
		return myColumn; 
	}

	public abstract int getCellDim();

}
