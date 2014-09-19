package cellsociety_team05_controller;

import javafx.scene.paint.Color;
import models.Cell;

public class DummyCellController extends CellController {
	
//	ArrayList<Cell> cells;

	@Override
	public Cell updateCell(Cell c, int nextState, Color color) {
		c.setState(nextState);
		c.getCellView().setColor(color);		
		return null;
	}

}