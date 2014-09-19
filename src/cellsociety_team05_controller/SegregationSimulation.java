package cellsociety_team05_controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javafx.scene.paint.Color;
import models.Cell;


// state 0 = empty, 1 = raceOne, 2 = raceTwo
public class SegregationSimulation extends SimulationRules {

    @Override
    public void updateNextBoard (Cell cell) {
        // myBoard.getEmptyCells();

        HashMap<Integer, ArrayList<Cell>> neighbourMap = cell.getNeighborMap();

        // TODO Auto-generated method stub
        int nextState = 0;
        // getting the number of neighbours, very inefficient now
        int numNeighbours = 0;
        for (int i = 0; i < neighbourMap.size(); i++) {
            numNeighbours += neighbourMap.get(i).size();
        }
        
        nextState = getNextState(cell, neighbourMap, numNeighbours);

        cell.setState(nextState);
        cell.getCellView().setColor(stateToColorMap.get(nextState));
        nextBoardCells[cell.getRow()][cell.getColumn()] = cell;

    }

    public int getNextState (Cell cell,
                             HashMap<Integer, ArrayList<Cell>> neighbourMap,
                             int numNeighbours) {
        double numAlike = 0.0;
        if (!neighbourMap.get(cell.getState()).isEmpty()) {
            numAlike = neighbourMap.get(cell.getState()).size();
            // need to change so that 0.5 is a variable
            if ((numAlike / numNeighbours) >= 0.5) { return cell.getState(); }
            return 0;
        }
        return cell.getState();
    }

}
