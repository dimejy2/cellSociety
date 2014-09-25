package models;

import java.util.ArrayList;
import java.util.List;

public class GameOfLifeCell extends Cell {

	public GameOfLifeCell(int state) {
		super(state);
	}

	@Override
	public int getNextState(List<Patch> aliveNeighbors) {
		int numAlive = aliveNeighbors.size();
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
