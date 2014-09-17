package cellsociety_team05_controller;

import java.util.Random;

import models.Board;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CellAutomata extends Application {
	SimulationRules mySimulation;

	
	@Override
	public void start(Stage s) throws Exception {
		// TODO Auto-generated method stub
		GridPane grid = new GridPane();
		XMLParser xmlParser = new XMLParser();
		xmlParser.parseXML("dummyxml.xml", grid);
		s.setTitle("Cellular Automata");
		mySimulation = xmlParser.getSimRules(); // Your simulation's class
		Board board = xmlParser.getBoard();
		Scene scene = mySimulation.init(s, 400, 400, grid, board);
		s.setScene(scene);
		s.show();
		
		// sets the game's loop 
		KeyFrame frame = mySimulation.start();
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	/**
	 * Start the program.
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
}
