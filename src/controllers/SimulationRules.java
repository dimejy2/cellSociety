package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import models.Board;
import models.Cell;
import models.CellFactory;
import models.Patch;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import views.PopulationGraph;


public abstract class SimulationRules {

<<<<<<< HEAD:src/cellsociety_team05_controller/SimulationRules.java
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
    protected static Random rand = new Random();
    protected PopulationGraph myPopulationGraph;
    protected List<Patch> invalidPatchChoices;
    protected int frames;

    public void init (Pane boardPane, Board board, int numStates) {

        myBoard = board;
        myBoard.createNeighborhoods();
        myPatches = myBoard.getPatches();
        myBoardPane = boardPane;
        myNumStates = numStates;
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

    private void generateNeighborMaps () {
        for (Patch patch : myPatches) {
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
        invalidPatchChoices = new ArrayList<>();
        for (Patch patch : myPatches) {
            updateNextPatch(patch);
        }
    }

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

    protected void swapWithRandomCell (Patch patch, List<Patch> patchList) {

        Patch toSwitch = patch.checkSurroundingPatches(patchList, invalidPatchChoices);
        if (toSwitch != null) {
            Cell temp = toSwitch.getCell();
            toSwitch.setCell(patch.getCell());
            if (temp != null) {
                patch.setCell(temp);
            }
            else {
                patch.setCell(CellFactory.getCell("", 0, null));
            }
            invalidPatchChoices.add(toSwitch);
            invalidPatchChoices.add(patch);
        }

    }
=======
	protected Board myBoard;
	protected List<Patch> myPatches;
	protected List<Patch> nextBoardObjects;
	protected Board nextBoard;
	protected Pane myBoardPane;
	protected Animation myAnimation;
	protected Slider mySpeedSlider;
	protected Map<Integer, Color> stateToColorMap;
	protected int myNumStates;
	protected static Random rand = new Random();
	protected PopulationGraph myPopulationGraph;
	protected List<Patch> invalidPatchChoices;
	protected int frames;

	public void init (Pane boardPane, Board board, int numStates) {

		myBoard = board;
		myBoard.createNeighborhoods();
		myPatches = myBoard.getPatches();
		myBoardPane = boardPane;
		myNumStates = numStates;
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
		invalidPatchChoices = new ArrayList<>();
		for (Patch patch : myPatches) {
			updateNextPatch(patch);
		}
	}

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

	protected void swapWithRandomCell(Patch patch, List<Patch> patchList) {

		Patch toSwitch = patch.checkSurroundingPatches(patchList, invalidPatchChoices);
		if(toSwitch != null ) {
			Cell temp = toSwitch.getCell();
			toSwitch.setCell(patch.getCell());
			if(temp != null) {
				patch.setCell(temp);
			}
			else {
				patch.setCell(CellFactory.getCell("", 0,null));
			}
			invalidPatchChoices.add(toSwitch);
			invalidPatchChoices.add(patch);
		}

	}

>>>>>>> master:src/controllers/SimulationRules.java
}
