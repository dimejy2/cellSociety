package models;

import java.util.List;
import java.util.Map;

public class RaceCell extends Cell{

	public RaceCell(int state) {
		super(state);
	}

	@Override
	public int getNextState(Map<Integer, List<Patch>> neighbors) {
		int nextState =0;
		double numNeighbors =neighbors.get(1).size() + neighbors.get(2).size();
		System.out.println("similar:");
		System.out.println(similarNeighbors.size());
		System.out.println("neighbors");
		System.out.println(numNeighbors);

		if(numNeighbors<1) {
			return 0;
		}
		System.out.println(similarNeighbors.size() / numNeighbors);
		if ((similarNeighbors.size() / numNeighbors) >= (myPatch.getProbability())) {
			nextState = myState;
		}
		return nextState;
	}

}
