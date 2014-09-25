package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;


public abstract class Board {

    private int numRows;
    private int numColumns;
    private Pane myBoardPane;
    protected Cell[][] myCells;
    private static final int WINDOW_SIZE = 400;
    private ArrayList<Cell> emptyCells;
    protected int cellDim;
    private int numStates;
    private double myProbability;
    private Map<Integer, ArrayList<Cell>> myStateMap;
    protected Shape cellShape;
    protected double xCoord;
    protected double yCoord;

    public Board (int row, int column, Pane boardPane, int states) {
        numRows = row;
        numColumns = column;
        myBoardPane = boardPane;
        myCells = new Cell[row][column];
        numStates = states;
        cellDim = WINDOW_SIZE / Math.max(row, column);
    }

    public void setProbability (double probability) {
        myProbability = probability;
    }

    public abstract void addCell (Cell cell);

    public void putShapedCell () {
        cellShape.relocate(xCoord, yCoord);
        myBoardPane.getChildren().add(cellShape);
    }

    public void checkEmptyCells () {
        emptyCells = new ArrayList<Cell>();
        for (int i = 0; i < myCells.length; i++) {
            for (int j = 0; j < myCells[1].length; i++) {
                if (myCells[i][j].getState() == 0) {
                    emptyCells.add(myCells[i][j]);
                }
            }
        }
    }

    public ArrayList<Cell> getEmptyCells () {
        return emptyCells;
    }

    public Cell[][] getCells () {
        return myCells;
    }

    public void setCells (Cell[][] newCells) {
        myCells = newCells;
    }

    public void generateMyStateMap () {
        myStateMap = genericStateMap(numStates);
        for (Cell[] subCellArray : myCells) {
            for (Cell cell : subCellArray) {
                myStateMap.get(cell.getState()).add(cell);
            }
        }

    }

    public int getNumStates () {
        return numStates;
    }

    public Map<Integer, ArrayList<Cell>> getStateMap () {
        return myStateMap;
    }

    public HashMap<Integer, ArrayList<Cell>> genericStateMap (int n) {

        HashMap<Integer, ArrayList<Cell>> toReturn = new HashMap<>();
        for (int i = 0; i < n; i++) {
            toReturn.put(i, new ArrayList<Cell>());

        }

        return toReturn;
    }

    public void saveNeighborStates (Cell cell, int[] xDelta, int[] yDelta) {

        HashMap<Integer, ArrayList<Cell>> neighborStateMap = genericStateMap(numStates);

        for (int i = 0; i < xDelta.length; i++) {
            if (!isOutOfBounds(cell, xDelta[i], yDelta[i])) {
                Cell neighborCell =
                        myCells[cell.getRow() + xDelta[i]][cell.getColumn() + yDelta[i]];
                neighborStateMap.get(neighborCell.getState()).add(neighborCell);
            }
        }
        cell.setNeighborMap(neighborStateMap);
    }

    private boolean isOutOfBounds (Cell cell, int xDelta, int yDelta) {

        return (cell.getRow() + xDelta < 0 || cell.getRow() + xDelta > myCells.length - 1)
               ||
               (cell.getColumn() + yDelta < 0 || cell.getColumn() + yDelta > myCells[0].length - 1);
    }

    public double getProbablity () {
        return myProbability;
    }

    public int getNumRows () {
        return numRows;
    }

    public int getColumns () {
        return numColumns;
    }

}
