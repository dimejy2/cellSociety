package models;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import views.CellView;

public class Cell  {
	
	private int myXPosition;
	private int myYPosition;
	private int myState; 
//	private int stateIndex =0; 
	private CellView myCellView;
	private int myResources;
	private int framesAlive;

	
	public Cell ( int x, int y, int state, int resources){
		myXPosition = x; 
		myYPosition = y; 
		myState = state; 
		myCellView = new CellView(50, 50, state);
//		stateIndexChecker(); 
		myResources = resources;
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
//		if(stateIndexChecker()) return stateIndex; 
//		System.out.println("your cell has more than one state");
//		return 0; 
		return myState;
		}  
		
	public void setState(int state){
		myState = state;
//		if(stateIndexChecker()){
//			myState.set(stateIndex, 0); 
//			stateIndex = state; 
//			myState.set(stateIndex, 1); 
//			if(stateIndexChecker()) return; 	
//		}
//		System.out.println("Changing the state failed"); 
	}
	
//	private boolean stateIndexChecker(){
//		int sum = 0 ;  
//		for(int i : myState){
//			sum += i; 
//			if(myState.get(i) == 1 ) stateIndex = i; 
//		}
//		if(sum == 1 ) return true; 
//		return false; 
//	} 
	
	
}
