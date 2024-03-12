package Shapes;

import java.awt.*;

public interface Moveable {
    public void setDraggingPoint(Point point);
    public Point getDraggingPoint();
    public boolean contains(Point point);
    public void moveTo(Point point);
}
