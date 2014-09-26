package models;

import java.util.List;
import java.util.Map;

public class GameOfLifeCell extends Cell {
	private static final int EMPTY_PATCH = 0;
	private static final int ALIVE_CELL = 1;

	public GameOfLifeCell(int state) {
		super(state, null);
	}

	@Override
	public int getNextState(Map<Integer, List<Patch>> neighborsMap) {
		int numAlive = neighborsMap.get(ALIVE_CELL).size();
		int nextState;
		if (numAlive < 2) {
			nextState = EMPTY_PATCH;
		}
		else if (numAlive > 3)
			nextState = EMPTY_PATCH;
		
		else nextState = myState;
		
		return nextState;
	}
	
	

}
