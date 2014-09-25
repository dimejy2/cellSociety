
package cellsociety_team05_controller;

import java.util.ArrayList;
import java.util.HashMap;

import models.Cell;
import models.Patch;


public class FireSimulation extends SimulationRules {


	@Override
	public void updateNextPatch (Patch patch) {
		// TODO Auto-generated method stub
		int isDead = 0;
		int isTree = 1;
		int isBurning = 2;

		int nextState;
		if(patch.getCell() != null) {
			nextState = patch.getCell().getNextState(patch.getNeighborMap());
			patch.updateCell(nextState);
		}	
	}
}
