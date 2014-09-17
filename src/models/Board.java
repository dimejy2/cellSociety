package models;

import java.util.ArrayList;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

public class Board{

	private int row;
	private int column;
	private GridPane gridPane;
	private Cell[][] myCells;

	// might not need cellSize
	public Board(int row, int column, GridPane gridPane) {
		this.row = row;
		this.column = column;
		this.gridPane = gridPane;
		myCells = new Cell[row][column];
		setUpGrid(gridPane, row, column);
	}
	
	public void addCell(Cell cell) {
		myCells[cell.getXPosition()][cell.getYPosition()] = cell;
		gridPane.add(cell.getCellView().getRectangle(),cell.getXPosition(), cell.getYPosition());
	}
	
	
	public void setUpGrid(GridPane grid, int width, int height) {
		for(int i=0; i < width; i++) {
			grid.getColumnConstraints().add(new ColumnConstraints(400/width));
		}
		for(int i=0; i< height; i++) {
			grid.getRowConstraints().add(new RowConstraints(400/height));
		}
	}
//	public void buildBoard() {
//		//state should be dealt with
//		ArrayList<Integer> state = new ArrayList<Integer>();
//		for (int x = 0; x < row; x++) {
//			for (int y = 0; y < column; y++) {
//				Cell cell = new Cell(x, y, state);
//				myCells[x][y] = cell;
//				gridPane.add(cell.getCellView().getRectangle(), x, y);
//			}
//		}
//	}

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

}

