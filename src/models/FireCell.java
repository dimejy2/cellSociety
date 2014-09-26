package models;

import java.util.List;
import java.util.Map;

public class FireCell extends Cell {

	public FireCell(int state) {
		super(state, null);
	}

	@Override
	public int getNextState(Map<Integer, List<Patch>> neighbors) {
		myPatch.decrementPatch();
		int nextState;
		if(!myPatch.isAlive()) {
			nextState = 0;
		}
		else {
			nextState = myState;
		}
		return nextState;
	}



}
