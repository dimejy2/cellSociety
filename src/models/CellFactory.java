package models;

import java.util.Map;

public class CellFactory {
	public static Cell getCell(String criteria, int state, Map<String, Double> cellResources) {
		if(state == 0) {
			return null;
		}
		if(criteria.equals("GameofLife")) {
			return new GameOfLifeCell(state);
		}
		
		if(criteria.equals("FireSimulation")) {
			if(state == 1) {
				return new TreeCell(state);
			}
			else
				return new FireCell(state);
		}
		
		if(criteria.equals("Segregation")) {
			if(state ==1) {
				return new RaceOneCell(state);
			}
			else{
				return new RaceTwoCell(state);
			}
		}
		
		if(criteria.equals("WaTorWorld")) {
			if(state == 1) {
				return new FishCell(state, cellResources);
			}
			else {
				return new SharkCell(state, cellResources);
			}
		}
		
		else
			return null;
	}
}
