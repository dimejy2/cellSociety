package cellsociety_team05_controller;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import models.Board;
import models.Cell;
import models.CellFactory;
import models.HexagonBoard;
import models.Patch;
import models.PatchFactory;
import models.TriangleBoard;
import models.SquareBoard;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class XMLParser extends DefaultHandler {

	private Board board;
	private Pane boardPane;
	private int rowNumber;
	private double resources;
	private double breedTime;
	private SimulationRules mySimulation;
	private int numCellStates;
	public static final Dimension GRID_SIZE = new Dimension(400, 400);
	public double cellDim;
	private Map<Integer, Color> stateToColorMap;
	private Map<String, Double> patchProperties = new HashMap<String, Double>();	
	private Map<String, Double> cellProperties = new HashMap<String, Double>();
	private double probability;
	private double decrementValue = 1;
	private double incrementValue = 1;
	private boolean hasError;
	private int colNumber;
	private int numRows;
	private int numCols;
	private String criteria;
	private int multipleBoardInitChecker; 
	private Random Chance = new Random(); 
	private RandomCollection<Integer> probState = new RandomCollection<Integer>() ; 
	

	/** The main method sets things up for parsing */
	public XMLParser (String file_path, Pane myBoardPane) {
		parseXML(file_path, myBoardPane);
	}

	private void parseXML (String file_path, Pane boardP) throws xmlError {
		// parse
		SAXParserFactory factory = SAXParserFactory.newInstance();
		rowNumber = 0;
		boardPane = boardP;
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(file_path, this);

		}
		catch (ParserConfigurationException e) {
			System.out.println("ParserConfig error");
		}
		catch (SAXException e) {
			System.out.println("SAXException : xml not well formed");
		}
		catch (IOException e) {
			System.out.println("IO error");
		}

		if (hasError || (rowNumber != numRows)) throw new xmlError();

	}


	public void startElement (String uri, String localName,
			String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("grid")) {
			String row = attributes.getValue("row");
			String column = attributes.getValue("column");
			numRows = Integer.parseInt(row);
			numCols = Integer.parseInt(column);
			cellDim = GRID_SIZE.width / Math.max(Integer.parseInt(row), Integer.parseInt(column));

			if (attributes.getValue("gridType").equals("SquareBoard")) {
				board = new SquareBoard(Integer.parseInt(row), Integer.parseInt(column), boardPane,
						numCellStates);
			}
			else if (attributes.getValue("gridType").equals("HexagonBoard")) {
				board = new HexagonBoard(Integer.parseInt(row), Integer.parseInt(column), boardPane,
						numCellStates);
			}
			else if (attributes.getValue("gridType").equals("TriangleBoard")) {
				board = new TriangleBoard(Integer.parseInt(row), Integer.parseInt(column), boardPane,
						numCellStates);
			}

			else{

				hasError = true; 
			}
			board.setColorMap(stateToColorMap);

		}

		if (qName.equalsIgnoreCase("cellularautomata")) {
			if (attributes.getValue("type").equals("GameofLife")) {
				mySimulation = new glifeSimulation();
				criteria = attributes.getValue("type");
			}
			else if (attributes.getValue("type").equals("FireSimulation")) {
				mySimulation = new FireSimulation();
			}
			else if (attributes.getValue("type").equals("Segregation")) {
				mySimulation = new SegregationSimulation();
			}
			else if (attributes.getValue("type").equals("WaTorWorld")) {
				mySimulation = new WatorWorldSimulation();
			}
			else {
				hasError = true;
			}
			criteria = attributes.getValue("type");
		}

		if (qName.equalsIgnoreCase("property")) {
			if (attributes.getValue("name").equals("cellResources")) {
				resources = Integer.parseInt(attributes.getValue("value"));
				cellProperties.put(attributes.getValue("name"), resources);
			}

			else if (attributes.getValue("name").equals("numCellStates")) {
				numCellStates = Integer.parseInt(attributes.getValue("value"));
			}

			else if (attributes.getValue("name").equals("patchResources")) {
				double patchResources = Integer.parseInt(attributes.getValue("value"));
				patchProperties.put(attributes.getValue("name"),patchResources);
			}

			else if (attributes.getValue("name").equals("probability")) {
				probability = Double.parseDouble(attributes.getValue("value"));
				patchProperties.put(attributes.getValue("name"),probability);
			}

			else if (attributes.getValue("name").equals("patchDecrement")) {
				double patchDecrementValue = Integer.parseInt(attributes.getValue("value"));
				patchProperties.put(attributes.getValue("name"), patchDecrementValue);
			}

			else if (attributes.getValue("name").equals("cellDecrement")) {
				decrementValue = Integer.parseInt(attributes.getValue("value"));
				cellProperties.put(attributes.getValue("name"), decrementValue);
			}
			else if (attributes.getValue("name").equals("cellIncrement")) {
				incrementValue = Integer.parseInt(attributes.getValue("value"));
				cellProperties.put(attributes.getValue("name"), incrementValue);
			}
			else if (attributes.getValue("name").equals("breedTime")) {
				breedTime = Integer.parseInt(attributes.getValue("value"));
				cellProperties.put(attributes.getValue("name"), breedTime);
			}
		}

		if (qName.equalsIgnoreCase("row")) {
			multipleBoardInitChecker ++; 
			String row = attributes.getValue("values");
			for (int j = 0; j < row.length(); j++) {
				Patch newPatch = PatchFactory.getPatch(criteria, rowNumber, j, patchProperties,cellDim);
				if(Character.getNumericValue(row.charAt(j))>0) {

					Cell cell =
							CellFactory.getCell(criteria, Character.getNumericValue(row.charAt(j)), 
									cellProperties);

					if (Character.getNumericValue(row.charAt(j)) >= numCellStates) hasError = true;
					newPatch.setCell(cell);
				}
				board.addPatch(newPatch);

				colNumber++;
			}

			if (colNumber != numCols) hasError = true;

			rowNumber++;
			colNumber = 0;
		}


		if(qName.equalsIgnoreCase("randomfill")){
			multipleBoardInitChecker ++; 
			for(int j = 0 ; j < numRows ; j ++){
				for (int i = 0; i < numCols; i++){
					int randomState = randomStateGenerator(numCellStates) ; 
					Patch newPatch = PatchFactory.getPatch(criteria, i, j, patchProperties,cellDim);
					if(randomState != 0)	{
						Cell cell = CellFactory.getCell(criteria, randomState, cellProperties);
						newPatch.setCell(cell);
					}
					board.addPatch(newPatch);
				} 
			}
			rowNumber = numRows ; 
		}

		if(qName.equalsIgnoreCase("randomWeightedfill")){
			multipleBoardInitChecker ++; 
			randomWeightedGeneratorAdder(); 
			for(int j = 0 ; j < numRows ; j ++){
				for (int i = 0; i < numCols; i++){
					int randomState = probState.next() ; 
					Patch newPatch = PatchFactory.getPatch(criteria, i, j, patchProperties,cellDim);
					if(randomState != 0)	{
						Cell cell = CellFactory.getCell(criteria, randomState, cellProperties);
						newPatch.setCell(cell);
					}
					board.addPatch(newPatch);
				} 
			}
			rowNumber = numRows ; 
		}
		
		
		if (qName.equalsIgnoreCase("colors")) {
			stateToColorMap = new HashMap<Integer, Color>();

		}

		if (qName.equalsIgnoreCase("color")) {
			int state = Integer.parseInt(attributes.getValue("state"));
			stateToColorMap.put(state, stringToColor(attributes.getValue("color")));

		}
	}

	private Color stringToColor (String color) {

		Color toReturn = new Color(0, 0, 0, 0);

		try {
			toReturn = Color.web(color);
		}
		catch (IllegalArgumentException e) {
			toReturn = Color.web("black");
		}

		return toReturn;
	}

	/*
	 * When the parser encounters the end of an element, it calls this method
	 */
	public void endElement (String uri, String localName,
			String qName, Attributes attributes) throws SAXException {

	}

	public SimulationRules getSimRules () {
		return mySimulation;
	}

	public Board getBoard () {
		return board;
	}

	public int getNumStates () {
		return numCellStates;
	}

	private int randomStateGenerator( int a){
		return Chance.nextInt(numCellStates); 

	}

	private void randomWeightedGeneratorAdder(){
		ArrayList<Double> numProb = weightGenerator(); 
		for(int i = 0 ; i < numCellStates; i ++){
			probState.add(numProb.get(i), i);	
			System.out.println(numProb.get(i) +" BOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"); 
		}
		
	}

	private ArrayList<Double> weightGenerator(){
		ArrayList<Double> numProb = new ArrayList<Double>(); 
		double sum = 1.0 ; 
		for(int i = 0 ; i < numCellStates -1 ; i++ ){
			numProb.add( Math.abs(Chance.nextDouble()*sum - 0.5*sum));  
			sum = sum - numProb.get(i); 
		}
		numProb.add( Math.abs(sum) ); 
		
		return numProb; 
	}

}
