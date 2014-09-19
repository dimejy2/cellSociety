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
		int burnProbability = 10; // some integer between 0 and 99

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
				 
				if( chance.nextInt(100) <= burnProbability){
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




	@Override
	protected void checkCells()  {
		nextBoardCells = new Cell[myCells.length][myCells[0].length];
		for (int row = 0; row < myCells.length; row++) {
			for (int column = 0; column < myCells[0].length; column++) {
				Cell cell = myCells[row][column];
				myBoard.saveNeighborStates(cell, x4Delta , y4Delta);
			}
		}
		for (int row = 0; row < myCells.length; row++) {
			for (int column = 0; column < myCells[0].length; column++) {
				Cell cell = myCells[row][column];
				updateNextBoard(cell);				
			}
		}
	}
}
