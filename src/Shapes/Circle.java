package Shapes;

import org.json.simple.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Circle extends Shapes {
     private int radiusH;
    private int radiusV;
    private String ID;

    private  int count= (int) Math.floor(Math.random()*100);
     public Circle(){
         this.ID= "circle"+this.count;

     }
    public int getRadiusH(){
        return this.radiusH;
    }
    public int getRadiusV(){
        return this.radiusV;
    }
    public void setRadiusH (int radiusH){
      this.radiusH=radiusH;
    }
    public void setRadiusV (int radiusV){
        this.radiusV=radiusV;
    }

    public String getID(){return this.ID;}

    @Override
    public void setID(int i) {
        this.ID="circle"+this.count+"("+i+")";
    }

    public void draw(java.awt.Graphics canvas){
         canvas.setColor(getColor());
         canvas.drawOval(this.getPosition().x,this.getPosition().y,this.radiusH*2,this.radiusV*2);
         if(this.getFillColor()!=null)
        { canvas.setColor(getFillColor());
         canvas.fillOval(this.getPosition().x,this.getPosition().y,this.radiusH*2,this.radiusV*2);}
    }

    @Override
    public boolean contains(Point point) {
         Point center1 = new Point();
         center1.x=this.getPosition().x+this.radiusH;
         center1.y=this.getPosition().y+this.radiusV;

        double p = (Math.pow((point.x - center1.x ), 2) / Math.pow(this.radiusH, 2)) + (Math.pow((point.y - center1.y), 2) / Math.pow(this.radiusV, 2));

        return p<=1;
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
        Point po2=new Point(this.getPosition().x+this.getRadiusH()*2,this.getPosition().y);
        Point po3=new Point(this.getPosition().x,this.getPosition().y+this.getRadiusV()*2);
        Point po4=new Point(this.getPosition().x+this.getRadiusH()*2,this.getPosition().y+this.getRadiusV()*2);
        corners.add(this.getPosition());
        corners.add(po2);
        corners.add(po3);
        corners.add(po4);
        return corners;
    }

    public Circle parseShapeObject(JSONObject shape)
    {
        super.parseShapeObject(shape);
        this.radiusH = ((Long)shape.get("horizontalR")).intValue();
        this.radiusV = ((Long)shape.get("verticalR")).intValue();
        return this;
    }

    @Override
    public JSONObject shapeToJson() {
        JSONObject rectangle = super.shapeToJson();
        rectangle.put("horizontalR",this.radiusH);
        rectangle.put("verticalR",this.radiusV);

        return rectangle;
    }

    @Override
    public Point resize(Point mouse, Point corner) {
        ArrayList<Point> corners = getPoints();
        if(corner.equals(corners.get(0))){
            this.radiusH= (int) mouse.distance(corners.get(1));

            this.radiusV= (int) mouse.distance(corners.get(2));
            this.setPosition(mouse);

        }return corners.get(0);
    }
}
