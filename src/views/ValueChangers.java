package views;

import java.util.List;

import controllers.SimulationController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

public abstract class ValueChangers {
	List<Control> valueChangers;
	TextField sharkBreedTime;
	TextField fishBreedTime;
	TextField maxResources;
	TextField probability;
	SimulationController mySimulationController;
	
	public abstract void makeAdjusters();
	
	public List<Control> getValueChangers() {
		return valueChangers;
	}
	
	protected void configureMaxResources() {
		maxResources.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	mySimulationController.changeMaxResources(maxResources);
		    }
		});
		valueChangers.add(maxResources);
	}
	
	protected void configureProbability() {
		probability.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	mySimulationController.changeProbability(probability);
		    }
		});
		valueChangers.add(probability);
	}
}
