package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellView {

	private Rectangle myRectangle; 
	public CellView(double width , double height, int state){
		
		myRectangle  = new Rectangle(width, height); 
		myRectangle.setFill(stateToColor(state));
	}
	public void setColor(Color color){
		myRectangle.setFill(color);		
	}
	
	public Rectangle getRectangle() {
		return myRectangle;
	}
	
	//just an example. color for each state should be dealt with in cellview?
	public Color stateToColor(int state) {
		if (state == 1) {
			return Color.RED;
		}
		if (state == 2) {
			return Color.GREEN;
		}
		return Color.BLACK;
	}
	
	
}
