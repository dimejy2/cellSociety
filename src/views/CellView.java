package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class CellView {

    private Rectangle myRectangle;
    private Color myColor;

    public CellView (double width, double height, int state) {

        myRectangle = new Rectangle(width, height);
        myRectangle.setStroke(Color.GRAY);
    }

    public Rectangle getRectangle () {
        return myRectangle;
    }

    public void setColor (Color color) {
    	myColor = color;
        myRectangle.setFill(myColor);

    }
    
    public Color getColor() {
    	return myColor;
    }
}
