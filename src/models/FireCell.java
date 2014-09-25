package models;

import java.util.List;
import java.util.Map;

public class FireCell extends Cell {

	public FireCell(int state) {
		super(state);
	}

	@Override
	public int getNextState(Map<Integer, List<Patch>> neighbors) {
		myPatch.updateResources(-1);
		int nextState;
		if(!myPatch.isAlive()) {
			nextState = 0;
		}
		else {
			nextState = myPatch.getCellsState();
		}
		return nextState;
	}

}
