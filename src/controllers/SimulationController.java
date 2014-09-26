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
		mySimulation.setMaxResources(Integer.parseInt(maxResources.getText()));//HANDLE EXCEPTION
	}
	
	public void changeIncrementAmount(TextField incrementAmount, TextField decrementAmount) {
//		myAnimation.setRate(incrementAmount.getValue());
		
	}
	
	public void changeDecrementAmount(TextField decrementAmount) {
//		myAnimation.setRate(decrementAmount.getValue());
	}	
	
	public void changeProbability(TextField probability) {
//		myAnimation.setRate(probability.getValue());
		
	}
	
	public void changeBreedTime(TextField breedTime, int state) {
//		myAnimation.setRate(breedTime.getValue());
	}

}
