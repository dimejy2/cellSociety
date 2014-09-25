package models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;


public class HexagonBoard extends Board {

    public HexagonBoard (int row, int column, Pane boardPane2, int states) {
        super(row, column, boardPane2, states);
    }

    @Override
    public void addCell (Cell cell) {
        // adjusting the locations of hexagon cells (odd vs even columns)
    	super.addCell(cell);
//        myCells[cell.getRow()][cell.getColumn()] = cell;
    	Shape cellShape = cell.getCellView().getHexagon();

        double xCoord = ((3 * cell.getColumn() / 2 + 1) * cell.getCellDim()) / 2;
        double yCoord = (cellDim * Math.sqrt(3) / 4) + cell.getRow() * (cellDim * Math.sqrt(3) / 2);

        if (cell.getColumn() % 2 == 1) {
            // might wanna simplify
            xCoord = ((3 * cell.getColumn() / 2 + 1) * cell.getCellDim()) / 2 + cell.getCellDim() / 4;
            yCoord = (cellDim * Math.sqrt(3) / 2) + cell.getRow() * (cellDim * Math.sqrt(3) / 2);
        }
        putShapedCell(cellShape, xCoord, yCoord);

    }

}
