package cellsociety_team05_controller;

import java.util.ArrayList;

public class Cell  {
	
	private float myXPosition;
	private float myYPosition;
	private ArrayList<Integer> myState; 
	private int stateIndex =0; 
	
	public Cell ( float x, float y, ArrayList<Integer> state){
		myXPosition = x; 
		myYPosition = y; 
		myState = state; 
		stateIndexChecker(); 
	}

	public float getXPosition(){
		return myXPosition; 
	}
	
	public float getYPosition(){
		return myYPosition; 
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
