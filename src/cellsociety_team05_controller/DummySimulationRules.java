package cellsociety_team05_controller;

import java.util.List;

import models.Cell;

public class DummySimulationRules extends SimulationRules {

	@Override
	protected List<Cell> checkCells(Cell cell, Cell neighbour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nextState(int cellState, List<Cell> aliveNeighbours) {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
//	public Cell updateCell(Cell c) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//

		
	
}