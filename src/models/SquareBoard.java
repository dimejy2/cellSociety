package models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;


public class SquareBoard extends Board {
	private final static int WIDTH_ADJUSTMENT_FACTOR = 2;

	public SquareBoard (int row, int column, Pane boardPane2, int states) {
		super(row, column, boardPane2, states);
	}



	@Override
	public void addPatch (Patch patch) {
		// adjusting the locations of hexagon cells (odd vs even columns)
		super.addPatch(patch);
		Shape shape = patch.getPatchView().getRectangle();
		patch.setShape(shape);
		double xCoord = cellDim / WIDTH_ADJUSTMENT_FACTOR + patch.getColumn() * cellDim;
		double yCoord = cellDim / WIDTH_ADJUSTMENT_FACTOR + patch.getRow() * cellDim;

		shape.relocate(xCoord, yCoord);
		putShapedPatch(patch);
	}

}
