package cellsociety_team05_controller;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import models.Board;
import models.Cell;

public class DummySimulationRules extends SimulationRules {

	@Override
	protected int checkCells(Cell cell, Cell neighbour) {
		// TODO Auto-generated method stub
		int alikeNeighbours = 0;
		if (neighbour != null && (cell.getState() == neighbour.getState())) {
			alikeNeighbours++;
		}
		return alikeNeighbours;
	}

	@Override
	public int nextState(int alikeNeighbours) {
		if (alikeNeighbours > 3) {
			return 0; // some state that you want to turn it into (index #)
		}
		return 1; // your original state (index #)
	}

	// something like this maybe...:(
	public Color stateToColor(int state) {
		if (state == 0) {
			return Color.RED;
		}
		return Color.GREEN;
	}

	// deal with corner cases
	@Override
	protected void updateBoard(Board board) {
		// TODO Auto-generated method stub
		myCells = board.getCells();
		nextBoardCells = new Cell[board.getRow()][board.getColumn()];
		for (int i = 0; i < board.getRow(); i++) {
			for (int j = 0; j < board.getColumn(); j++) {
				Cell cell = myCells[i][j];
				for (int[] n : neighbourMap) {
					Cell neighbour = myCells[cell.getXPosition() + n[0]][cell
							.getYPosition() + n[1]];
					int alikeNeighbours = checkCells(cell, neighbour);
					int nextState = nextState(alikeNeighbours);
					// myCellController.updateCell(cell, nextState, color)
					// associate color with state maybe? below to another method
					// maybe
					Color color = stateToColor(nextState);
					myCellController.updateCell(cell, nextState, color);
				}
			}
		}
	}
}