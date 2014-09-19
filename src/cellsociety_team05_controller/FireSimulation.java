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

		if(cell.getState()== isDead){ 
			nextBoardCells[cell.getRow()][cell.getColumn()] = cell;
			return; 
		}
		
		if(cell.getState() == isBurning ){
			cell.incrementResources(-1);
			
			if(cell.getResources() == 0){ 
				cell.setState(isDead);
				cell.getCellView().setColor(stateToColorMap.get(isDead));
				nextBoardCells[cell.getRow()][cell.getColumn()] = cell;
				return;
			}
		}

		
		if(cell.getState()== isTree){
			if( !cell.getNeighborMap().get(isBurning).isEmpty() ){
				 
				if( chance.nextInt(100) < 5){
					cell.setState(isBurning);
					cell.getCellView().setColor(stateToColorMap.get(isBurning));
				}
				
			}
			
			nextBoardCells[cell.getRow()][cell.getColumn()] = cell;
		}
		
		
		nextBoardCells[cell.getRow()][cell.getColumn()] = cell;

	}




	public void saveNeighborStates(Cell cell) {
		super.saveNeighborStates(cell);
	}

}