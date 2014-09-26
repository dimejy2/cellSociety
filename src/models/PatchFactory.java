package models;

import java.util.Map;

public class PatchFactory {

	public static Patch getPatch(String criteria, int row, int col, 
			Map <String, Double> resources, double cellDim) {
		
		if(criteria.equalsIgnoreCase("GameofLife")) {
			return new GameOfLifePatch(row, col, resources, cellDim);
		}
		
		else if(criteria.equalsIgnoreCase("FireSimulation")) {
			return new FireSimulationPatch(row, col,resources,cellDim);
		}
		
		else if(criteria.equalsIgnoreCase("Segregation")) {
			return new SegregationPatch(row, col, resources, cellDim);
		}
		
		else if(criteria.equalsIgnoreCase("WaTorWorld")) {
			return new WatorWorldPatch(row, col, resources, cellDim);
		}
		
		else if(criteria.equalsIgnoreCase("SugarScapeSimulation")) {
			return new SugarPatch(row, col, resources, cellDim);
		}
		
		else{
			return null;
		}
		
	}
}
