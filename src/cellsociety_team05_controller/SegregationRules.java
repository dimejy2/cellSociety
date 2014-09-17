package cellsociety_team05_controller;

import javafx.scene.paint.Color;
import models.Board;
import models.Cell;

//for game of life, 0 = empty, 1 = firstRace, 2 = secondRace
public class SegregationRules extends SimulationRules {

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
	//have to add a parameter called int percentage = 0.5
	//need to think of a way to calculate the total # of neighbours (8fornow)
	//after dealing with the corner cases
	public int nextState(int alikeNeighbours, int cellState) {
		if ((cellState == 1) && (alikeNeighbours/8 < 0.5)) {
			return 0;
		} else if ((cellState == 1) && (alikeNeighbours > 3)) {
			return 0;
		} else if ((cellState == 0) && (alikeNeighbours == 3)) {
			return 1;
		} else {
			return cellState;
		}
	}

	//just for segregation
	public void updateSBoard(Board board) {
		myCells = board.getCells();
		nextBoardCells = new Cell[board.getRow()][board.getColumn()];
		//change this deal with corner cases
		for (int i = 0; i < board.getRow(); i++) {
			for (int j = 0; j < board.getColumn(); j++) {
				Cell cell = myCells[i][j];
				for (int[] n : neighbourMap) {
					Cell neighbour = myCells[cell.getXPosition() + n[0]][cell
							.getYPosition() + n[1]];
					int liveNeighbours = checkCells(cell, neighbour);
					int nextState = nextState(liveNeighbours, cell.getState());
					// associate color with state maybe? method below
					Color color = stateToColor(nextState);
					Cell updatedCell = myCellController.updateCell(cell, nextState, color);
					nextBoardCells[i][j] = updatedCell;
				}
			}
		}
	}
	
	@Override
	protected Color stateToColor(int state) {
		if (state == 1) {
			return Color.RED;
		} 
		else if (state == 2) {
			return Color.BLUE;
		}
		else {
			return Color.WHITE;
		}
	}

}