package models;

import java.util.Map;

public class PatchFactory {
	public static Patch getPatch(String criteria, int row, int col, 
			Map <String, Double> resources, double cellDim) {
		
		if(criteria.equals("GameofLife")) {
			return new GameOfLifePatch(row, col, resources, cellDim);
		}
		
		else if(criteria.equals("FireSimulation")) {
			return new FireSimulationPatch(row, col,resources,cellDim);
		}
		
		else if(criteria.equals("Segregation")) {
			return new SegregationPatch(row, col, resources, cellDim);
		}
		
		else{
			return null;
		}
		
	}
}
