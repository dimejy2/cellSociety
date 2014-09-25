package models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;


public class TriangleBoard extends Board {

    public TriangleBoard (int row, int column, Pane boardPane2, int states) {
        super(row, column, boardPane2, states);
    }

    @Override
    public void addCell (Cell cell) {
        // adjusting the locations of hexagon cells (odd vs even columns)
        myCells[cell.getRow()][cell.getColumn()] = cell;

        Shape cellShape = cell.getCellView().getDownTriangle();

        if ((cell.getRow() + cell.getColumn()) % 2 == 1) {
            cellShape = cell.getCellView().getUpTriangle();
        }

        double xCoord = cell.getColumn() * (cell.getCellDim() / 2);
        double yCoord =
                (cell.getCellDim() * Math.sin(Math.PI / 3) / 2) + cell.getRow() *
                        cell.getCellDim() * Math.sin(Math.PI / 3);

        putShapedPatch(cellShape, xCoord, yCoord);
    }

}
