package models;

import java.util.List;
import java.util.Map;

public class SharkCell extends Cell {
	private static final int FISH_CELL = 1;

	public SharkCell(int state, Map<String, Double> cellResources) {
		super(state, cellResources);
		myBreedingTime = cellResources.get("breedTime");
		myResources = cellResources.get("cellResources");
		decrementValue = cellResources.get("cellDecrement");
	}

	@Override
	public int getNextState(Map<Integer, List<Patch>> neighbors) {
		if(neighbors.get(FISH_CELL).size() > 0) {

		}
		return 0;
	}

	@Override
	public boolean isStarved() {
		myResources -= decrementValue;
		return myResources <= 0;
	}

	@Override
	public boolean canConsume(Patch patch, List<Patch> neighbors, List<Patch> invalidPatches) {
		return myPatch.checkSurroundingPatches(neighbors, invalidPatches) != null;
	}
}
