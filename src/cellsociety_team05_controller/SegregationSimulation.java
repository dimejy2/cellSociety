package cellsociety_team05_controller;

import models.Patch;

public class SegregationSimulation extends SimulationRules {


	@Override
	public void updateNextPatch(Patch patch) {

		if(invalidPatchChoices.contains(patch)) {
			return;
		}
		
		if(patch.getCell() != null) {
			int nextState = patch.getCell().getNextState(patch.getNeighborMap());
			if(nextState == 0 && myBoard.getStateMap().get(0).size()>0) {
				swapWithRandomCell(patch, myBoard.getStateMap().get(0));
				return;
			}
		}
	}
}
