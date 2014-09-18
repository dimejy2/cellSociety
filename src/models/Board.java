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

	private int row;
	private int column;
	private GridPane gridPane;
	private Cell[][] myCells;
	private static final int WINDOW_SIZE = 400;
	private ArrayList<Cell> emptyCells;
	private int cellDim;
	private Map<Integer, Set<Cell>> myStateMap;

	// might not need cellSize
	public Board(int row, int column, GridPane gridPane, int states) {
		this.row = row;
		this.column = column;
		this.gridPane = gridPane;
		myCells = new Cell[row][column];
		cellDim = WINDOW_SIZE/Math.max(row, column);
		setUpGrid(gridPane);
		generatemyStateMap(); 
	}

	public void addCell(Cell cell) {
		myCells[cell.getRow()][cell.getColumn()] = cell;
		gridPane.add(cell.getCellView().getRectangle(),cell.getRow(), cell.getColumn());
	}

	public void setUpGrid(GridPane grid) {
		for(int i=0; i < row; i++) {
			grid.getColumnConstraints().add(new ColumnConstraints(cellDim));
		}
		for(int i=0; i< column; i++) {
			grid.getRowConstraints().add(new RowConstraints(cellDim));
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

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public void setColumn(int column) {
		this.column = column;
	}


	private void generatemyStateMap() {
		myStateMap = new HashMap<>(); 
		for(Cell[] subCellArray : myCells) {
			for(Cell cell : subCellArray ){
				if(!myStateMap.keySet().contains(cell.getState())){ 
					myStateMap.put(cell.getState(), new HashSet<Cell>()); 
					myStateMap.get(cell.getState()).add(cell); 
				}
				else{myStateMap.get(cell.getState()).add(cell); }
			}
		}

	}

	public Map<Integer, Set<Cell>> getStateMap(){
		return myStateMap; 		
	}
	
}
