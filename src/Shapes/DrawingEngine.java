package Shapes;

import Shapes.Shapes;

import java.awt.*;

public interface DrawingEngine {

    public void addShape(Shapes shape);

    public void removeShape(Shapes shape);

    public Shapes[] getShapes();

    public void refresh(Graphics canvas);

}
