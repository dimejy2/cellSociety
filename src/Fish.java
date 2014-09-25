

import java.util.ArrayList;
import models.Cell;


public class Fish extends PredatorPreySimulation {

    @Override
    public void updateNextBoard (Cell cell) {

        ArrayList<Cell> emptyNeighbors = cell.getNeighborMap().get(empty);
        Cell emptyNeighbor;
        if ((emptyNeighbor = getRandomNeighbor(emptyNeighbors)) != null) {
            Cell newCell = null;
            setNewCell(newCell, cell);
            update(newCell, cell, emptyNeighbor);
        }

        else {
            nextBoardCells.add(cell);
        }
    }

    @Override
    public boolean isSatisfied (Cell cell) {
        return cell.getFramesAlive() == 5;
    }

}
