package models;

import java.util.Map;

public class SegregationPatch extends Patch {

	public SegregationPatch(int row, int col, Map<String, Double> resources,
			double patchDim) {
		
		super(row, col, resources, patchDim);
		myProbability = resources.get("probability");
	}


	@Override
	public void generateNeighborMap() {
		myNeighborMap = genericStateMap(3);
		super.generateNeighborMap();
	}

	@Override 
	public void updateCell(int state) {
		if(state == 0) {
			myCell = null;
		}
		else {
			setCell(CellFactory.getCell("Segregation", state, null));
		}
	}

}
