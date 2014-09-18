package cellsociety_team05_controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javafx.scene.paint.Color;
import models.Cell;

//state 0 = dead, 1 = alive
public class glifeSimulation extends SimulationRules {

	@Override
	public void updateNextBoard(Cell cell,
			HashMap<Integer, ArrayList<Cell>> neighbourMap) {
		// TODO Auto-generated method stub
		int nextState = 0;

		if (!neighbourMap.get(1).isEmpty()){
			int numAlive = neighbourMap.get(1).size();
			if ((cell.getState() == 1) && (numAlive < 2)) {
				nextState = 0;
			} else if ((cell.getState() == 1) && (numAlive > 3))
				nextState = 0;
			else if ((cell.getState() == 0) && (numAlive == 3))
				nextState = 1;
			else
				nextState = cell.getState();	
			Color color;
			if(nextState == 0) {
				color = Color.WHITE;
			}
			else {
				color = Color.BLACK;
			}
		}
		Cell nextCell = cell.nextCell(cell.getRow(), cell.getColumn(), nextState);
		nextBoardCells[nextCell.getRow()][nextCell.getColumn()] = nextCell;
	}


}
