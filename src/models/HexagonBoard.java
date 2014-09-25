package models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;


public class HexagonBoard extends Board {

    public HexagonBoard (int row, int column, Pane boardPane2, int states) {
        super(row, column, boardPane2, states);
    }

    @Override
    public void addPatch (Patch patch) {
        // adjusting the locations of hexagon cells (odd vs even columns)
    	super.addPatch(patch);
    	Shape shape = patch.getPatchView().getHexagon();
    	patch.setShape(shape);
        double xCoord = ((3 * patch.getColumn() / 2 + 1) * patch.getPatchDim()) / 2;
        double yCoord = (cellDim * Math.sqrt(3) / 4) + patch.getRow() * (cellDim * Math.sqrt(3) / 2);

        if (patch.getColumn() % 2 == 1) {
            // might wanna simplify
            xCoord = ((3 * patch.getColumn() / 2 + 1) * patch.getPatchDim()) / 2 + patch.getPatchDim() / 4;
            yCoord = (cellDim * Math.sqrt(3) / 2) + patch.getRow() * (cellDim * Math.sqrt(3) / 2);
        }
        shape.relocate(xCoord,yCoord);
        putShapedPatch(patch);

    }

}
