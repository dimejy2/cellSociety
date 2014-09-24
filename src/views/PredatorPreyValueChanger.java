//package views;
//
//import java.util.ArrayList;
//
//import cellsociety_team05_controller.SimulationController;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.scene.control.Slider;
//import javafx.scene.control.TextField;
//
//public class PredatorPreyValueChanger {
//	ArrayList<Slider> sliders;
//	TextField spawnTime;
//	SimulationController mySimulationController;
//	
//	private void makeAdjusters() {
//		spawnTime = new TextField();
//	}
//	
//	private void configureSpawnTime() {
//		spawnTime.textProperty().addListener(new ChangeListener<String>() {
//		    @Override
//		    public void changed(ObservableValue<? extends String> observable,
//		            String oldValue, String newValue) {
//
//		        outputTextArea.appendText("TextField Text Changed (newValue: " + newValue + ")\n");
//		    }
//		});
//	}
//	
//	private void configureSpeedSlider() {
//		mySpeedSlider.valueProperty().addListener(new ChangeListener<Number>() {
//            public void changed(ObservableValue<? extends Number> ov,
//                    Number old_val, Number new_val) {
//                        mySimulationController.changeSpeed(mySpeedSlider);
//                }
//            });
//	}
//}
