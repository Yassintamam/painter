package Shapes;

import org.json.simple.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Line_segment extends Shapes {
   private Point endPoint;
   private String ID;
    private  int count= (int) Math.floor(Math.random()*100);
    public Line_segment(){ this.ID= "line"+this.count;}

    public String getID(){return this.ID;}

    @Override
    public void setID(int i) {
        this.ID="line"+this.count+"("+i+")";
    }

    public Point getEndPoint() {
        return this.endPoint;
    }

    public void setEndPoint(Point endPoint){
        this.endPoint=endPoint;
    }

    public void draw(java.awt.Graphics canvas){
        canvas.setColor(getColor());
        canvas.drawLine(this.getPosition().x,this.getPosition().y,this.getEndPoint().x,this.getEndPoint().y);
    }

    @Override
    public boolean contains(Point point) {
        double m = this.getPosition().distance(this.getEndPoint());
        double m1 =this.getPosition().distance(point);
        double m2 =this.getEndPoint().distance(point);
        double m3 = m1+m2;
        double m4 = m3-m;

        return Math.abs(m4)<=3;
    }

    @Override
    public void moveTo(Point point) {
        Point oldPoint = getPosition();
        Point dragPoint=this.getDraggingPoint();
        point.x+=(getPosition().x-dragPoint.x);
        point.y+= (getPosition().y-dragPoint.y);
        int x = point.x - oldPoint.x;
        int y = point.y - oldPoint.y;
        this.endPoint.x+=x;
        this.endPoint.y+=y;
        setPosition(point);
    }
    @Override
    public Line_segment clone() throws CloneNotSupportedException {

        Line_segment l=(Line_segment) super.clone();
        int x = this.getEndPoint().x;
        int y = this.getEndPoint().y;
        Point p = new Point(x,y);
        l.setEndPoint(p);
        return l;
    }
    public Line_segment parseShapeObject(JSONObject shape)
    {
        super.parseShapeObject(shape);
        Point point  = new Point();
        point.x = ((Long) shape.get("pointx")).intValue();
        point.y = ((Long) shape.get("pointy")).intValue();
        this.endPoint = point;


        return this;
    }

    @Override
    public JSONObject shapeToJson() {
        JSONObject lineSegment = super.shapeToJson();
        lineSegment.put("pointx",this.endPoint.x);
        lineSegment.put("pointy",this.endPoint.y);

        return lineSegment;
    }


    @Override
    public ArrayList<Point> getPoints() {
        ArrayList<Point> corners = new ArrayList<>();
        corners.add(this.getPosition());
        corners.add(this.getEndPoint());
        return  corners;
    }

    @Override
    public Point resize(Point mouse, Point corner) {
        if(corner.equals(this.getPosition()))
            this.setPosition(mouse);
        if(corner.equals(this.getEndPoint()))
            this.setEndPoint(mouse);
        return mouse;
    }
}
