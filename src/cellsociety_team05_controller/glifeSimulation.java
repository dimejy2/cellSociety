package cellsociety_team05_controller;

import models.Cell;


// state 0 = dead, 1 = alive
public class glifeSimulation extends SimulationRules {
	 protected static final int[] xDelta = { -1, 0, 1, -1, 0, 1 };
	 protected static final int[] yDelta = { 1, 1, 1, 0, -1, 0 };

    @Override
    public void updateNextBoard (Cell cell) {
        int nextState = 0;

        if (!cell.getNeighborMap().get(1).isEmpty()) {
            int numAlive = cell.getNeighborMap().get(1).size();
            if ((cell.getState() == 1) && (numAlive < 2)) {
                nextState = 0;
            }
            else if ((cell.getState() == 1) && (numAlive > 3))
                nextState = 0;
            else if ((cell.getState() == 0) && (numAlive == 3))
                nextState = 1;
            else nextState = cell.getState();
        }
        cell.setState(nextState);
        cell.getCellView().setColor(stateToColorMap.get(nextState));
        nextBoardCells.add(cell);
    }

    void currentCellNeighbors (Cell cell) {
        // TODO Auto-generated method stub
        myBoard.saveNeighborStates(cell, xDelta, yDelta);

    }

}
