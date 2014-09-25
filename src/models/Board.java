package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;


public abstract class Board {

    private int numRows;
    private int numColumns;
    private Pane myBoardPane;
    protected Patch[][] myPatches;
    protected List<Patch> myGraph;
    private static final int WINDOW_SIZE = 400;
    protected Map<Integer, Color> stateToColorMap;
    protected int cellDim;
    private int numStates;
    private double myProbability;
    private Map<Integer, ArrayList<Patch>> myStateMap;
    // protected int[] myXDelta = { -1, 0, 1, -1, 1, -1, 0, 1 };
    // protected int[] myYDelta = { -1, -1, -1, 0, 0, 1, 1, 1 };
    // protected int[] myXDelta = { -1, 0, 1, -1, 0, 1 };
    // protected int[] myYDelta = { 1, 1, 1, 0, -1, 0 };
    protected int[] myXDelta = { -1, 0, 1, 1, 0, -1 };
    protected int[] myYDelta = { 0, -1, 0, 1, 1, 1 };

    public Board (int row, int column, Pane boardPane, int states) {
        numRows = row;
        numColumns = column;
        myBoardPane = boardPane;
        myPatches = new Patch[row][column];
        myGraph = new ArrayList<>();
        numStates = states;
        cellDim = WINDOW_SIZE / Math.max(row, column);
    }

    public void setProbability (double probability) {
        myProbability = probability;
    }

    public void addPatch (Patch patch) {
        myPatches[patch.getRow()][patch.getColumn()] = patch;
        myGraph.add(patch);
    }

    public void updatePatchViews () {
        for (Patch patch : myGraph) {
            putShapedPatch(patch);
        }
    }

    public void putShapedPatch (Patch patch) {
        if (patch.getCell() != null) {
            Cell cell = patch.getCell();
            patch.updateFill(stateToColorMap.get(cell.getState()));
        }
        else {
            patch.updateFill(stateToColorMap.get(0));
        }
        myBoardPane.getChildren().add(patch.getShape());
    }

    public List<Patch> getPatches () {
        return myGraph;
    }

    public void generateMyStateMap () {
        myStateMap = genericStateMap(numStates);
        for (Patch patch : myGraph) {
            if (patch.getCell() != null) {
                myStateMap.get(patch.getCell().getState()).add(patch);
            }
            else {
                myStateMap.get(0).add(patch);
            }
        }
    }

    public int getNumStates () {
        return numStates;
    }

    public Map<Integer, ArrayList<Patch>> getStateMap () {
        return myStateMap;
    }

    public HashMap<Integer, ArrayList<Patch>> genericStateMap (int n) {

        HashMap<Integer, ArrayList<Patch>> toReturn = new HashMap<>();
        for (int i = 0; i < n; i++) {
            toReturn.put(i, new ArrayList<Patch>());
        }
        return toReturn;
    }

    public void createNeighborhoods () {
        for (Patch[] row : myPatches) {
            for (Patch patch : row) {
                saveNeighbors(patch, myXDelta, myYDelta);
            }
        }
    }

    public void setXDeltaYDelta (int[] xDelta, int[] yDelta) {
        myXDelta = xDelta;
        myYDelta = yDelta;
    }

    public void saveNeighbors (Patch patch, int[] xDelta, int[] yDelta) {

        List<Patch> neighborPatches = new ArrayList<Patch>();
        for (int i = 0; i < xDelta.length; i++) {
            if (!isOutOfBounds(patch, xDelta[i], yDelta[i])) {
                Patch neighborPatch =
                        myPatches[patch.getRow() + xDelta[i]][patch.getColumn() + yDelta[i]];
                neighborPatches.add(neighborPatch);
            }
        }
        System.out.println("Final neighbor size: " + Integer.toString(neighborPatches.size()));
        patch.setNeighborPatches(neighborPatches);
    }

    private boolean isOutOfBounds (Patch patch, int xDelta, int yDelta) {

        return (patch.getRow() + xDelta < 0 || patch.getRow() + xDelta > myPatches.length - 1)
               ||
               (patch.getColumn() + yDelta < 0 || patch.getColumn() + yDelta > myPatches[0].length - 1);
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

    public void setColorMap (Map<Integer, Color> colorMap) {
        stateToColorMap = colorMap;
    }
}
