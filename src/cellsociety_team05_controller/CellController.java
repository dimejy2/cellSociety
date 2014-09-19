package cellsociety_team05_controller;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import models.Cell;

public abstract class CellController {
        ArrayList<Cell> cells;
        
        public abstract Cell updateCell(Cell c, int nextState, Color color);
}