package views;

import java.awt.Dimension;
import java.io.File;

import models.Board;
import cellsociety_team05_controller.SimulationController;
import cellsociety_team05_controller.SimulationRules;
import cellsociety_team05_controller.XMLParser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BoardView {
	public static final Dimension DEFAULT_SIZE = new Dimension(600, 800);
	private Scene myScene;
	private Stage myStage;
	private Button myPlayButton;
	private Button myPauseButton;
	private Button myStopButton;
	private Button myChooseFileButton;
	private SimulationRules mySimulation;
	private String xmlFile;
	private GridPane myGrid;
	private Board myBoard;
	private SimulationController mySimulationController;

	public BoardView(Stage stage, GridPane grid) {
//		mySimulation = simulationRules;
		myStage = stage;
		BorderPane root = new BorderPane();
		myGrid = grid;
		root.setLeft(grid);
		root.setRight(makeControlPanel());
		myScene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height, Color.WHITE);

	}
	
	public void boardViewInit() {
		mySimulationController = new SimulationController();
		XMLParser xmlParser = new XMLParser();
		xmlParser.parseXML(xmlFile, myGrid);
		myBoard = xmlParser.getBoard();
		mySimulation = xmlParser.getSimRules();
		mySimulation.init(myGrid, myBoard);
		mySimulationController.setUpSimulation(mySimulation);

	}

	private Node makeControlPanel() {
		VBox result = new VBox();
		myPlayButton = makeButton("Play", new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				mySimulation.play();
			}
		});
		result.getChildren().add(myPlayButton);

		myChooseFileButton = makeButton("Choose XML file", new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				chooseXMLFile();
			}
		});
		result.getChildren().add(myChooseFileButton);

		myPauseButton = makeButton("Pause", new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event) {
				mySimulation.pause();
			}
		});
		result.getChildren().add(myPauseButton);

		myStopButton = makeButton("Stop", new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event) {
				mySimulation.stop();
			}
		});
		result.getChildren().add(myStopButton);
		return result;
	}

	private Button makeButton(String label,
			EventHandler<ActionEvent> handler) {
		Button result = new Button();
		result.setText(label);
		result.setOnAction(handler);
		return result;
	}

	private void chooseXMLFile () {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(myStage);
		if(file!= null) {
			xmlFile = file.getPath();
		}
		boardViewInit();
	}
	
	public SimulationRules getSimulationRules() {
		return mySimulation;
	}
	
	public Scene getScene() {
		return myScene;
	}
}
