package cellsociety_team05_controller;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import models.Cell;

//1 = fish, 2 = shark
public class PredatorPreySimulation extends SimulationRules {

	private int[] xDelta = { 0 ,-1, 1, 0};
	private int[] yDelta = {-1, -0, 0, 1};

	@Override
	public void updateNextBoard(Cell cell) {
		int empty = 0;
		int fish = 1;
		int shark = 2;
		cell.incrementFramesAlive(); //updates alive
		if(invalidCellChoices.contains(cell)) {

		}
		else if(cell.getState()==fish) {
			ArrayList<Cell> emptyNeighbors = cell.getNeighborMap().get(empty);
			Cell emptyNeighbor;
			if((emptyNeighbor = getRandomNeighbor(emptyNeighbors)) != null) {
				Cell newCell;
				if(cell.getFramesAlive()==5) {
					newCell = cell.replicateCell(cell, cell.getState());
					newCell.getCellView().setColor(stateToColorMap.get(newCell.getState()));
					cell.resetFramesAlive();
				}
				else {
					newCell = cell.replicateCell(cell, 0);
					newCell.getCellView().setColor(stateToColorMap.get(0));
				}
				nextBoardCells[cell.getRow()][cell.getColumn()] = newCell;
				nextBoardCells[emptyNeighbor.getRow()][emptyNeighbor.getColumn()] = cell;
				invalidCellChoices.add(emptyNeighbor);
				invalidCellChoices.add(newCell);
				invalidCellChoices.add(cell);
				cell.setRow(emptyNeighbor.getRow());
				cell.setColumn(emptyNeighbor.getColumn());
			}
			else {
				nextBoardCells[cell.getRow()][cell.getColumn()] = cell;
			}
		}


		else if(cell.getState()==shark) {
			ArrayList<Cell> fishNeighbors = cell.getNeighborMap().get(fish);
			Cell fishNeighbor;
			if((fishNeighbor = getRandomNeighbor(fishNeighbors)) != null) {
				Cell newCell;
				if(cell.getFramesAlive()>=5) {
					newCell =  cell.replicateCell(cell, cell.getState());
					newCell.getCellView().setColor(stateToColorMap.get(cell.getState()));
					newCell.setResources(6);
					cell.resetFramesAlive();
				}
				else {
					newCell =  cell.replicateCell(cell, 0);
					newCell.getCellView().setColor(stateToColorMap.get(0));
				}					

				nextBoardCells[cell.getRow()][cell.getColumn()] = newCell;
				nextBoardCells[fishNeighbor.getRow()][fishNeighbor.getColumn()] = cell;
				cell.setRow(fishNeighbor.getRow());
				cell.setColumn(fishNeighbor.getColumn());
				cell.incrementResources(2);
				invalidCellChoices.add(fishNeighbor);
				invalidCellChoices.add(newCell);
				invalidCellChoices.add(cell);
			}

			else {
				cell.incrementResources(-2);
				if(cell.getResources() ==0) {
					cell.setState(0);
					cell.getCellView().setColor(stateToColorMap.get(cell.getState()));
					invalidCellChoices.add(cell);
					nextBoardCells[cell.getRow()][cell.getColumn()] = cell;
					return;
				}
			}

			ArrayList<Cell> emptyNeighbors = cell.getNeighborMap().get(empty);
			Cell emptyNeighbor;
			if((emptyNeighbor = getRandomNeighbor(emptyNeighbors)) != null) {

				Cell newCell;
				if(cell.getFramesAlive()>=5) {
					newCell =  new Cell(cell.getRow(), cell.getColumn(), cell.getState(), cell.getCellDim());
					newCell.getCellView().setColor(stateToColorMap.get(cell.getState()));
					newCell.setResources(6);
					cell.resetFramesAlive();
				}
				else {
					newCell =  new Cell(cell.getRow(), cell.getColumn(), 0, cell.getCellDim());
					newCell.getCellView().setColor(stateToColorMap.get(0));
				}	
				Cell emptyCell = emptyNeighbor;
				nextBoardCells[cell.getRow()][cell.getColumn()] = newCell;
				nextBoardCells[emptyCell.getRow()][emptyCell.getColumn()] = cell;
				invalidCellChoices.add(emptyCell);
				invalidCellChoices.add(cell);
				invalidCellChoices.add(newCell);
				cell.setRow(emptyNeighbor.getRow());
				cell.setColumn(emptyNeighbor.getColumn());

			}
			else {
				nextBoardCells[cell.getRow()][cell.getColumn()] = cell;	
				invalidCellChoices.add(cell);
			}
		}

		else if(cell.getState() == empty) {
			nextBoardCells[cell.getRow()][cell.getColumn()] = cell;
		}
		else {
			nextBoardCells[cell.getRow()][cell.getColumn()] = cell;
		}
	}




	@Override
	public void saveNeighborStates(Cell cell) {
		super.saveNeighborStates(cell);
	}


	@Override
	public void checkCells() {
		nextBoardCells = new Cell[myCells.length][myCells[0].length];
		invalidCellChoices = new ArrayList<>();
		for (int row = 0; row < myCells.length; row++) {
			for (int column = 0; column < myCells[0].length; column++) {
				Cell cell = myCells[row][column];
				myBoard.saveNeighborStates(cell, x4Delta, y4Delta);
			}
		}
		for (int row = 0; row < myCells.length; row++) {
			for (int column = 0; column < myCells[0].length; column++) {
				Cell cell = myCells[row][column];
				updateNextBoard(cell);				
			}
		}
	}
}