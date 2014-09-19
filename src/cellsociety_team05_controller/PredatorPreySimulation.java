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
			ArrayList<Cell> emptyNeighbors = cell.getNeighborMap().get(empty); // get all the neighbor cells that are empty
			if(emptyNeighbors.size() > 0) {
				int randomNum = chance.nextInt(emptyNeighbors.size()); // generate a random integer bounded by the number of empty neighbors
				while(emptyNeighbors.size() >0 && invalidCellChoices.contains(emptyNeighbors.get(randomNum))) {
					emptyNeighbors.remove(randomNum);
					if(emptyNeighbors.size() ==0) {
						break;
					}
					randomNum = chance.nextInt(emptyNeighbors.size());
				}
				if(emptyNeighbors.size() > 0) {
					Cell newCell;
					if(cell.getFramesAlive()==5) {
						newCell =  new Cell(cell.getRow(), cell.getColumn(), cell.getState(), cell.getCellDim());
						newCell.getCellView().setColor(stateToColorMap.get(cell.getState()));
						cell.resetFramesAlive();
					}
					else {
						newCell =  new Cell(cell.getRow(), cell.getColumn(), 0, cell.getCellDim());
						newCell.getCellView().setColor(stateToColorMap.get(0));

					}
					nextBoardCells[cell.getRow()][cell.getColumn()] = newCell;
					nextBoardCells[emptyNeighbors.get(randomNum).getRow()][emptyNeighbors.get(randomNum).getColumn()] = cell;
					invalidCellChoices.add(emptyNeighbors.get(randomNum));
					invalidCellChoices.add(newCell);
					invalidCellChoices.add(cell);
					cell.setRow(emptyNeighbors.get(randomNum).getRow());
					cell.setColumn(emptyNeighbors.get(randomNum).getColumn());
				}
				else {
					nextBoardCells[cell.getRow()][cell.getColumn()] = cell;	
				}
			}
			else {
				nextBoardCells[cell.getRow()][cell.getColumn()] = cell;
			}
		}

		else if(cell.getState()==shark) {
			ArrayList<Cell> fishNeighbors = cell.getNeighborMap().get(fish);
			if(fishNeighbors.size() >0) {
				int randomNum = chance.nextInt(fishNeighbors.size());
				Cell fishCell = fishNeighbors.get(randomNum);
				while(fishNeighbors.size() > 0 && invalidCellChoices.contains(fishCell)) {
					fishNeighbors.remove(randomNum);
					if(fishNeighbors.size() <=0) {
						break;
					}
					randomNum = chance.nextInt(fishNeighbors.size());

				}
				if(fishNeighbors.size()>0) {
					Cell newCell;
					if(cell.getFramesAlive()==5) {
						newCell =  new Cell(cell.getRow(), cell.getColumn(), cell.getState(), cell.getCellDim());
						newCell.getCellView().setColor(stateToColorMap.get(cell.getState()));
						newCell.setResources(6);
						cell.resetFramesAlive();
					}
					else {
						newCell =  new Cell(cell.getRow(), cell.getColumn(), 0, cell.getCellDim());
						newCell.getCellView().setColor(stateToColorMap.get(0));
					}					

					nextBoardCells[cell.getRow()][cell.getColumn()] = newCell;
					nextBoardCells[fishCell.getRow()][fishCell.getColumn()] = cell;
					cell.setRow(fishCell.getRow());
					cell.setColumn(fishCell.getColumn());
					cell.incrementResources(2);
					invalidCellChoices.add(fishCell);
					invalidCellChoices.add(newCell);
					invalidCellChoices.add(cell);
				}
			}
			if(fishNeighbors.size()<1) {
				cell.incrementResources(-2);
				if(cell.getResources() ==0) {
					cell.setState(0);
					cell.getCellView().setColor(stateToColorMap.get(cell.getState()));
					invalidCellChoices.add(cell);
					nextBoardCells[cell.getRow()][cell.getColumn()] = cell;
				}
			}

			if(cell.getNeighborMap().get(empty).size() > 0 && fishNeighbors.size()<1) {
				ArrayList<Cell> emptyNeighbors = cell.getNeighborMap().get(empty);
				int randomNum = chance.nextInt(emptyNeighbors.size());
				while(emptyNeighbors.size() >0 && invalidCellChoices.contains(emptyNeighbors.get(randomNum))) {
					emptyNeighbors.remove(randomNum);
					if(emptyNeighbors.size() ==0) {
						break;
					}
					randomNum = chance.nextInt(emptyNeighbors.size());
				}
				if(emptyNeighbors.size()>0) {
					Cell newCell;
					if(cell.getFramesAlive()==5) {
						newCell =  new Cell(cell.getRow(), cell.getColumn(), cell.getState(), cell.getCellDim());
						newCell.getCellView().setColor(stateToColorMap.get(cell.getState()));
						newCell.setResources(6);
						cell.resetFramesAlive();
					}
					else {
						newCell =  new Cell(cell.getRow(), cell.getColumn(), 0, cell.getCellDim());
						newCell.getCellView().setColor(stateToColorMap.get(0));
					}	
					Cell emptyCell = emptyNeighbors.get(randomNum);
					nextBoardCells[cell.getRow()][cell.getColumn()] = newCell;
					nextBoardCells[emptyCell.getRow()][emptyCell.getColumn()] = cell;
					invalidCellChoices.add(emptyCell);
					invalidCellChoices.add(cell);
					invalidCellChoices.add(newCell);
					cell.setRow(emptyNeighbors.get(randomNum).getRow());
					cell.setColumn(emptyNeighbors.get(randomNum).getColumn());
				}
				else {
					nextBoardCells[cell.getRow()][cell.getColumn()] = cell;	
					invalidCellChoices.add(cell);
				}
			}
			else {
				nextBoardCells[cell.getRow()][cell.getColumn()] = cell;	
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