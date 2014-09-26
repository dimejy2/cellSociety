package models;

import java.util.List;
import java.util.Map;

public class RaceOneCell extends RaceCell {
	private static final int RACE_ONE = 1;

	public RaceOneCell(int state) {
		super(state);
	}

	@Override
	public int getNextState(Map<Integer, List<Patch>> neighbors) {
		similarNeighbors = neighbors.get(RACE_ONE);
		return super.getNextState(neighbors);
	}

}
