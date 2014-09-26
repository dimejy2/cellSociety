package views;

import java.util.ArrayList;
import java.util.List;

import controllers.SimulationController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class PredatorPreyValueChangers extends ValueChangers {


	public void makeAdjusters() {
		valueChangers = new ArrayList<Control>();
		sharkBreedTime = new TextField();
		fishBreedTime = new TextField();
		configureSharkBreedTime();
		configureFishBreedTime();
	}

	private void configureSharkBreedTime() {
		sharkBreedTime.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				mySimulationController.changeBreedTime(sharkBreedTime, 2);;

			}
		});
		valueChangers.add(sharkBreedTime);
	}

	private void configureFishBreedTime() {
		fishBreedTime.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				mySimulationController.changeBreedTime(sharkBreedTime, 1);
			}
		});
		valueChangers.add(fishBreedTime);
	}

}
