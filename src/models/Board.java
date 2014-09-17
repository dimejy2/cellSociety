package models;

import java.util.ArrayList;
import javafx.scene.layout.GridPane;
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
	}

	public void buildBoard() {
		//state should be dealt with
		ArrayList<Integer> state = new ArrayList<Integer>();
		for (int x = 0; x < row; x++) {
			for (int y = 0; y < column; y++) {
				Cell cell = new Cell(x, y, state);
				myCells[x][y] = cell;
				gridPane.add(cell.getCellView().getRectangle(), x, y);
			}
		}
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

}

