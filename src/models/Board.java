package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;


public abstract class Board {

        private int numRows;
        private int numColumns;
        private Pane myBoardPane;
        protected Cell[][] myCells;
        protected List<Cell> cellGraph;
        private static final int WINDOW_SIZE = 400;
        private ArrayList<Cell> emptyCells;
        protected int cellDim;
        private int numStates;
        private double myProbability;
        private Map<Integer, ArrayList<Cell>> myStateMap;
        protected int[] myXDelta = { -1, 0, 1, -1, 0, 1 };
        protected int[] myYDelta = { 1, 1, 1, 0, -1, 0 };


        public Board (int row, int column, Pane boardPane, int states) {
                numRows = row;
                numColumns = column;
                myBoardPane = boardPane;
                myCells = new Cell[row][column];
                cellGraph = new ArrayList<>();
                numStates = states;
                cellDim = WINDOW_SIZE / Math.max(row, column);
        }

        public void setProbability (double probability) {
                myProbability = probability;
        }

        public void addCell (Cell cell) {
                myCells[cell.getRow()][cell.getColumn()] = cell;
                cellGraph.add(cell);
        }


        public void putShapedCell (Shape cellShape, double x, double y) {
                cellShape.relocate(x, y);
                myBoardPane.getChildren().add(cellShape);
        }

        public void checkEmptyCells () {
                emptyCells = new ArrayList<Cell>();
                for (Cell cell : cellGraph) {
                        if (cell.getState() == 0) {
                                emptyCells.add(cell);
                        }

                }
        }

        public ArrayList<Cell> getEmptyCells () {
                return emptyCells;
        }

        public List<Cell> getCells () {
                return cellGraph;
        }

        public void setCells (List<Cell> newCells) {
                cellGraph = newCells;
        }

        public void generateMyStateMap () {
                myStateMap = genericStateMap(numStates);
                for (Cell cell : cellGraph) {
                        myStateMap.get(cell.getState()).add(cell);
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

        public void createNeighborhoods() {
                for(Cell[] row : myCells) {
                        for(Cell cell : row) {
                                saveNeighborStates(cell, myXDelta, myYDelta);
                        }
                }
        }

        public void setXDeltaYDelta(int[] xDelta, int[] yDelta) {
                myXDelta = xDelta;
                myYDelta = yDelta;
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