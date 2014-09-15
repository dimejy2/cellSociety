package cellsociety_team05_controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CellAutomata extends Application {
	SimulationRules mySimulation;
	
	private Board createBoard(String xml) {
			
	}
	
	@Override
	public void start(Stage s) throws Exception {
		// TODO Auto-generated method stub
		s.setTitle("Cellular Automata");
		mySimulation = new SimulationRules(); // Your simulation's class
		Board board = createBoard(xml);
		Scene scene = mySimulation.init(s, 400, 400, board);
		s.setScene(scene);
		s.show();
		
		// sets the game's loop 
		KeyFrame frame = mySimulation.start();
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

}
