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
		if (cell.getState() == isBurning) cell.setState(0);
		
		if ((!cell.getNeighborMap().get(2).isEmpty()) && (cell.getState()!=0)){
			int numBurning = cell.getNeighborMap().get(1).size();
			int catchesFire = numBurning 
			if ((cell.getState() == 1) && (numAlive < 2)) {
				nextState = 0;
			} else if ((cell.getState() == 1) && (numAlive > 3))
				nextState = 0;
			else if ((cell.getState() == 0) && (numAlive == 3))
				nextState = 1;
			else
				nextState = cell.getState();	
		
		}
		
		
	}

    @Override
    public void updateNextBoard (Cell cell) {
        // TODO Auto-generated method stub
        
    }

}
