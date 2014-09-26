package views;

import models.Hexagon;
import models.Triangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class PatchView {

    // private Rectangle myRectangle;
    private Polygon myHexagon;
    private Hexagon newHex;
    private Rectangle myRectangle;
    private Polygon myUpTriangle;
    private Polygon myDownTriangle;
    private Triangle newUpTri;
    private Triangle newDownTri;

    public PatchView (double width, double height) {
        // might not need height at all
        myRectangle = new Rectangle(width, height);

        newHex = new Hexagon(width / 2);
        newHex.setHex();
        myHexagon = newHex.getHex();

        newUpTri = new Triangle(width);
        newUpTri.setUpTriangle();
        myUpTriangle = newUpTri.getTriangle();

        newDownTri = new Triangle(width);
        newDownTri.setDownTriangle();
        myDownTriangle = newDownTri.getTriangle();

        setStroke(myRectangle);
        setStroke(myHexagon);
        setStroke(myUpTriangle);
        setStroke(myDownTriangle);

    }

    public Rectangle getRectangle () {
        return myRectangle;
    }

    public Polygon getHexagon () {
        return myHexagon;
    }

    public Polygon getUpTriangle () {
        return myUpTriangle;
    }

    public Polygon getDownTriangle () {
        return myDownTriangle;
    }

    public void setColor (Color color) {
        myHexagon.setFill(color);
        myRectangle.setFill(color);
        myUpTriangle.setFill(color);
        myDownTriangle.setFill(color);
    }

    public void setStroke (Shape shape) {
        shape.setStroke(Color.GRAY);
    }

}
