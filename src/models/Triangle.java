package models;

import javafx.scene.shape.Polygon;


public class Triangle {

    double mySize;
    Polygon triangle = new Polygon();

    public Triangle (double size)
    {
        mySize = size;
    }

    public void setDownTriangle () {
        triangle.getPoints().addAll(new Double[] { 0.0, 0.0, mySize, 0.0, mySize / 2,
                                                  mySize * Math.sin(Math.PI / 3) });
    }

    public void setUpTriangle () {
        triangle.getPoints().addAll(new Double[] { mySize / 2, 0.0, mySize,
                                                  mySize * Math.sin(Math.PI / 3), 0.0,
                                                  mySize * Math.sin(Math.PI / 3) });
    }

    public Polygon getTriangle () {
        return triangle;
    }

}
