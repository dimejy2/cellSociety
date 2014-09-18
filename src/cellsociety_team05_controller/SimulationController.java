package cellsociety_team05_controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Slider;
import javafx.util.Duration;

public class SimulationController {	
	private KeyFrame frame;
	private SimulationRules mySimulation;
	private Timeline myAnimation;

	public void setUpSimulation(SimulationRules simulation) {
		mySimulation = simulation;
		frame = mySimulation.frame();
		myAnimation = new Timeline();
		simulation.setAnimation(myAnimation);
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);
	}
	
	public void changeSpeed(Slider mySpeedSlider) {
		myAnimation.setRate(mySpeedSlider.getValue());
	}
}
