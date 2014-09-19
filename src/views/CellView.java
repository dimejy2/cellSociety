package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellView {

	private Rectangle myRectangle; 
	public CellView(double width , double height, int state){
		
		myRectangle  = new Rectangle(width, height); 
//		myRectangle.setFill(stateToColor(state));
		myRectangle.setStroke(Color.GRAY);
	}

	public Rectangle getRectangle() {
		return myRectangle;
	}
	
	public void setColor(Color color) {
		myRectangle.setFill(color);
	}
	
	//just an example. color for each state should be dealt with in cellview?
//	public Color stateToColor(int state) {
//		if (state == 0) {
//			return Color.WHITE;
//		}
//		if (state == 1) {
//			return Color.BLACK;
//		}
//		return Color.BLACK;
//	}
//	
	
}