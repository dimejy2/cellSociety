package models;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import views.CellView;

public class Cell  {
	
	private int myXPosition;
	private int myYPosition;
	private ArrayList<Integer> myState; 
	private int stateIndex =0; 
	private CellView myCellView;
	private int myResources;
	private int framesAlive;

	
	public Cell ( int x, int y, ArrayList<Integer> state){
		myXPosition = x; 
		myYPosition = y; 
		myState = state; 
		myCellView = new CellView(50, 50);
		stateIndexChecker(); 
		framesAlive = 0;
	}

	public CellView getCellView() {
		return myCellView;
	}
	
	public int getXPosition(){
		return myXPosition; 
	}
	
	public int getYPosition(){
		return myYPosition; 
	}
	
	public void setResources(int resources) {
		myResources = resources;
	}
	
	public void updateResources(int change) {
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
		if(stateIndexChecker()) return stateIndex; 
		System.out.println("your cell has more than one state");
		return 0; 
		}  
		
	public void setState(int state){
		if(stateIndexChecker()){
			myState.set(stateIndex, 0); 
			stateIndex = state; 
			myState.set(stateIndex, 1); 
			if(stateIndexChecker()) return; 	
		}
		System.out.println("Changing the state failed"); 
	}
	
	private boolean stateIndexChecker(){
		int sum = 0 ;  
		for(int i : myState){
			sum += i; 
			if(myState.get(i) == 1 ) stateIndex = i; 
		}
		if(sum == 1 ) return true; 
		return false; 
	} 
		
	
}
