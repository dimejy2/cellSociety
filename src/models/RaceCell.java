package models;

import java.util.List;
import java.util.Map;

public class RaceCell extends Cell{
	private static final int RACE_ONE = 1;
	private static final int RACE_TWO = 2;

	public RaceCell(int state) {
		super(state, null);
	}

	@Override
	public int getNextState(Map<Integer, List<Patch>> neighbors) {
		int nextState =0;
		double numNeighbors =neighbors.get(RACE_ONE).size() + neighbors.get(RACE_TWO).size();

		if(numNeighbors<1) {
			return 0;
		}
		
		if ((similarNeighbors.size() / numNeighbors) >= (myPatch.getProbability())) {
			nextState = myState;
		}
		return nextState;
	}

}
