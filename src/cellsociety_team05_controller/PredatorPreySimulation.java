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
				int randomNum = rand.nextInt(emptyNeighbors.size()); // generate a random integer bounded by the number of empty neighbors
				while(emptyNeighbors.size() >0 && invalidCellChoices.contains(emptyNeighbors.get(randomNum))) {
					randomNum = rand.nextInt(emptyNeighbors.size());
					emptyNeighbors.remove(randomNum);

				}
				if(emptyNeighbors.size() > 0) {
					Cell newCell =  new Cell(cell.getRow(), cell.getColumn(), 0, cell.getCellDim());
					newCell.getCellView().setColor(stateToColorMap.get(0));
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
					invalidCellChoices.add(cell);
				}
			}
			else {
				nextBoardCells[cell.getRow()][cell.getColumn()] = cell;
				invalidCellChoices.add(cell);
			}
		}

		else if(cell.getState()==shark) {
			ArrayList<Cell> fishNeighbors = cell.getNeighborMap().get(fish);
			if(fishNeighbors.size() >0) {
				int randomNum = rand.nextInt(fishNeighbors.size());
				Cell fishCell = fishNeighbors.get(randomNum);
				while(fishNeighbors.size() > 0 && invalidCellChoices.contains(fishCell)) {
					randomNum = rand.nextInt(fishNeighbors.size());
					fishCell = fishNeighbors.get(randomNum);
					fishNeighbors.remove(randomNum);

				}
				if(fishNeighbors.size()>0) {

					Cell newCell =  new Cell(cell.getRow(), cell.getColumn(), 0, cell.getCellDim());
					newCell.getCellView().setColor(stateToColorMap.get(0));
					nextBoardCells[newCell.getRow()][newCell.getColumn()] = newCell;
					nextBoardCells[fishCell.getRow()][fishCell.getColumn()] = cell;
					cell.setRow(fishCell.getRow());
					cell.setColumn(fishCell.getColumn());
					invalidCellChoices.add(fishCell);
					invalidCellChoices.add(newCell);
					invalidCellChoices.add(cell);
				}
			}
			if(cell.getNeighborMap().get(empty).size() > 0 && fishNeighbors.size()<1) {
				ArrayList<Cell> emptyNeighbors = cell.getNeighborMap().get(empty);
				int randomNum = rand.nextInt(emptyNeighbors.size());
				Cell newCell =  new Cell(cell.getRow(), cell.getColumn(), 0, cell.getCellDim());
				Cell emptyCell = emptyNeighbors.get(randomNum);
				newCell.getCellView().setColor(stateToColorMap.get(0));
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
}