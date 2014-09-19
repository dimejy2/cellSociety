package cellsociety_team05_controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javafx.scene.paint.Color;
import models.Cell;


// state 0 = empty, 1 = raceOne, 2 = raceTwo
public class SegregationSimulation extends SimulationRules {

    @Override
    public void updateNextBoard (Cell cell) {
        // myBoard.getEmptyCells();
        Random rand = new Random();

        HashMap<Integer, ArrayList<Cell>> neighbourMap = cell.getNeighborMap();
        // myBoard.checkEmptyCells();
        // ArrayList<Cell> emt = myBoard.getEmptyCells();
        myBoard.generateMyStateMap();
        Map<Integer, ArrayList<Cell>> map = myBoard.getStateMap();
        ArrayList<Cell> emptyCells = map.get(0);
        System.out.println(emptyCells.size());
        
        // TODO Auto-generated method stub
        int nextState = 0;
        // getting the number of neighbours, very inefficient now
        int numNeighbours = 0;
        for (int i = 0; i < neighbourMap.size(); i++) {
            numNeighbours += neighbourMap.get(i).size();
        }

        nextState = getNextState(cell, neighbourMap, numNeighbours);

        if (nextState == 0) {
            int randomIndex = rand.nextInt(emptyCells.size() + 0);
            Cell emptyCell = emptyCells.get(randomIndex);
            emptyCell.setState(cell.getState());
            emptyCell.getCellView().setColor(stateToColorMap.get(cell.getState()));
            nextBoardCells[emptyCell.getRow()][emptyCell.getColumn()] = emptyCell;
            map.get(0).remove(randomIndex);
        }

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
            if ((numAlike / numNeighbours) >= 0.4) { return cell.getState(); }
            return 0;
        }
        return 0;
    }

}
