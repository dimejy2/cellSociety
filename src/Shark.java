

import java.util.ArrayList;
import models.Cell;


public class Shark extends PredatorPreySimulation {

    @Override
    public void updateNextBoard (Cell cell) {
        // TODO Auto-generated method stub
        ArrayList<Cell> fishNeighbors = cell.getNeighborMap().get(fish);
        Cell fishNeighbor;
        if ((fishNeighbor = getRandomNeighbor(fishNeighbors)) != null) {
            Cell newCell = null;
            update(newCell, cell, fishNeighbor);
            cell.incrementResources(2);
        }

        else {
            cell.incrementResources(-2);
            if (cell.getResources() == 0) {
                cell.setState(0);
                cell.getCellView().setColor(stateToColorMap.get(cell.getState()));
                currentCellUpdate(cell);
                return;
            }
        }

        ArrayList<Cell> emptyNeighbors = cell.getNeighborMap().get(empty);
        Cell emptyNeighbor;
        if ((emptyNeighbor = getRandomNeighbor(emptyNeighbors)) != null) {

            Cell newCell = null;
            setNewCell(newCell, cell);
            Cell emptyCell = emptyNeighbor;
            update(newCell, cell, emptyCell);
        }
        else {
          currentCellUpdate(cell);
        }
    }

    @Override
    public boolean isSatisfied (Cell cell) {
        return (cell.getFramesAlive() >= 5);
    }

//    @Override
//    public void setNewCell (Cell newCell, Cell cell) {
//        if (cell.getFramesAlive() >= 5) {
//            newCell = cell.replicateCell(cell, cell.getState());
//            newCell.getCellView().setColor(stateToColorMap.get(cell.getState()));
//            newCell.setResources(6);
//            cell.resetFramesAlive();
//        }
//        else {
//            newCell = cell.replicateCell(cell, 0);
//            newCell.getCellView().setColor(stateToColorMap.get(0));
//        }
//    }

}
