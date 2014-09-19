package cellsociety_team05_controller;
import java.util.ArrayList;
import java.util.HashMap;

import models.Cell;


public class FireSimulation extends SimulationRules {
	private int[] xDelta = {-1, 0 , 1, -1, 1, -1, 0 ,1};
	private int[] yDelta = {-1, -1, -1, 0, 0, 1, 1, 1};
	@Override
	public void updateNextBoard(Cell cell) {
		// TODO Auto-generated method stub
		int isDead = 0; 
		int isTree = 1; 
		int isBurning = 2;  
		int nextState = 0 ; 
	
	
		
		if ((cell.getState() == isBurning) && (cell.getResources() == 0) ) {
			cell.setState(isDead);
			cell.getCellView().setColor(stateToColorMap.get(nextState));
			nextBoardCells[cell.getRow()][cell.getColumn()] = cell;	
		}
		
		if ((!cell.getNeighborMap().get(isBurning).isEmpty()) && (cell.getState()!=isDead)){
			int numBurning = cell.getNeighborMap().get(isBurning).size(); 
			
			if ((cell.getState() == isTree) && (numBurning >= 1) && (chance.nextDouble() < myBoard.getProbablity())) nextState = isBurning;
			else nextState = isTree; 
		} 
			
			cell.setState(nextState);
			cell.getCellView().setColor(stateToColorMap.get(nextState));
			nextBoardCells[cell.getRow()][cell.getColumn()] = cell;	
			
			if(cell.getState() == isBurning) cell.incrementResources(-1);
	
	}
		
	
	

	public void saveNeighborStates(Cell cell) {
		super.saveNeighborStates(cell);
	}

}
