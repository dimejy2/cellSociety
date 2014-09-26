package models;

import java.util.Map;

public class FireSimulationPatch extends Patch {

	public FireSimulationPatch(int row, int col, Map<String, Double> resources,
			double patchDim) {
		super(row, col, resources, patchDim);
		myProbability = resources.get("probability");
		myResources = resources.get("patchResources");
		myDecrement = resources.get("patchDecrement");
	}

	@Override
	public void generateNeighborMap() {
		myNeighborMap = genericStateMap(3);
		super.generateNeighborMap();
	}

	@Override
	public void updateCell(int state) {
		if(state != myCell.getState()) {
			myCell = CellFactory.getCell("FireSimulation", state, null);
		}
		if(myCell != null) {
			myCell.setPatch(this);
		}
	}

}
