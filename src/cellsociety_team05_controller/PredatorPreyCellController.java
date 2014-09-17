package cellsociety_team05_controller;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import models.Cell;

public class PredatorPreyCellController extends CellController {
	
//	ArrayList<Cell> cells;

	@Override
	public Cell updateCell(Cell c, int nextState, Color color) {
		// TODO Auto-generated method stub
		c.setState(nextState);
		c.getCellView().setColor(color);		
		return null;
	}

}