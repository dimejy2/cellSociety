package cellsociety_team05_controller;

import models.Cell;
import models.Patch;


// state 0 = dead, 1 = alive
public class glifeSimulation extends SimulationRules {
	 protected static final int[] xDelta = { -1, 0, 1, -1, 0, 1 };
	 protected static final int[] yDelta = { 1, 1, 1, 0, -1, 0 };

    @Override
    public void updateNextBoard (Patch patch) {
        int nextState = 0;
        patch.generateNeighborMap();
        System.out.println(getNeighborMap().get(1).size());
        if (!patch.getNeighborMap().get(1).isEmpty()) {
            int numAlive = patch.getNeighborMap().get(1).size();
            if ((patch.getCellsState() == 1) && (numAlive < 2)) {
                nextState = 0;
            }
            else if ((patch.getCellsState() == 1) && (numAlive > 3))
                nextState = 0;
            else if ((patch.getCellsState() == 0) && (numAlive == 3))
                nextState = 1;
            else nextState = patch.getCell().getState();
        }
        patch.getCell().setState(nextState);
//        patch.getCell().getCellView().setColor(stateToColorMap.get(nextState));
//        nextBoardCells.add(cell);
    }

//    void currentCellNeighbors (Cell cell) {
//        // TODO Auto-generated method stub
//        myBoard.saveNeighborStates(cell, xDelta, yDelta);
//
//    }

}
