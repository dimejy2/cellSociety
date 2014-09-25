package models;

import java.util.ArrayList;

public class Patch {

	private int rowPosition; 
	private int colPosition; 
	private ArrayList<Integer> staticResources; 
	
	public Patch(int row, int col, ArrayList <Integer> resources){
		 rowPosition = row; 
		 colPosition = col; 
		 staticResources = resources; 
	}
	
	public int getResource( int index){
		return staticResources.get(index); 
	}
	
	public void changeResource( int index, int delta){
		staticResources.set(index, staticResources.get(index) + delta); 		
	}
	
	public int getRowPostion(){
			return rowPosition; 
	}
	
	public int getColPostion(){
		return colPosition; 
	}
	
}
