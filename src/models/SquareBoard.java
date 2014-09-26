package models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;


public class SquareBoard extends Board {

	public SquareBoard (int row, int column, Pane boardPane2, int states) {
		super(row, column, boardPane2, states);
	}

	@Override
	public void addPatch (Patch patch) {
		// adjusting the locations of hexagon cells (odd vs even columns)
		super.addPatch(patch);
		Shape shape = patch.getPatchView().getRectangle();
		patch.setShape(shape);
		double xCoord = cellDim / 2 + patch.getColumn() * cellDim;
		double yCoord = cellDim / 2 + patch.getRow() * cellDim;

		shape.relocate(xCoord, yCoord);
		putShapedPatch(patch);
	}

}
