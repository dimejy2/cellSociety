package cellsociety_team05_controller;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import models.Board;
import models.Cell;

public class DummySimulationRules extends SimulationRules {

	@Override
	protected int checkCells(Cell cell, Cell neighbour) {
		// TODO Auto-generated method stub
		int liveNeighbours = 0;
		if (neighbour != null && (cell.getState() == neighbour.getState())) {
			liveNeighbours++;
		}
		return liveNeighbours;
	}

	@Override
	public int nextState(int alikeNeighbours) {
		if (alikeNeighbours > 3) {
			return 0; // some state that you want to turn it into (index #)
		}
		return 1; // your original state (index #)
	}
		
	
}