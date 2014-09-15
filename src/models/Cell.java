package models;

import java.util.ArrayList;

import views.CellView;

public class Cell  {
	
	private int myXPosition;
	private int myYPosition;
	private ArrayList<Integer> myState; 
	private int stateIndex =0; 
	private CellView myCellView;
	

	
	public Cell ( int x, int y, ArrayList<Integer> state){
		myXPosition = x; 
		myYPosition = y; 
		myState = state; 
		myCellView = new CellView(50, 50);
		stateIndexChecker(); 
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
