package models;

import javafx.scene.shape.Polygon;


public class Hexagon {

    double mySize;
    Polygon hexagon;

    public Hexagon (double size)
    {
        mySize = size;
    }

    public void setHex () {
        int i;
        hexagon = new Polygon();
        for (i = 0; i < 6; i++) {
            Double x_corner = (Double) (mySize * Math.cos(i * 2 * Math.PI / 6));
            Double y_corner = (Double) (mySize * Math.sin(i * 2 * Math.PI / 6));

            hexagon.getPoints().add(x_corner);
            hexagon.getPoints().add(y_corner);

        }
    }

    public Polygon getHex () {
        return hexagon;
    }

}
