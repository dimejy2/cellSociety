package cellsociety_team05_controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import models.Cell;


// state 0 = empty, 1 = raceOne, 2 = raceTwo
public class SegregationSimulation extends SimulationRules {
	private int[] xDelta = { -1, 0, 1, -1, 1, -1, 0, 1 };
	private int[] yDelta = { -1, -1, -1, 0, 0, 1, 1, 1 };

	@Override
	public void updateNextBoard (Cell cell) {
		int empty = 0;
		int one = 1;
		int two = 2;
		HashMap<Integer, ArrayList<Cell>> neighbourMap = cell.getNeighborMap();

		ArrayList<Cell> emptyCells = myBoard.getStateMap().get(0);

		int numNeighbours = 0;
		for (int i = 1; i < neighbourMap.size(); i++) {
			numNeighbours += neighbourMap.get(i).size();
		}



		int nextState = getNextState(cell, neighbourMap, numNeighbours);

		if(invalidCellChoices.contains(cell)) {
			return;
		}

		if (nextState == empty && myBoard.getStateMap().get(0).size()>0) { //want to move, and empty spots available
			int randomIndex = chance.nextInt(emptyCells.size()); //pick random empty cell's index
			Cell emptyCell = emptyCells.get(randomIndex); //store empty cell
			setNextCell(emptyCell, cell.getState()); //put in empty cell you wish to move to, stores newcell
			emptyCells.remove(randomIndex); // spot no longer empty, so remove
			invalidCellChoices.add(emptyCell);
		}

		setNextCell(cell, nextState); //add it to 
		invalidCellChoices.add(cell);

	}

	public void setNextCell (Cell newCell, int nextState) {

		newCell.setState(nextState); //update state of empty cell
		newCell.getCellView().setColor(stateToColorMap.get(nextState)); //change color of empty cell

		nextBoardCells.add(newCell); //add empty cell to nextboard
		System.out.println(nextBoardCells.size());
		//		System.out.println(nextBoardCells.size());
	}

	public int getNextState (Cell cell,
			HashMap<Integer, ArrayList<Cell>> neighbourMap,
			double numNeighbours) {
		double numAlike = 0.0;
		if (!neighbourMap.get(cell.getState()).isEmpty()) { //has neighbors of similar race
			numAlike = neighbourMap.get(cell.getState()).size(); //number of neighbors w/ same race
			if ((numAlike / numNeighbours) >= (myBoard.getProbablity())) { //if enough same race neighbors
				return cell.getState(); //stay
			}
			return 0; //else move
		}
		return 0;
	}

	@Override
	void currentCellNeighbors (Cell cell) {
		myBoard.saveNeighborStates(cell, xDelta, yDelta);

	}

}
