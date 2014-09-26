package models;

import java.util.List;
import java.util.Map;

public class TreeCell extends Cell{

	public TreeCell(int state) {
		super(state, null);
	}

	@Override
	public int getNextState(Map<Integer, List<Patch>> neighbors) {
        if (neighbors.get(2).size()>0) {
        	if (rand.nextDouble() <= myPatch.getProbability()) {
                return 2;
            }
        }	
        return 1;
	}

}
