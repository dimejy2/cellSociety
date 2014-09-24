package cellsociety_team05_controller;

import java.awt.Dimension;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import models.Board;
import models.Cell;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser extends DefaultHandler {


	private Board board;
	private GridPane grid;
	private String temp;// what do we need this for?
	private int rowNumber;
	private int resources;
	private SimulationRules mySimulation;
	private int numCellStates;
	public static final Dimension GRID_SIZE = new Dimension(400, 400);
	public int cellDim;
	private Map<Integer, Color> stateToColorMap;
	private double probability;
	private int decrementValue = 1;
	private int incrementValue = 1;
	private boolean hasError; 
	private int colNumber; 
	private int numRows; 
	private int numCols; 
	

	/** The main method sets things up for parsing */
	public void parseXML(String file_path, GridPane gridPane) throws xmlError{
		// parse
		SAXParserFactory factory = SAXParserFactory.newInstance();
		rowNumber = 0;
		grid = gridPane;
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(file_path, this);

		} catch (ParserConfigurationException e) {
			System.out.println("ParserConfig error");
		} catch (SAXException e) {
			System.out.println("SAXException : xml not well formed");
		} catch (IOException e) {
			System.out.println("IO error");
		}

		if (hasError || (rowNumber != numRows)) throw new xmlError(); 

	}



	/*
	 * When the parser encounters plain text (not XML elements),
	 * it calls(this method, which accumulates them in a string buffer
	 */
	public void characters(char[] buffer, int start, int length) {
		temp = new String(buffer, start, length);
	}


	/*
	 * Every time the parser encounters the beginning of a new element,
	 * it calls this method, which resets the string buffer
	 */ 
	public void startElement(String uri, String localName,
			String qName, Attributes attributes) throws SAXException {
		temp = "";
		if (qName.equalsIgnoreCase("grid")) {
			String x = attributes.getValue("xValue");
			String y = attributes.getValue("yValue");
			numRows = Integer.parseInt(x); 
			numCols = Integer.parseInt(y);
			cellDim = GRID_SIZE.width / Math.max(Integer.parseInt(x), Integer.parseInt(y));

			board = new Board(numRows, numCols, grid, numCellStates);
			board.setProbability(probability);
		}

		if (qName.equalsIgnoreCase("cellularautomata")) {
			if(attributes.getValue("type").equals("GameofLife")) {
				mySimulation = new glifeSimulation();
			}
			else if(attributes.getValue("type").equals("FireSimulation")) {
				mySimulation = new FireSimulation();
			}
			else if(attributes.getValue("type").equals("Segregation")) {
				mySimulation = new SegregationSimulation();
			}
			else if(attributes.getValue("type").equals("WaTorWorld")) {
				mySimulation = new PredatorPreySimulation();
			}
			else{ hasError = true; 
			}
		}

		if(qName.equalsIgnoreCase("property")) {
			if(attributes.getValue("name").equals("resources")) {
				resources = Integer.parseInt(attributes.getValue("value"));
			}

			if(attributes.getValue("name").equals("numCellStates")) {
				numCellStates = Integer.parseInt(attributes.getValue("value"));
			}

			if(attributes.getValue("name").equals("probability")) {
				probability = Double.parseDouble(attributes.getValue("value"));

			}

			if(attributes.getValue("name").equals("decrement")) {
				decrementValue = Integer.parseInt(attributes.getValue("value"));
			}
			if(attributes.getValue("name").equals("increment")) {
				incrementValue = Integer.parseInt(attributes.getValue("value"));
			}                       
		}

		
		if (qName.equalsIgnoreCase("row")) {
			String row = attributes.getValue("values");   
			for(int j=0; j<row.length(); j++) {
				
				Cell cell = new Cell(rowNumber, j, Character.getNumericValue(row.charAt(j)), cellDim);
				cell.getCellView().setColor(stateToColorMap.get(Character.getNumericValue(row.charAt(j))));
				try{
				board.addCell(cell);
				}
				catch(ArrayIndexOutOfBoundsException e){
					hasError = true;
				}
				cell.setResources(resources);
				cell.setIncrementDecrementValues(incrementValue, decrementValue);
				if(Character.getNumericValue(row.charAt(j)) >= numCellStates) hasError = true; 
				colNumber ++; 
			}
			
			if(colNumber != numCols ) hasError = true ; 
			
			rowNumber++;
			colNumber = 0; 
		}

		
		if(qName.equalsIgnoreCase("colors")) {
			stateToColorMap = new HashMap<Integer, Color>();
			mySimulation.setColorMap(stateToColorMap);

		}

		if(qName.equalsIgnoreCase("color")) {
			int state = Integer.parseInt(attributes.getValue("state"));
			stateToColorMap.put(state, stringToColor(attributes.getValue("color")));

		}
	}

	private Color stringToColor(String color) {
		
		Color toReturn = new Color(0, 0, 0, 0); 
		
		try {
			toReturn = Color.web(color); 
		} catch (IllegalArgumentException e) {
			toReturn = Color.web("black"); 
		}
		
		return toReturn; 
	}

	/*
	 * When the parser encounters the end of an element, it calls this method
	 */
	public void endElement(String uri, String localName,
			String qName, Attributes attributes) throws SAXException {

	}

	public SimulationRules getSimRules() {
		return mySimulation;
	}

	public Board getBoard() {
		return board;
	}

	public int getNumStates() {
		return numCellStates;
	}

	
}