package cellsociety_team05_controller;

import java.io.IOException;

import javafx.scene.layout.GridPane;

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
	private String temp;
	private int rowNumber;
	private int resources;
	private SimulationRules mySimulation;
	private int numCellStates;
	private int numberofCells = 0;


	/** The main method sets things up for parsing */
    public void parseXML(String file_path, GridPane gridPane) {
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

			board = new Board(Integer.parseInt(x), Integer.parseInt(y), grid, numCellStates);
		}
		
		if (qName.equalsIgnoreCase("cellularautomata")) {
			if(attributes.getValue("type").equals("DummyRules")) {
				mySimulation = new glifeSimulation();
			}
		}
		
		if (qName.equalsIgnoreCase("row")) {
			String row = attributes.getValue("values");   
			for(int j=0; j<row.length(); j++) {
				Cell cell = new Cell(rowNumber, j, Character.getNumericValue(row.charAt(j)));
				board.addCell(cell);
				numberofCells ++;
				
			}
			rowNumber++;
		}
		
		if(qName.equalsIgnoreCase("property")) {
			if(attributes.getValue("name").equals("resources")) {
				resources = Integer.parseInt(attributes.getValue("value"));
			}
			
			if(attributes.getValue("name").equals("numCellStates")) {
				numCellStates = Integer.parseInt(attributes.getValue("value"));
			}
		}
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
}