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
        Random rand = new Random();

        HashMap<Integer, ArrayList<Cell>> neighbourMap = cell.getNeighborMap();

        myBoard.generateMyStateMap();
        ArrayList<Cell> emptyCells = myBoard.getStateMap().get(0);


        int nextState = 0;
        // getting the number of neighbours, very inefficient now
        // don't count empty cells as your neighbours -> start from i = 1
        double numNeighbours = 0;
        for (int i = 1; i < neighbourMap.size(); i++) {
            numNeighbours += neighbourMap.get(i).size();
        }

        nextState = getNextState(cell, neighbourMap, numNeighbours);

        if (nextState == 0) {
            int randomIndex = rand.nextInt(emptyCells.size() + 0);
            Cell emptyCell = emptyCells.get(randomIndex);
            setNextCell(emptyCell, cell.getState());
            emptyCells.remove(randomIndex);
        }

        setNextCell(cell, nextState);

    }

    public void setNextCell (Cell cell, int nextState) {
        cell.setState(nextState);
        cell.getCellView().setColor(stateToColorMap.get(nextState));
        nextBoardCells[cell.getRow()][cell.getColumn()] = cell;
    }

    public int getNextState (Cell cell,
                             HashMap<Integer, ArrayList<Cell>> neighbourMap,
                             double numNeighbours) {
        double numAlike = 0.0;
        if (!neighbourMap.get(cell.getState()).isEmpty()) {
            numAlike = neighbourMap.get(cell.getState()).size();
            if ((numAlike / numNeighbours) >= myBoard.getProbablity()) {
                System.out.println(numAlike/numNeighbours);
                return cell.getState(); }
            return 0;
        }
        return 0;
    }

}
