package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellView {

	private Rectangle myRectangle; 
	public CellView(double width , double height){
		
		myRectangle  = new Rectangle(width, height); 
	}
	public void setColor(Color color){
		myRectangle.setFill(color);		
	}
	
	public Rectangle getRectangle() {
		return myRectangle;
	}
	
	
}
