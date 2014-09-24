package views;

import java.util.ArrayList;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;

public class SegregationValueChangers extends ValueChangers {

	@Override
	public void makeAdjusters() {
		valueChangers = new ArrayList<Control>();
		probability = new TextField();
		configureProbability();
	}

}
