package models;

import java.util.Map;

public class SugarPatch  extends Patch{

	public SugarPatch(int row, int col, Map<String, Double> resources,
			double patchDim) {
		super(row, col, resources, patchDim);
	}

	@Override
	public void updateCell(int state) {
		if(state != myCell.getState()) {
			myCell = CellFactory.getCell("SugarScapeSimulation", state, null);
		}
		if(myCell != null) {
			myCell.setPatch(this);
		}
	}

	@Override
	public void generateNeighborMap() {
		myNeighborMap = genericStateMap(2);
		super.generateNeighborMap();
	}
}
