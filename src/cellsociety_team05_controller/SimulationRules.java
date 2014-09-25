package cellsociety_team05_controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import models.Board;
import models.Cell;
import models.Patch;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

import views.PopulationGraph;


public abstract class SimulationRules {

	protected Board myBoard;
	protected List<Patch> myPatches;
	protected List<Patch> nextBoardObjects;
	protected Board nextBoard;
	protected CellController myCellController;
	protected Pane myBoardPane;
	protected Animation myAnimation;
	protected Slider mySpeedSlider;
	protected Map<Integer, Color> stateToColorMap;
	protected int myNumStates;
	protected Random chance;
	protected PopulationGraph myPopulationGraph;
	// protected static final int[] xDelta = { -1, 0, 1, -1, 1, -1, 0, 1 };
	// protected static final int[] yDelta = { -1, -1, -1, 0, 0, 1, 1, 1 };
//	 protected static final int[] x4Delta = { 0, 0, 1, -1 };
//	 protected static final int[] y4Delta = { 1, -1, 0, 0 };
	// protected static final int[] hex_x_Delta = { 1, 1, 0, -1, -1, 0 };
	// protected static final int[] hex_y_Delta = { 0, -1, -1, 0, 1, 1 };

	protected ArrayList<Cell> invalidCellChoices;
	protected int frames;

	public void init (Pane boardPane, Board board, int numStates) {

		// Create a place to see the shapes
		myCellController = new DummyCellController(); // Your simulation's
		// CellController
		myBoard = board;
		myBoard.createNeighborhoods();
		myPatches = myBoard.getPatches();
		myBoardPane = boardPane;
		myNumStates = numStates;
		chance = new Random();
		frames = 0;
	}

	private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
		@Override
		public void handle (ActionEvent evt) {
			myBoard.generateMyStateMap();
			generateNeighborMaps();
			checkCells();
			switchBoards();
			updatePopulationGraph();
			frames++;
		}
	};

	private void generateNeighborMaps() {
		for(Patch patch : myPatches) {
			patch.generateNeighborMap();
		}
	}

	public abstract void updateNextPatch (Patch patch);

	public void switchBoards () {
		myBoardPane.getChildren().clear();
		myBoard.updatePatchViews();
	}

	public void checkCells ()
	{
		nextBoardObjects = new ArrayList<Patch>();
		invalidCellChoices = new ArrayList<>();
		for (Patch patch : myPatches) {
			updateNextPatch(patch);
		}
	}
	//	abstract void currentCellNeighbors(Cell cell);

	public void setAnimation (Animation animation) {
		myAnimation = animation;
	}

	public void stop () {
		myAnimation.stop();
	}

	public void play () {
		myAnimation.play();
	}

	public void pause () {
		myAnimation.pause();
	}

	public KeyFrame frame () {
		return new KeyFrame(Duration.millis(1000), oneFrame);
	}

	public void setSpeedSlider (Slider slider) {
		mySpeedSlider = slider;
	}

	//	public void setColorMap (Map<Integer, Color> colorMap) {
	//		stateToColorMap = colorMap;
	//	}


	protected Cell getRandomNeighbor (ArrayList<Cell> neighbors) {
		if (neighbors.size() == 0) { return null; }
		int randomNum = chance.nextInt(neighbors.size());
		Cell neighbor = neighbors.get(randomNum);
		while (neighbors.size() > 0 && invalidCellChoices.contains(neighbor)) {
			neighbors.remove(neighbor);
			if (neighbors.size() == 0) { return null; }
			randomNum = chance.nextInt(neighbors.size());
			neighbor = neighbors.get(randomNum);
		}
		return neighbor;
	}

	protected void updatePopulationGraph () {
		for (int state : myBoard.getStateMap().keySet()) {
			if (state == 0) {
				continue;
			}
			myPopulationGraph.addData(frames, myBoard.getStateMap().get(state).size(), state);
		}
	}

	public void setPopulationGraph (PopulationGraph populationGraph) {
		myPopulationGraph = populationGraph;
	}

	public void setMaxResources (int resources) {
		// Will be implemented after graphs are
	}

	public void setBoardProbability (double probability) {
		myBoard.setProbability(probability);
	}
}
