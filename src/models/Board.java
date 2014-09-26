package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;


public abstract class Board {


	private int numRows;
	private int numColumns;
	private Pane myBoardPane;
	protected Patch[][] myPatches;
	protected Map<int[], Patch> myMapGraph;
	private static final int WINDOW_SIZE = 400;
	protected Map<Integer, Color> stateToColorMap;
	protected int cellDim;
	private int numStates;
	private Map<Integer, ArrayList<Patch>> myStateMap;
//	protected int[] myXDelta = { 1, 1, 0, -1, -1, 0 };
//	protected int[] myYDelta = { 0, -1, -1, 0, 1, 1 };
	 protected int[] myXDelta = { -1, 0, 1, -1, 1, -1, 0, 1 }; //8
	 protected int[] myYDelta = { -1, -1, -1, 0, 0, 1, 1, 1 }; //8
//	 protected int[] myXDelta = { 0, 0, 1, -1 }; //4
//	 protected int[] myYDelta = { 1, -1, 0, 0 }; //4
//	 protected int[] myXDelta = { 1, 1, 0, -1, -1, 0 }; //6
//	 protected int[] myYDelta = { 0, -1, -1, 0, 1, 1 }; //6



	public Board (int row, int column, Pane boardPane, int states) {
		numRows = row;
		numColumns = column;
		myBoardPane = boardPane;
		myPatches = new Patch[row][column];
		myMapGraph = new HashMap<int[], Patch>();
		numStates = states;
		cellDim = WINDOW_SIZE / Math.max(row, column);
	}



	public void addPatch (Patch patch) {
		myPatches[patch.getRow()][patch.getColumn()] = patch;
		int[] coordinates = new int[2];
		coordinates[0]= (patch.getRow());
		coordinates[1] = (patch.getColumn());
		myMapGraph.put(coordinates, patch);
	}

	public void updatePatchViews() {
		for(int[] coordinate : myMapGraph.keySet()) {
			putShapedPatch(myMapGraph.get(coordinate));
		}
	}


	public void putShapedPatch (Patch patch) {
		if(patch.getCell()!=null) {
			Cell cell = patch.getCell();
			patch.updateFill(stateToColorMap.get(cell.getState()));
		}
		else {
			patch.updateFill(stateToColorMap.get(0));
		}
		myBoardPane.getChildren().add(patch.getShape());
	}

	public Map<int[], Patch> getPatches () {
		return myMapGraph;
	}

	public void generateMyStateMap () {
		myStateMap = genericStateMap(numStates);
		for (int[] coordinate : myMapGraph.keySet()) {
			Patch patch = myMapGraph.get(coordinate);
			if(patch.getCell() != null) {
				myStateMap.get(patch.getCell().getState()).add(patch);
			}
			else {
				myStateMap.get(0).add(patch);
			}
		}
	}

	public int getNumStates () {
		return numStates;
	}

	public Map<Integer, ArrayList<Patch>> getStateMap () {
		return myStateMap;
	}

	public HashMap<Integer, ArrayList<Patch>> genericStateMap (int n) {

		HashMap<Integer, ArrayList<Patch>> toReturn = new HashMap<>();
		for (int i = 0; i < n; i++) {
			toReturn.put(i, new ArrayList<Patch>());
		}
		return toReturn;
	}

	public void createNeighborhoods() {
		for(Patch[] row : myPatches) {
			for(Patch patch : row) {
				saveNeighbors(patch, myXDelta, myYDelta);
			}
		}
	}

	public void setXDeltaYDelta(int[] xDelta, int[] yDelta) {
		myXDelta = xDelta;
		myYDelta = yDelta;
	}

	public void saveNeighbors (Patch patch, int[] xDelta, int[] yDelta) {

		List<Patch> neighborPatches = new ArrayList<Patch>();
		for (int i = 0; i < xDelta.length; i++) {
			if (!isOutOfBounds(patch, xDelta[i], yDelta[i])) {
				Patch neighborPatch =
						myPatches[patch.getRow() + xDelta[i]][patch.getColumn() + yDelta[i]];
				neighborPatches.add(neighborPatch);
			}
		}
		patch.setNeighborPatches(neighborPatches);
	}

	private boolean isOutOfBounds (Patch patch, int xDelta, int yDelta) {

		return (patch.getRow() + xDelta < 0 || patch.getRow() + xDelta > myPatches.length - 1)
				||
				(patch.getColumn() + yDelta < 0 || patch.getColumn() + yDelta > myPatches[0].length - 1);
	}



	public int getNumRows () {
		return numRows;
	}

	public int getColumns () {
		return numColumns;
	}

	public void setColorMap (Map<Integer, Color> colorMap) {
		stateToColorMap = colorMap;
	}

}
