package models;

import java.util.Map;

public class GameOfLifePatch extends Patch {

	public GameOfLifePatch(int row, int col, Map<String, Double> resources,
			double patchDim) {
		super(row, col, resources, patchDim);
	}

	@Override
	public void generateNeighborMap() {
		myNeighborMap = genericStateMap(2);
		super.generateNeighborMap();
	}

	@Override
	public void updateCell(int state) {
		if(state != myCell.getState()) {
			myCell = CellFactory.getCell("GameofLife", state);
		}
		if(myCell != null) {
			myCell.setPatch(this);
		}
	}
	

}
