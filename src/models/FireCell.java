package models;

import java.util.List;
import java.util.Map;

public class FireCell extends Cell {
	private final static int EMPTY_PATCH = 0;

	public FireCell(int state) {
		super(state, null);
	}

	@Override
	public int getNextState(Map<Integer, List<Patch>> neighbors) {
		myPatch.decrementPatch();
		int nextState;
		if(!myPatch.isAlive()) {
			nextState = EMPTY_PATCH;
		}
		else {
			nextState = myState;
		}
		return nextState;
	}



}
