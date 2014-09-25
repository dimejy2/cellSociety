package models;

import java.util.List;
import java.util.Map;

public class RaceOneCell extends RaceCell {

	public RaceOneCell(int state) {
		super(state);
	}

	@Override
	public int getNextState(Map<Integer, List<Patch>> neighbors) {
		similarNeighbors = neighbors.get(1);
		return super.getNextState(neighbors);
	}

}
