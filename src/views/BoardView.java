package views;

import java.awt.Dimension;
import java.io.File;

import models.Board;
import cellsociety_team05_controller.SimulationController;
import cellsociety_team05_controller.SimulationRules;
import cellsociety_team05_controller.XMLParser;
import cellsociety_team05_controller.faultyXMLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BoardView {
	public static final Dimension DEFAULT_SIZE = new Dimension(600, 600);
	public static final Dimension BUTTON_SIZE = new Dimension(150, 50);
	public static final Insets BUTTON_PADDING = new Insets(20, 20, 100, 0);
	public static final int BUTTON_SPACING = 20;
	private Scene myScene;
	private Stage myStage;
	private Button myPlayButton;
	private Button myPauseButton;
	private Button myStopButton;
	private Button myChooseFileButton;
	private Button myResetButton;
	private Slider mySpeedSlider;
	private SimulationRules mySimulation;
	private String xmlFile;
	private GridPane myGrid;
	private Board myBoard;
	private BorderPane root;
	private SimulationController mySimulationController;
	private XMLParser xmlParser;

	public BoardView(Stage stage) {
		myStage = stage;
		root = new BorderPane();
		root.setRight(makeControlPanel());
		myScene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height, Color.WHITE);

	}

	public void xmlInit() {
		xmlParser = new XMLParser();
		myGrid = new GridPane();
		xmlParser.parseXML(xmlFile, myGrid);
		simulationInit();

	}

	public void displayError(String s) {
		Label errorLabel = new Label(s);
		root.setLeft(errorLabel);
	}

	public void simulationInit() {
		mySimulationController = new SimulationController();
		root.setLeft(myGrid);
		int numStates = xmlParser.getNumStates();
		myBoard = xmlParser.getBoard();
		mySimulation = xmlParser.getSimRules();
		mySimulation.setSpeedSlider(mySpeedSlider);
		mySimulation.init(myGrid, myBoard, numStates);
		mySimulationController.setUpSimulation(mySimulation);		
		PopulationGraph populationGraph = new PopulationGraph(numStates);
		mySimulation.setPopulationGraph(populationGraph);
		root.setBottom(populationGraph.getPopulationGraph());
	}

	private Node makeControlPanel() {
		VBox result = new VBox(BUTTON_SPACING);
		result.setPadding(BUTTON_PADDING);
		myPlayButton = makeButton("Play", new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				mySimulation.play();
				myPlayButton.setDisable(true);
				myPauseButton.setDisable(false);
				myStopButton.setDisable(false);
				myChooseFileButton.setDisable(true);
			}
		});
		myPlayButton.setDisable(true);
		result.getChildren().add(myPlayButton);

		myChooseFileButton = makeButton("Choose XML file", new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				chooseXMLFile();
				myPlayButton.setDisable(false);
			}
		});
		result.getChildren().add(myChooseFileButton);

		myPauseButton = makeButton("Pause", new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event) {
				mySimulation.pause();
				myPauseButton.setDisable(true);
				myPlayButton.setDisable(false);
				myResetButton.setDisable(false);
			}
		});
		myPauseButton.setDisable(true);
		result.getChildren().add(myPauseButton);

		myStopButton = makeButton("Stop", new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event) {
				mySimulation.stop();
				myPlayButton.setDisable(true);
				myPauseButton.setDisable(true);
				myChooseFileButton.setDisable(false);
				myResetButton.setDisable(false);
			}
		});
		myStopButton.setDisable(true);
		result.getChildren().add(myStopButton);

		myResetButton = makeButton("Reset", new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				mySimulation.stop();
				simulationInit();
				myPauseButton.setDisable(true);
				myPlayButton.setDisable(false);
				myChooseFileButton.setDisable(false);
			}
		});
		myResetButton.setDisable(true);
		result.getChildren().add(myResetButton);

		mySpeedSlider = new Slider(1, 10, 1);
		configureSpeedSlider();
		Label speedLabel = new Label("Speed level: ");
		result.getChildren().add(speedLabel);
		result.getChildren().add(mySpeedSlider);
		return result;
	}

	private Button makeButton(String label,
			EventHandler<ActionEvent> handler) {
		Button result = new Button();
		result.setText(label);
		result.setOnAction(handler);
		result.setPrefSize(BUTTON_SIZE.width, BUTTON_SIZE.height);
		return result;
	}

	private void configureSpeedSlider() {
		mySpeedSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number old_val, Number new_val) {
				mySimulationController.changeSpeed(mySpeedSlider);
			}
		});
	}

	private void chooseXMLFile () {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(myStage);
		if(file!= null) {
			xmlFile = file.getPath();
		}
		xmlInit();
	}

	public SimulationRules getSimulationRules() {
		return mySimulation;
	}

	public Scene getScene() {
		return myScene;
	}
}
