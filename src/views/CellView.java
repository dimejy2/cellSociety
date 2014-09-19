package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class CellView {

    private Rectangle myRectangle;

    public CellView (double width, double height, int state) {

        myRectangle = new Rectangle(width, height);
        myRectangle.setStroke(Color.GRAY);
    }

    public Rectangle getRectangle () {
        return myRectangle;
    }

    public void setColor (Color color) {
        myRectangle.setFill(color);

    }
}
