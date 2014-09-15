package models;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board extends Canvas {

	private int row;
	private int column;
	private int cellSize;
	private GridPane gridPane;

	//might not need cellSize
	public Board(int row, int column, int cellSize, GridPane gridPane) {
		this.row = row;
		this.column = column;
		this.cellSize = cellSize;
		this.gridPane = gridPane;
	}

	public void buildBoard() {
		int numCol = column/cellSize;
		int numRow = row/cellSize;
		ArrayList<Integer> state = new ArrayList<Integer>();
		for (int x = 0; x < numCol; x++) {
			for (int y = 0; y < numRow; y++) {
				Cell cell = new Cell(x, y, state);
//				gridPane.add();
			}
		}
	}

	public void drawBoard() {
		this.getGraphicsContext2D().clearRect(0, 0, column,
				row);
		this.getGraphicsContext2D().setFill(Color.BLACK);
		for (int y = 0; y < column; y++) {
			for (int x = 0; x < row; x++) {
					drawCell(x, y);
				}
			}
		}

	private void drawCell(int x, int y) {
		this.getGraphicsContext2D().fillRect(x * cellSize, y * cellSize,
				cellSize, cellSize);
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
