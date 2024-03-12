package Shapes;

import org.json.simple.JSONObject;

import java.awt.*;
import java.util.ArrayList;

public class Triangle extends Shapes {
    private String ID;

    private Point p2;

    private Point p3;

    private  int count= (int) Math.floor(Math.random()*100);

    public Triangle(){ this.ID= "triangle"+this.count;}
    @Override
    public String getID() {
        return this.ID;
    }

    @Override
    public void setID(int i) {
        this.ID="triangle"+this.count+"("+i+")";
    }

    public Point getP2(){return this.p2;}
    public void setP2(Point p2){this.p2=p2;}

    public Point getP3(){return this.p3;}
    public void setP3(Point p3){this.p3=p3;}

    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(getColor());
        canvas.drawLine(this.getPosition().x,this.getPosition().y,this.getP2().x,this.getP2().y);
        canvas.drawLine(this.getPosition().x,this.getPosition().y,this.getP3().x,this.getP3().y);
        canvas.drawLine(this.getP2().x,this.getP2().y,this.getP3().x,this.getP3().y);
        if(this.getFillColor()!=null)
        { canvas.setColor(getFillColor());
            int x[]=new int[3];
            int y[]=new int[3];
            x[0]=this.getPosition().x;
            x[1]=this.p2.x;
            x[2]=this.p3.x;
            y[0]=this.getPosition().y;
            y[1]=this.p2.y;
            y[2]=this.p3.y;
            canvas.fillPolygon(x,y,3);
       }
    }


    private boolean checkPositive(Point p1, Point p2, Point p3) {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y) > 0;
    }

    @Override
    public boolean contains(Point point) {
        boolean isOne, isTwo, isThree;

        isOne = checkPositive(point, this.getPosition(), p2);
        isTwo = checkPositive(point, p2, p3);
        isThree = checkPositive(point, p3, this.getPosition());

        boolean hasNegative = !isOne || !isTwo || !isThree;
        boolean hasPositive = isOne || isTwo || isThree;

        return !(hasNegative && hasPositive);
    }

    @Override
    public void moveTo(Point point) {
        Point dragPoint =this.getDraggingPoint();
        Point point1=this.getPosition();
        point.x+=(point1.x- dragPoint.x);
        point.y+=(point1.y- dragPoint.y);
        int x = point.x - point1.x;
        int y = point.y - point1.y;
        this.p2.x+=x;
        this.p2.y+=y;

        this.p3.x+=x;
        this.p3.y+=y;
        this.setPosition(point);
    }
    public Triangle clone() throws CloneNotSupportedException {

        Triangle t=(Triangle) super.clone();
        int x1 = this.getP2().x;
        int y1 = this.getP2().y;
        Point p1 = new Point(x1,y1);
        t.setP2(p1);
        int x2 = this.getP3().x;
        int y2 = this.getP3().y;
        Point p2 = new Point(x2,y2);
        t.setP3(p2);
        return t;
    }


    @Override
    public ArrayList<Point> getPoints() {
        ArrayList<Point> corners = new ArrayList<>();
        corners.add(this.getPosition());
        corners.add(this.p2);
        corners.add(this.p3);
        return  corners;
    }

    public Triangle parseShapeObject(JSONObject shape)
    {
        super.parseShapeObject(shape);
        Point point  = new Point();
        point.x = ((Long) shape.get("pointx")).intValue();
        point.y = ((Long) shape.get("pointy")).intValue();
        this.p2 = point;


        Point point3 = new Point();
        point3.x = ((Long) shape.get("point3x")).intValue();
        point3.y = ((Long) shape.get("point3y")).intValue();
        this.p3 = point3;

        return this;
    }

    @Override
    public JSONObject shapeToJson() {
        JSONObject triangle = super.shapeToJson();
        triangle.put("pointx",this.p2.x);
        triangle.put("pointy",this.p2.y);

        triangle.put("point3x",this.p3.x);
        triangle.put("point3y",this.p3.y);

        return triangle;
    }

    @Override
    public Point resize(Point mouse, Point corner) {
       if(corner.equals(this.getPosition()))
           this.setPosition(mouse);

       if(corner.equals(this.p2))
           this.setP2(mouse);
       if(corner.equals(this.p3))
           this.setP3(mouse);

       return mouse;
    }
}
