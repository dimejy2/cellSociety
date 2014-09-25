

import cellsociety_team05_controller.SimulationRules;
import models.Cell;


// 1 = fish, 2 = shark
abstract class PredatorPreySimulation extends SimulationRules {

    private int[] xDelta = { 0, -1, 1, 0 };
    private int[] yDelta = { -1, -0, 0, 1 };
    protected int empty = 0;
    protected int fish = 1;
    protected int shark = 2;

    public void updateNextBoard (Cell cell) {

        cell.incrementFramesAlive(); // updates alive
        if (invalidCellChoices.contains(cell)) {
        }
        nextBoardCells.add(cell);
    }

    public void update (Cell newCell, Cell cell, Cell neighbor) {
        nextBoardCells.add(newCell);
        currentCellUpdate(cell);
        invalidCellChoices.add(neighbor);
        invalidCellChoices.add(newCell);
        cell.setRow(neighbor.getRow());
        cell.setColumn(neighbor.getColumn());
    }

    public void currentCellUpdate (Cell cell) {
        invalidCellChoices.add(cell);
        nextBoardCells.add(cell);
    }

    // public abstract void setNewCell (Cell newCell, Cell cell);

    public abstract boolean isSatisfied (Cell cell);

    public void setNewCell (Cell newCell, Cell cell) {
        if (isSatisfied(cell)) {
            newCell = cell.replicateCell(cell, cell.getState());
            newCell.getCellView().setColor(stateToColorMap.get(newCell.getState()));
            cell.resetFramesAlive();
            // this should just be for shark...but since it doesn't matter for fish?
            newCell.setResources(6);
        }
        else {
            newCell = cell.replicateCell(cell, 0);
            newCell.getCellView().setColor(stateToColorMap.get(0));
        }
    }

    @Override
    void currentCellNeighbors (Cell cell) {
        // TODO Auto-generated method stub
        myBoard.saveNeighborStates(cell, xDelta, yDelta);

    }

}
