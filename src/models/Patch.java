package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import views.CellView;

public abstract class Patch {


	private Map<String, Double> staticResources; 
	protected Cell myCell;
	private List<Patch> myNeighborPatches;
	protected Map<Integer, List<Patch>> myNeighborMap;
	protected int myRow;
	protected int myColumn;
	protected double myProbability;
	protected double myResources;
	protected CellView myPatchView;
	protected Shape myShape;
	private double myPatchDim;
	private int myNumStates;


	public Patch(int row, int col, Map <String, Double> resources, double patchDim){
		myRow = row; 
		myColumn = col; 
		staticResources = resources; 
		myPatchView = new CellView(patchDim, patchDim, 0);
		myPatchDim = patchDim;
	}

	public void setNumStates(int numStates) {
		myNumStates = numStates;
	}

	private void setProbability(double probability) {
		myProbability = probability;
	}

	private void setResources(double resources) {
		myResources = resources;
	}

	public Cell getCell() {
		return myCell;
	}

	public void setCell(Cell cell) {
		myCell = cell;
		if(cell!=null) {
			myCell.setPatch(this);
		}
	}


	public int getRow(){
		return myRow; 
	}

	public int getColumn(){
		return myColumn; 
	}

	public void setNeighborPatches(List<Patch> neighborPatches) {
		myNeighborPatches = neighborPatches;
	}

	public List<Patch> getNeighborList() {
		return myNeighborPatches;
	}

	protected Map<Integer, List<Patch>> genericStateMap (int n) {

		Map<Integer, List<Patch>> toReturn = new HashMap<>();
		for (int i = 0; i < n; i++) {
			toReturn.put(i, new ArrayList<Patch>());
		}
		return toReturn;
	}

	public void generateNeighborMap() {
		for(Patch patch : myNeighborPatches) {
			if(patch.getCell()!=null) {
				myNeighborMap.get(patch.getCellsState()).add(patch);
			}
			else {
				myNeighborMap.get(0).add(patch);
			}
		}
	}

	public Map<Integer, List<Patch>> getNeighborMap() {
		return myNeighborMap;
	}

	public abstract void updateCell(int state);

	public int getCellsState() {
		return myCell.getState();
	}

	public void setShape(Shape shape) {
		myShape = shape;
	}

	public Shape getShape() {
		return myShape;
	}

	public CellView getPatchView() {
		return myPatchView;
	}

	public void removeCell() {
		myCell = null;
	}

	public double getPatchDim() {
		return myPatchDim;
	}

	public void updateFill(Color color) {
		myPatchView.setColor(color);
	}

	public void updateResources(int delta) {
		myResources += delta;
	}

	public boolean isAlive() {
		return myResources>0;
	}

	public double getProbability() {
		return myProbability;
	}
}