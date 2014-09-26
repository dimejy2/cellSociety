package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
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
	
	public void changeMaxResources(TextField maxResources) {
		mySimulation.setMaxResources(Integer.parseInt(maxResources.getText()));
	}
	
	public void changeIncrementAmount(TextField incrementAmount, TextField decrementAmount) {
		
	}
	
	public void changeDecrementAmount(TextField decrementAmount) {
	}	
	
	public void changeProbability(TextField probability) {
	}
	
	public void changeBreedTime(TextField breedTime, int state) {
	}

}
