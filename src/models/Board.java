package models;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board extends Canvas {

	private int row;
	private int column;
	private GridPane gridPane;
	private ArrayList<Cell> myCells;

	//might not need cellSize
	public Board(int row, int column, GridPane gridPane) {
		this.row = row;
		this.column = column;
		this.gridPane = gridPane;
		myCells = new ArrayList<>();
	}

	public void buildBoard() {

		ArrayList<Integer> state = new ArrayList<Integer>();
		for (int x = 0; x < row; x++) {
			for (int y = 0; y < column; y++) {
				Cell cell = new Cell(x, y, state);
				myCells.add(cell);
				gridPane.add(cell.getCellView().getRectangle(), x, y);
			}
		}
	}

	public ArrayList<Cell> getCells() {
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
