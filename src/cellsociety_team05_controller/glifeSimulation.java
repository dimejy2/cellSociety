package cellsociety_team05_controller;

import java.util.List;

import models.Cell;
import models.GameOfLifeCell;
import models.Patch;


// state 0 = dead, 1 = alive
public class glifeSimulation extends SimulationRules {


	@Override
	public void updateNextPatch (Patch patch) {
		int nextState = 0;
		List<Patch> aliveNeighbors = patch.getNeighborMap().get(1);
		int numAlive=aliveNeighbors.size();
		if(patch.getCell() != null) {
			nextState = patch.getCell().getNextState(aliveNeighbors);
			patch.updateCell(nextState);
		}
		else {
			if (numAlive == 3)
				patch.setCell(new GameOfLifeCell(1));
		}
	}
}
