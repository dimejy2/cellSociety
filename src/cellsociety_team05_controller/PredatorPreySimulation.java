package cellsociety_team05_controller;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;
import models.Cell;


// 1 = fish, 2 = shark
public class PredatorPreySimulation extends SimulationRules {

    private int[] xDelta = { 0, -1, 1, 0 };
    private int[] yDelta = { -1, -0, 0, 1 };

    @Override
    public void updateNextBoard (Cell cell) {
        int empty = 0;
        int fish = 1;
        int shark = 2;
        cell.incrementFramesAlive(); // updates alive
        if (invalidCellChoices.contains(cell)) {
        }
        else if (cell.getState() == fish) {
            ArrayList<Cell> emptyNeighbors = cell.getNeighborMap().get(empty);
            Cell emptyNeighbor;
            if ((emptyNeighbor = getRandomNeighbor(emptyNeighbors)) != null) {
                Cell newCell;
                if (cell.getFramesAlive() == 5) {
                    newCell = cell.replicateCell(cell, cell.getState());
                    newCell.getCellView().setColor(stateToColorMap.get(newCell.getState()));
                    cell.resetFramesAlive();
                }
                else {
                    newCell = cell.replicateCell(cell, 0);
                    newCell.getCellView().setColor(stateToColorMap.get(0));
                }
                nextBoardCells.add(newCell);
                nextBoardCells.add(cell);
                invalidCellChoices.add(emptyNeighbor);
                invalidCellChoices.add(newCell);
                invalidCellChoices.add(cell);
                cell.setRow(emptyNeighbor.getRow());
                cell.setColumn(emptyNeighbor.getColumn());
            }
            else {
                nextBoardCells.add(cell);
            }
        }

        else if (cell.getState() == shark) {
            ArrayList<Cell> fishNeighbors = cell.getNeighborMap().get(fish);
            Cell fishNeighbor;
            if ((fishNeighbor = getRandomNeighbor(fishNeighbors)) != null) {
                Cell newCell;
                if (cell.getFramesAlive() >= 5) {
                    newCell = cell.replicateCell(cell, cell.getState());
                    newCell.getCellView().setColor(stateToColorMap.get(cell.getState()));
                    newCell.setResources(6);
                    cell.resetFramesAlive();
                }
                else {
                    newCell = cell.replicateCell(cell, 0);
                    newCell.getCellView().setColor(stateToColorMap.get(0));
                }

                nextBoardCells.add(newCell);
                nextBoardCells.add(cell);
                cell.setRow(fishNeighbor.getRow());
                cell.setColumn(fishNeighbor.getColumn());
                cell.incrementResources(2);
                invalidCellChoices.add(fishNeighbor);
                invalidCellChoices.add(newCell);
                invalidCellChoices.add(cell);
            }

            else {
                cell.incrementResources(-2);
                if (cell.getResources() == 0) {
                    cell.setState(0);
                    cell.getCellView().setColor(stateToColorMap.get(cell.getState()));
                    invalidCellChoices.add(cell);
                    nextBoardCells.add(cell);
                    return;
                }
            }

            ArrayList<Cell> emptyNeighbors = cell.getNeighborMap().get(empty);
            Cell emptyNeighbor;
            if ((emptyNeighbor = getRandomNeighbor(emptyNeighbors)) != null) {

                Cell newCell;
                if (cell.getFramesAlive() >= 5) {
                    newCell =
                            new Cell(cell.getRow(), cell.getColumn(), cell.getState(),
                                     cell.getCellDim());
                    newCell.getCellView().setColor(stateToColorMap.get(cell.getState()));
                    newCell.setResources(6);
                    cell.resetFramesAlive();
                }
                else {
                    newCell = new Cell(cell.getRow(), cell.getColumn(), 0, cell.getCellDim());
                    newCell.getCellView().setColor(stateToColorMap.get(0));
                }
                Cell emptyCell = emptyNeighbor;
                nextBoardCells.add(newCell);
                nextBoardCells.add(cell);
                invalidCellChoices.add(emptyCell);
                invalidCellChoices.add(cell);
                invalidCellChoices.add(newCell);
                cell.setRow(emptyNeighbor.getRow());
                cell.setColumn(emptyNeighbor.getColumn());

            }
            else {
                nextBoardCells.add(cell);
                invalidCellChoices.add(cell);
            }
        }

        else {
            nextBoardCells.add(cell);
        }
    }

    @Override
    void currentCellNeighbors (Cell cell) {
        // TODO Auto-generated method stub
        myBoard.saveNeighborStates(cell, xDelta, yDelta);

    }
}