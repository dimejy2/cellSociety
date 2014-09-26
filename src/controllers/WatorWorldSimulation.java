package controllers;

import models.Cell;
import models.Patch;


public class WatorWorldSimulation extends SimulationRules {


    @Override
	public void updateNextPatch(Patch patch) {

		if(invalidPatchChoices.contains(patch) || patch.getCell() == null) {
			return;
		}
		Cell currentCell = patch.getCell();
		patch.getCell().incrementFramesAlive();
		if(currentCell.canConsume(patch, patch.getNeighborMap().get(1), invalidPatchChoices)) {
			swapWithRandomCell(patch, patch.getNeighborMap().get(1));
			if(currentCell.canReplicate()) {
				patch.setCell(breed("WaTorWorld", currentCell));
			}
			else {
				patch.setCell(null);
			}
		}
		
		else if(currentCell.isStarved()) {
			patch.setCell(null);
			invalidPatchChoices.add(patch);
		}
		
		else if(currentCell.canMove(patch, patch.getNeighborMap().get(0), invalidPatchChoices)) {
			swapWithRandomCell(patch, patch.getNeighborMap().get(0));
			if(currentCell.canReplicate()) {
				patch.setCell(breed("WaTorWorld", currentCell));
			}
		}
    }
    
	private Cell breed(String s, Cell cell) {
		cell.resetBreedingTime();
		return cell.replicateCell(s);
	}

}
