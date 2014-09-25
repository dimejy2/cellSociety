package models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class SquareBoard extends Board {

    public SquareBoard (int row, int column, Pane boardPane2, int states) {
        super(row, column, boardPane2, states);
    }

    @Override
    public void addCell (Cell cell) {
         // adjusting the locations of hexagon cells (odd vs even columns)
         myCells[cell.getRow()][cell.getColumn()] = cell;
        
         Shape cellShape = cell.getCellView().getRectangle();
        
         double xCoord = cellDim / 2 + cell.getColumn() * cellDim;
         double yCoord = cellDim / 2 + cell.getRow() * cellDim;
        
         putShapedPatch(cellShape, xCoord, yCoord);
        
    }

}