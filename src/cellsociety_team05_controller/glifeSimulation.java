package cellsociety_team05_controller;

import java.util.ArrayList;
import java.util.List;

import models.Cell;

//state 0 = dead, 1 = alive
public class glifeSimulation extends SimulationRules {

	@Override
	protected List<Cell> checkCells(Cell cell, Cell neighbour) {
		// TODO Auto-generated method stub
		List<Cell> aliveNeighbours = new ArrayList<Cell>();
		if (neighbour != null && (neighbour.getState() == 1)) {
			aliveNeighbours.add(neighbour);
		}
		return aliveNeighbours;
	}

	@Override
	public int nextState(int cellState, List<Cell> aliveNeighbours) {
		// TODO Auto-generated method stub
		if ((cellState == 1) && (aliveNeighbours.size() < 2)) {
			return 0;
		} else if ((cellState == 1) && (aliveNeighbours.size() > 3))
			return 0;
		else if ((cellState == 0) && (aliveNeighbours.size() == 3))
			return 1;
		else
			return 0;
	}


}