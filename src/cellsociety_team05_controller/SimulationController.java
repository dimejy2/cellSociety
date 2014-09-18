package cellsociety_team05_controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class SimulationController {	

	public void setUpSimulation(SimulationRules simulation) {
		KeyFrame frame = simulation.start();
		Timeline animation = new Timeline();
		simulation.setAnimation(animation);
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
	}
}
