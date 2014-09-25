package cellsociety_team05_controller;

import java.util.ArrayList;
import java.util.HashMap;
import models.Cell;


public class FireSimulation extends SimulationRules {
    private int[] xDelta = { 0, -1, 1, 0 };
    private int[] yDelta = { -1, -0, 0, 1 };
    @Override
    public void updateNextBoard (Cell cell) {
        // TODO Auto-generated method stub
        int isDead = 0;
        int isTree = 1;
        int isBurning = 2;

        if (cell.getState() == isDead) {
            nextBoardCells[cell.getRow()][cell.getColumn()] = cell;
            return;
        }

        if (cell.getState() == isBurning) {
            cell.incrementResources(-1);

            if (cell.getResources() == 0) {
                cell.setState(isDead);
                cell.getCellView().setColor(stateToColorMap.get(isDead));
                nextBoardCells[cell.getRow()][cell.getColumn()] = cell;
                return;
            }
        }

        if (cell.getState() == isTree) {
            if (!cell.getNeighborMap().get(isBurning).isEmpty()) {

                if (chance.nextDouble() <= myBoard.getProbablity()) {
                    cell.setState(isBurning);
                    cell.getCellView().setColor(stateToColorMap.get(isBurning));
                }

            }

            nextBoardCells[cell.getRow()][cell.getColumn()] = cell;
        }

        nextBoardCells[cell.getRow()][cell.getColumn()] = cell;

    }

    @Override
    public void currentCellNeighbors (Cell cell) {
        // TODO Auto-generated method stub
        myBoard.saveNeighborStates(cell, xDelta, yDelta);

    }

}
