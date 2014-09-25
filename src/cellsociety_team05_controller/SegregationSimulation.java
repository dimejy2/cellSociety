package cellsociety_team05_controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import models.Cell;
import models.CellFactory;
import models.Patch;


// state 0 = empty, 1 = raceOne, 2 = raceTwo
public class SegregationSimulation extends SimulationRules {


	@Override
	public void updateNextPatch(Patch patch) {

		if(invalidPatchChoices.contains(patch)) {
			return;
		}
		
		int nextState=0;

		if(patch.getCell() != null) {
			nextState = patch.getCell().getNextState(patch.getNeighborMap());
			if(nextState == 0 && myBoard.getStateMap().get(0).size()>0) {
				swapWithRandomCell(patch, myBoard.getStateMap().get(0));
				return;
			}
			else nextState = patch.getCellsState();
		}
		patch.updateCell(nextState);
	}

	protected void swapWithRandomCell(Patch patch, List<Patch> patchList) {

		int randIndex = rand.nextInt(patchList.size());
		Patch toSwitch = patchList.get(randIndex);
		while(invalidPatchChoices.contains(toSwitch)) {
			patchList.remove(randIndex);
			if(patchList.size()==0){
				return;
			}
			randIndex = rand.nextInt(patchList.size());
			toSwitch = patchList.get(randIndex);
		}
		
		toSwitch.setCell(patch.getCell());
		invalidPatchChoices.add(toSwitch);
		invalidPatchChoices.add(patch);
		patch.setCell(CellFactory.getCell("Segregation", 0));
	}

}