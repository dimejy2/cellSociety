package models;

import java.util.List;
import java.util.Map;

public class SugarCell extends Cell {

	private static final int METABOL = -5; 
	private static final int DEAD = 0; 
	private static final int ALIVE = 1;
	
	public SugarCell(int state, Map<String, Double> cellResources) {
		super(state, cellResources);
		myVision = cellResources.get("vision");
	}

	@Override
	public int getNextState(Map<Integer, List<Patch>> neighbors) {
		
		Patch newContainer = this.getPatch();
		this.incrementResources((int)newContainer.getResources());
		newContainer.updateResources(-newContainer.getResources());
		this.incrementResources(METABOL);
		
		if(this.getResources() <= 0 ){
			
			return DEAD; 
		}
		return this.getState(); 
	
	}

}
