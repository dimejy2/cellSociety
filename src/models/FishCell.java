package models;

import java.util.List;
import java.util.Map;

public class FishCell extends Cell{

	public FishCell(int state, Map<String, Double> cellResources) {
		super(state, cellResources);
		myBreedingTime = cellResources.get("breedTime");
		
	}

	@Override
	public int getNextState(Map<Integer, List<Patch>> neighbors) {
		return 0;
	}

}
