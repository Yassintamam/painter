package Shapes;

import Shapes.Shapes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Drawing__control extends JPanel implements DrawingEngine {
    private ArrayList<Shapes> records = new ArrayList<>();
    private Graphics canvas;

    public Drawing__control(Graphics canvas) {
        this.canvas = canvas;

    }

    @Override
    public void addShape(Shapes shape) {
        records.add(shape);

        refresh();
    }

    @Override
    public void removeShape(Shapes shape) {
        records.remove(shape);
        refresh(this.canvas);
    }

    @Override
    public Shapes[] getShapes() {
        return records.toArray(new Shapes[0]);
    }
    public int getSizeR(){return this.records.size();}

    @Override
    public void refresh(Graphics canvas){
       this.refresh();
        }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.fillRect(0,50,300,20);
        records.forEach(shape -> shape.draw(graphics));

    }

    public void refresh() {
        this.repaint();
    }
    }

