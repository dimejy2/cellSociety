package models;

public class CellFactory {
	public static Cell getCell(String criteria, int state) {
		if(criteria.equals("GameofLife")) {
			return new GameOfLifeCell(state);
		}
		else
			return null;
	}
}
