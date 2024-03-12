package Shapes;

import netscape.javascript.JSObject;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import org.json.simple.JSONObject;
public abstract class Shapes implements Shape, Moveable,Cloneable {

    private Point position;
    private Color color;
    private Color fillColor;

    private Point draggingPoint;
    private String ID;

    public abstract String getID();

    public abstract void setID(int i);
    public void setPosition(java.awt.Point position){
        this.position =position;
    }

    public java.awt.Point getPosition(){return this.position;}

    public void setDraggingPoint(java.awt.Point position){
        this.draggingPoint =position;
    }

    public java.awt.Point getDraggingPoint(){return this.draggingPoint;}

    public void setColor(java.awt.Color color){
        this.color=color;
    }

    public java.awt.Color getColor() {return this.color;}

    public void setFillColor(java.awt.Color color){
        this.fillColor=color;
    }

    public java.awt.Color getFillColor(){return this.fillColor;}

    public abstract void draw(java.awt.Graphics canvas);
    public abstract boolean contains(Point point);
    public abstract void moveTo(Point point);

        public Shapes clone() throws CloneNotSupportedException {
            return (Shapes) super.clone();
    }
    public ArrayList<Shapes> getCornerShapes(ArrayList<Point> corners){
        ArrayList<Shapes> cornerShapes=new ArrayList<>();
            for(Point a:corners){
            MyRectangle n = new MyRectangle();
            n.setLength(10);
            n.setWidth(10);
            n.setPosition(a);
            n.setColor(Color.BLACK);
            n.setFillColor(Color.BLACK);
            cornerShapes.add(n);
        }
            return cornerShapes;
    }
    public void resizeRects(java.awt.Graphics canvas,ArrayList<Shapes> cornerShapes) {

        for(Shapes a:cornerShapes){

            a.draw(canvas);
        }}

    public abstract ArrayList<Point> getPoints ();

    public abstract Point resize(Point p1,Point p2);


    public  JSONObject shapeToJson()
    {
        JSONObject shape = new JSONObject();
        shape.put("positionx",this.position.x);
        shape.put("positiony",this.position.y);
        shape.put("type",this.getClass().getName());
        if(this.color != null) {
            shape.put("colorR", this.color.getRed());
            shape.put("colorG", this.color.getGreen());
            shape.put("colorB", this.color.getBlue());
        }
        if(this.fillColor != null) {
            shape.put("fillColorR", this.fillColor.getRed());
            shape.put("fillColorG", this.fillColor.getGreen());
            shape.put("fillColorB", this.fillColor.getBlue());
        }
        return shape;
    }
    public  Shape parseShapeObject(JSONObject jsonObject)
    {
        Point position  = new Point();
        position.x = ((Long) jsonObject.get("positionx")).intValue();
        position.y = ((Long) jsonObject.get("positiony")).intValue();
        this.position = position;
        if(jsonObject.get("colorR") != null && jsonObject.get("colorG") != null && jsonObject.get("colorB")!= null) {
            System.out.println("R-->"+jsonObject.get("colorR"));
            System.out.println("G-->"+jsonObject.get("colorG"));
            System.out.println("B-->"+jsonObject.get("colorB"));
            Color color = new Color(((Long) jsonObject.get("colorR")).intValue(), ((Long) jsonObject.get("colorG")).intValue(), ((Long) jsonObject.get("colorB")).intValue());
            this.color = color;
        }
        if(jsonObject.get("fillColorR") != null && jsonObject.get("fillColorG") != null && jsonObject.get("fillColorB")!= null) {
            System.out.println("fR-->"+jsonObject.get("fillColorR"));
            System.out.println("fG-->"+jsonObject.get("fillColorG"));
            System.out.println("fB-->"+jsonObject.get("fillColorB"));
            Color fillColor = new Color(((Long) jsonObject.get("fillColorR")).intValue(), ((Long) jsonObject.get("fillColorG")).intValue(), ((Long) jsonObject.get("fillColorB")).intValue());
            this.fillColor = fillColor;
        }
        return this;

    }
    public static Shapes fromString(JSONObject shapeJson){

        String type = (String) shapeJson.get("type");
        try {
            Class shapeClass = Class.forName(type);

            Constructor<Shapes> constructor = shapeClass.getConstructor();
            Shapes shape = (Shapes) constructor.newInstance();

            ((Shapes) shape).parseShapeObject(shapeJson);

            return shape;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }



}
