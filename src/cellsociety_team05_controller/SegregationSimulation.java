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
    public void updateNextBoard (Cell cell,
                                 HashMap<Integer, ArrayList<Cell>> neighbourMap) {
        myBoard.getEmptyCells();
        
        // TODO Auto-generated method stub
        int nextState = 0;
        // getting the number of neighbours, very inefficient now
        int numNeighbours = 0;
        for (int i = 0; i < neighbourMap.size(); i++) {
            numNeighbours += neighbourMap.get(i).size();
        }

        if (cell.getState() == 1) {
            if (!neighbourMap.get(1).isEmpty()) {
                int numAlike = neighbourMap.get(1).size();
                // need to change so that 0.5 is a variable
                if ((numAlike / numNeighbours) >= 0.5) {
                    nextState = 1;
                }
                else {
                    nextState = 0;
                    //move to an empty cell
                }

            }
        }

        if (cell.getState() == 2) {
            if (!neighbourMap.get(2).isEmpty()) {
                int numAlike = neighbourMap.get(2).size();
                // need to change so that 0.5 is a variable
                if ((numAlike / numNeighbours) >= 0.5) {
                    nextState = 1;
                }
                else {
                    nextState = 0;
                    // move to an empty cell
                }

            }
        }

        else nextState = cell.getState();

        Color color;
        if (nextState == 0) {
            color = Color.WHITE;
        }
        if (nextState == 0) {
            color = Color.BLUE;
        }
        else {
            color = Color.RED;
        }
        Cell nextCell = cell.nextCell(cell.getRow(), cell.getColumn(), nextState);
        nextBoardCells[nextCell.getRow()][nextCell.getColumn()] = nextCell;
    }
}
