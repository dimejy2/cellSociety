package models;

import java.util.List;
import java.util.Map;

public class GameOfLifeCell extends Cell {

	public GameOfLifeCell(int state) {
		super(state, null);
	}

	@Override
	public int getNextState(Map<Integer, List<Patch>> neighborsMap) {
		int numAlive = neighborsMap.get(1).size();
		int nextState;
		if (numAlive < 2) {
			nextState = 0;
		}
		else if (numAlive > 3)
			nextState = 0;
		
		else nextState = myState;
		
		return nextState;
	}
	
	

}
