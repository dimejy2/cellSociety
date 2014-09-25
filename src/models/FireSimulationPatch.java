package models;

import java.util.Map;

public class FireSimulationPatch extends Patch {

	public FireSimulationPatch(int row, int col, Map<String, Double> resources,
			double patchDim) {
		super(row, col, resources, patchDim);
		myProbability = resources.get("probability");
		myResources = resources.get("patchResources");
	}
	
	@Override
	public void generateNeighborMap() {
		myNeighborMap = genericStateMap(3);
		super.generateNeighborMap();
	}

	@Override
	public void updateCell(int state) {
		myCell = CellFactory.getCell("FireSimulation", state);
		if(myCell != null) {
			myCell.setPatch(this);
		}
	}

}
