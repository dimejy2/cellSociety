package cellsociety_team05_controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javafx.scene.paint.Color;
import models.Cell;

//state 0 = dead, 1 = alive
public class glifeSimulation extends SimulationRules {

	@Override
	public void updateNextBoard(Cell cell) {
		int nextState = 0;

		if (!cell.getNeighborMap().get(1).isEmpty()){
			int numAlive = cell.getNeighborMap().get(1).size();
			if ((cell.getState() == 1) && (numAlive < 2)) {
				nextState = 0;
			} else if ((cell.getState() == 1) && (numAlive > 3))
				nextState = 0;
			else if ((cell.getState() == 0) && (numAlive == 3))
				nextState = 1;
			else
				nextState = cell.getState();	
		}
		cell.setState(nextState);
		cell.getCellView().setColor(stateToColorMap.get(nextState));
		nextBoardCells[cell.getRow()][cell.getColumn()] = cell;
	}


}