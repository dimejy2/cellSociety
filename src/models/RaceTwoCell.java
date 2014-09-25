package models;

import java.util.List;
import java.util.Map;

public class RaceTwoCell extends RaceCell{

	public RaceTwoCell(int state) {
		super(state);
	}

	@Override
	public int getNextState(Map<Integer, List<Patch>> neighbors) {
		similarNeighbors = neighbors.get(2);
		return super.getNextState(neighbors);
	}

}
