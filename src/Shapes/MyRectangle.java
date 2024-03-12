package Shapes;

import org.json.simple.JSONObject;

import java.awt.*;
import java.util.ArrayList;

public class MyRectangle extends Shapes {
    private int width;
    private int length;
    private String ID;

    private  int count= (int) Math.floor(Math.random()*100);
   public MyRectangle(){
       this.ID="rectangle"+this.count;
   }
    public String getID(){return this.ID;}

    @Override
    public void setID(int i) {
        this.ID="rectangle"+this.count+"("+i+")";
    }

    public int getLength() {
        return length;
    }
    public void setLength(int length){
        this.length=length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width){
        this.width=width;
    }
    public void draw(java.awt.Graphics canvas){
        canvas.setColor(getColor());
        canvas.drawRect(this.getPosition().x,this.getPosition().y,this.getWidth(),this.getLength());
        if(this.getFillColor()!=null)
        { canvas.setColor(getFillColor());
            canvas.fillRect(this.getPosition().x,this.getPosition().y,this.getWidth(),this.getLength()); }

    }


    @Override
    public boolean contains(Point point) {
        return new java.awt.Rectangle(this.getPosition().x,this.getPosition().y,this.getWidth(),this.getLength()).contains(point);
    }

    @Override
    public void moveTo(Point point) {
        Point dragPoint =this.getDraggingPoint();
        point.x+=(this.getPosition().x- dragPoint.x);
        point.y+=(this.getPosition().y- dragPoint.y);
        this.setPosition(point);
    }


    @Override
    public ArrayList<Point> getPoints() {
        ArrayList<Point> corners = new ArrayList<>();
        Point po2=new Point(this.getPosition().x+this.getWidth(),this.getPosition().y);
        Point po3=new Point(this.getPosition().x,this.getPosition().y+this.getLength());
        Point po4=new Point(this.getPosition().x+this.getWidth(),this.getPosition().y+this.getLength());
        corners.add(this.getPosition());
        corners.add(po2);
        corners.add(po3);
        corners.add(po4);
        return corners;
    }

    public MyRectangle parseShapeObject(JSONObject shape)
    {
        super.parseShapeObject(shape);
        this.width = ((Long)shape.get("width")).intValue();
        this.length = ((Long)shape.get("height")).intValue();
        return this;
    }

    @Override
    public JSONObject shapeToJson() {
        JSONObject rectangle = super.shapeToJson();
        rectangle.put("width",this.width);
        rectangle.put("height",this.length);

        return rectangle;
    }
    @Override
    public Point resize(Point mouse, Point corner) {
       ArrayList<Point> corners = getPoints();

       if(corner.equals(corners.get(0))) {

          this.width= (int) mouse.distance(corners.get(1));

           this.length= (int) mouse.distance(corners.get(2));


           setPosition(mouse);

           return getPoints().get(0);
       }

        if(corner.equals(corners.get(1))) {
            this.width= (int) mouse.distance(corners.get(0));

            this.length= (int) mouse.distance(corners.get(3));

            setPosition(new Point(getPosition().x, mouse.y));

            return getPoints().get(1);
        }

        if(corner.equals(corners.get(2))) {
            this.width= (int) mouse.distance(corners.get(3));

            this.length= (int) mouse.distance(corners.get(0));

           // setPosition(new Point(getPosition().x, mouse.y));


            return getPoints().get(2);
        }

        if(corner.equals(corners.get(3))) {
            this.width= (int) mouse.distance(corners.get(2));

            this.length= (int) mouse.distance(corners.get(1));

           // setPosition(new Point(getPosition().x, mouse.y));


            return getPoints().get(3);
        }

       return corners.get(0);
    }
}
