package models;

import java.util.List;
import java.util.Map;

public class RaceTwoCell extends RaceCell{
	private static final int RACE_TWO = 2;

	public RaceTwoCell(int state) {
		super(state);
	}

	@Override
	public int getNextState(Map<Integer, List<Patch>> neighbors) {
		similarNeighbors = neighbors.get(RACE_TWO);
		return super.getNextState(neighbors);
	}

}
