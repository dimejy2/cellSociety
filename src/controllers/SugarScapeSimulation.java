package controllers;

import models.Patch;
import models.SugarPatch;

public class SugarScapeSimulation extends SimulationRules {
	private int[] xDelta ={0,0, 1 , -1}; 
	private int[] yDelta = {1,-1, 0, 0};
	@Override
	public void updateNextPatch(Patch patch) {
		// TODO Auto-generated method stub

		Patch maxPatch = new SugarPatch(0, 0,null,0); 
		if(patch.getCell() == null) {
			return;
		}
		int vision = (int)patch.getCell().getVision();
		System.out.println(vision);
		for(int i = 1; i <= vision ; i++ ){

			for(int j = 0; j < xDelta.length; j++){
				System.out.println("test");
				int xVisionAway = patch.getRow()+ vision*xDelta[j]; 
				int yVisionAway = patch.getColumn() + vision*yDelta[j];
				int [] patchPostion = {xVisionAway, yVisionAway}; 
				Patch tempPatch = myPatchGraph.get(patchPostion); 
				System.out.println(tempPatch);
				if(tempPatch.getResources() > maxPatch.getResources()) maxPatch = tempPatch;
			}
		}
		maxPatch.setCell(patch.getCell());
		//patch.clearCell();
		maxPatch.updateCell(maxPatch.getCell().getNextState(null));
	}


}
