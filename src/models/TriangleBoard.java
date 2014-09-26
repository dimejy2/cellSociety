package models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;


public class TriangleBoard extends Board {

    public TriangleBoard (int row, int column, Pane boardPane2, int states) {
        super(row, column, boardPane2, states);
    }

    @Override
    public void addPatch (Patch patch) {
        // adjusting the locations of hexagon cells (odd vs even columns)
    	super.addPatch(patch);

        Shape cellShape = patch.getPatchView().getDownTriangle();

        if ((patch.getRow() + patch.getColumn()) % 2 == 1) {
            cellShape = patch.getPatchView().getUpTriangle();
        }

        patch.setShape(cellShape);
        double xCoord = patch.getColumn() * (patch.getPatchDim() / 2);
        double yCoord =
                (patch.getPatchDim() * Math.sin(Math.PI / 3) / 2) + patch.getRow() *
                        patch.getPatchDim() * Math.sin(Math.PI / 3);
        
        cellShape.relocate(xCoord, yCoord);
        putShapedPatch(patch);
    }

}
