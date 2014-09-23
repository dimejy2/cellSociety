package views;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class PopulationGraph {
	private Map<Integer, Series<Number,Number>> stateToSeriesMap;
	private LineChart<Number,Number> populationGraph;
	public static final Dimension GRAPH_DIMENSIONS = new Dimension(400, 200);
	
	
	public PopulationGraph(int states) {
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Frames");
		yAxis.setLabel("Population");        
		populationGraph = new LineChart<Number,Number>(xAxis,yAxis);
		populationGraph.setMaxSize(GRAPH_DIMENSIONS.width, GRAPH_DIMENSIONS.height);
		stateToSeriesMap= new HashMap<>();
		for(int i =0; i<states; i++) {
			XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
			newSeries.setName(String.valueOf(i));
			stateToSeriesMap.put(i, newSeries);
			populationGraph.getData().add(newSeries);
		}
	}
	
	public void addData(int frame, int pop, int state) {
		Series<Number,Number> series = stateToSeries(state);
		series.getData().add(new XYChart.Data<>(frame, pop));
	}
	
	public Series<Number,Number> stateToSeries(int state) {
		return stateToSeriesMap.get(state);
	}
	
	public LineChart<Number, Number> getPopulationGraph() {
		return populationGraph;
	}
}
