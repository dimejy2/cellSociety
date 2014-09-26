package controllers;

import models.Patch;


public class FireSimulation extends SimulationRules {

	@Override
	public void updateNextPatch (Patch patch) {

		int nextState;
		if(patch.getCell() != null) {
			nextState = patch.getCell().getNextState(patch.getNeighborMap());
			patch.updateCell(nextState);
		}	
	}
}
