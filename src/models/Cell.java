package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.scene.shape.Shape;
import views.PatchView;

public abstract class Cell {


	protected int myRow;
	protected int myColumn;
	protected int myState; 
	private PatchView myCellView;
	protected double myResources; // sugar for sugarMap
	private int framesAlive;
	private double myCellDim;
	private Map<Integer, List<Cell>> myNeighborStateMap; 
	private Map<String, Double> propertyMap;
	protected double myBreedingTime;
	protected double decrementValue;
	protected double incrementValue;
	protected int myVision; 
	protected Patch myPatch;
	protected List<Patch> similarNeighbors;

	protected static Random rand = new Random();
	

	public Cell (int state, Map<String, Double> cellResources){
		myState = state; 
		framesAlive = 0;
		propertyMap = cellResources;
	}
	
	public void setPatch(Patch patch) {
		myPatch = patch;
	}
	
	public Patch getPatch() {
		return myPatch;
	}

	public abstract int getNextState(Map<Integer, List<Patch>> neighbors);
	
	public PatchView getCellView() {
		return myCellView;
	}
	

	public void createCellView(int height, int state) {
		myCellView = new PatchView(height, height);
	}

	public int getRow(){
		return myRow; 
	}

	public int getColumn(){
		return myColumn; 
	}

	public void setResources(int resources) {
		myResources = resources;
	}
	
	public void incrementResources(int change) {
		myResources += change;
	}

	public boolean isAlive() {
		if(myResources > 0) {
			return true;
		}
		return false;
	}

	public int getFramesAlive() {
		return framesAlive;
	}

	public void incrementFramesAlive() {
		framesAlive++;
	}

	public void resetFramesAlive() {
		framesAlive = 0;
	}

	public int getState(){
		return myState;
	}  

	public void setState(int state){
		myState = state;
	}
	
	public void setNeighborMap(Map<Integer, List<Cell>> neighborMapToSet){
		myNeighborStateMap = neighborMapToSet; 
		
	}
	
	public double getResources() {
		return myResources;
	}
	
	public Map<Integer, List<Cell>> getNeighborMap(){
		return myNeighborStateMap; 
	}
	
	public void setIncrementDecrementValues(int increment, int decrement) {
		incrementValue = increment;
		decrementValue = decrement;
	}
	
	public void setRow(int row) {
		myRow = row;
	}
	
	public void setColumn(int column) {
		myColumn = column;
	}

	public void setReproductionTime(int reproductionTime) {
		myBreedingTime = reproductionTime;
	}
	
	public boolean canConsume(Patch patch, List<Patch> patches, List<Patch> invalidCells) {
		return false;
	}
	
	public boolean canReplicate() {
		return framesAlive >= myBreedingTime;
	}
	
	public Cell replicateCell(String s) {
		return CellFactory.getCell(s, myState, propertyMap);
	}
	
	public void resetBreedingTime() {
		framesAlive=0;
	}
	
	public boolean canMove(Patch patch, List<Patch> neighbors,List<Patch> invalidPatchChoices) {
		return patch.checkSurroundingPatches(neighbors, invalidPatchChoices) != null;
	}
	
	public boolean isStarved() {
		return false;
	}
	
	public void setVision(int n){
		myVision = n; 
	}

	public int getVision(){
		return myVision; 
		
	}
}
