package models;

import java.util.Map;

public class WatorWorldPatch extends Patch {

	public WatorWorldPatch(int row, int col, Map<String, Double> resources,
			double patchDim) {
		super(row, col, resources, patchDim);
	}

	@Override
	public void generateNeighborMap() {
		myNeighborMap = genericStateMap(3);
		super.generateNeighborMap();
	}
	
	@Override
	public void updateCell(int state) {
		
	}

}
