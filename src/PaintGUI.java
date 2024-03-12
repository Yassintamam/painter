import Shapes.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class PaintGUI {
    private JFrame frameMain;
    private JPanel MainPanel;
    private JButton circleButton;
    private JButton rectangleButton;
    private JButton trianglebutton;
    private JButton lineSegmentButton;
    private JComboBox comboBox1;
    private JButton deleteButton;
    private JButton colorizeButton;
    private JPanel drawingBoard;
    private JPanel control;
    private JPanel shapButtons;
    private JButton copyButton;
    private Shapes selectShape;
    private Drawing__control controlV;



    public boolean validation(String line){

        try{
                int v= Integer.parseInt(line);
                if(v<0)throw new NumberFormatException();
                return true;
        } catch (NumberFormatException ex) {
            return false;
        }

    }

    public PaintGUI() {
        frameMain = new JFrame();
        frameMain.setVisible(true);
        frameMain.setContentPane(MainPanel);
        frameMain.setSize(1000, 600);
        controlV = new Drawing__control(drawingBoard.getGraphics());
        drawingBoard.add(controlV);

        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener((e) -> save());
        file.add(save);

        JMenuItem load = new JMenuItem("Load");
        load.addActionListener((e) -> load());
        file.add(load);

        menuBar.add(file);

        controlV.addMouseListener(new Click());
        controlV.addMouseMotionListener(new Drag());

        frameMain.setJMenuBar(menuBar);
        controlV.refresh();

        circleButton.addActionListener((e) -> {
            JFrame FrameC = new JFrame();
            FrameC.setTitle("create a circle");
            FrameC.setVisible(true);
            FrameC.setSize(300, 300);
            JPanel panelC = new JPanel();
            panelC.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
            panelC.setLayout(new GridLayout(0, 1));
            FrameC.add(panelC, BorderLayout.CENTER);
            JLabel radH = new JLabel();
            radH.setText("horizontal radius");
            panelC.add(radH);
            JTextField radiusH = new JTextField();
            panelC.add(radiusH);
            JLabel radV = new JLabel();
            radV.setText("vertical radius");
            panelC.add(radV);
            JTextField radiusV = new JTextField();
            panelC.add(radiusV);
            JLabel posx = new JLabel();
            posx.setText("x coordinate");
            panelC.add(posx);
            JTextField positionX = new JTextField();
            panelC.add(positionX);
            JLabel posy = new JLabel();
            posy.setText("y coordinate");
            panelC.add(posy);
            JTextField positionY = new JTextField();
            panelC.add(positionY);
            JButton creat = new JButton("creat");
            panelC.add(creat);
            JButton innercolor = new JButton("Inner color");
            JButton outerline = new JButton("outerline");
            panelC.add(innercolor);
            panelC.add(outerline);
            Circle newC = new Circle();
            creat.addActionListener(e1 -> {
                String s1 = radiusH.getText();
                String s4 = radiusV.getText();
                String s2 = positionX.getText();
                String s3 = positionY.getText();
                if (validation(s1) && validation(s2) && validation(s3)) {
                    int r1 = Integer.parseInt(s1);
                    int r2 = Integer.parseInt(s4);
                    newC.setRadiusH(r1);
                    newC.setRadiusV(r2);
                    Point p = new Point();
                    int x = Integer.parseInt(s2);
                    int y = Integer.parseInt(s3);
                    p.setLocation(x, y);
                    newC.setPosition(p);
                    controlV.addShape(newC);
                    controlV.refresh(drawingBoard.getGraphics());
                    comboBox1.addItem(newC.getID());

                } else
                    JOptionPane.showMessageDialog(null, "please enter valid inputs");
            });
            outerline.addActionListener(e1 -> {
                AtomicReference<Color> newColor = new AtomicReference<>(Color.blue);
                newColor.set(JColorChooser.showDialog(null, "select a color for outline", newColor.get()));
                newC.setColor(newColor.get());
                controlV.refresh(drawingBoard.getGraphics());
            });

            innercolor.addActionListener(e1 -> {
                AtomicReference<Color> newColor = new AtomicReference<>(Color.blue);
                newColor.set(JColorChooser.showDialog(null, "select a color for inner shape", newColor.get()));
                newC.setFillColor(newColor.get());
                controlV.refresh(drawingBoard.getGraphics());
            });

        });

        rectangleButton.addActionListener((e) -> {
            JFrame FrameR = new JFrame();
            FrameR.setTitle("create a rectangle");
            FrameR.setVisible(true);
            FrameR.setSize(300, 300);
            JPanel panelR = new JPanel();
            panelR.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
            panelR.setLayout(new GridLayout(0, 1));
            FrameR.add(panelR, BorderLayout.CENTER);
            JLabel wid = new JLabel();
            wid.setText("width");
            panelR.add(wid);
            JTextField width = new JTextField();
            panelR.add(width);
            JLabel len = new JLabel();
            len.setText("length");
            panelR.add(len);
            JTextField length = new JTextField();
            panelR.add(length);
            JLabel posx = new JLabel();
            posx.setText("x coordinate");
            panelR.add(posx);
            JTextField positionX = new JTextField();
            panelR.add(positionX);
            JLabel posy = new JLabel();
            posy.setText("y coordinate");
            panelR.add(posy);
            JTextField positionY = new JTextField();
            panelR.add(positionY);
            JButton creat = new JButton("creat");
            panelR.add(creat);

            JButton innercolor = new JButton("Inner color");
            JButton outerline = new JButton("outerline");
            panelR.add(innercolor);
            panelR.add(outerline);
            MyRectangle newR = new MyRectangle();
            creat.addActionListener(e1 -> {
                String s1 = width.getText();
                String s4 = length.getText();
                String s2 = positionX.getText();
                String s3 = positionY.getText();
                if (validation(s1) && validation(s2) && validation(s3) && validation(s4)) {
                    int w = Integer.parseInt(s1);
                    int l = Integer.parseInt(s4);
                    newR.setWidth(w);
                    newR.setLength(l);
                    Point p = new Point();
                    int x = Integer.parseInt(s2);
                    int y = Integer.parseInt(s3);
                    p.setLocation(x, y);
                    newR.setPosition(p);
                    controlV.addShape(newR);
                    controlV.refresh(drawingBoard.getGraphics());
                    comboBox1.addItem(newR.getID());

                } else
                    JOptionPane.showMessageDialog(null, "please enter valid inputs");
            });

            outerline.addActionListener(e1 -> {
                AtomicReference<Color> newColor = new AtomicReference<>(Color.blue);
                newColor.set(JColorChooser.showDialog(null, "select a color for outline", newColor.get()));
                newR.setColor(newColor.get());
                controlV.refresh(drawingBoard.getGraphics());
            });

            innercolor.addActionListener(e1 -> {
                AtomicReference<Color> newColor = new AtomicReference<>(Color.blue);
                newColor.set(JColorChooser.showDialog(null, "select a color for inner shape", newColor.get()));
                newR.setFillColor(newColor.get());
                controlV.refresh(drawingBoard.getGraphics());
            });
        });

        trianglebutton.addActionListener((e) -> {
            JFrame FrameT = new JFrame();
            FrameT.setTitle("create a triangle");
            FrameT.setVisible(true);
            FrameT.setSize(350, 350);
            JPanel panelT = new JPanel();
            panelT.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            panelT.setLayout(new GridLayout(0, 1));
            FrameT.add(panelT, BorderLayout.CENTER);
            JLabel XT1 = new JLabel();
            XT1.setText("position x1");
            panelT.add(XT1);

            JTextField X1 = new JTextField();
            panelT.add(X1);

            JLabel YT1 = new JLabel();
            YT1.setText("position y1");
            panelT.add(YT1);

            JTextField Y1 = new JTextField();
            panelT.add(Y1);

            JLabel XT2 = new JLabel();
            XT2.setText("position x2");
            panelT.add(XT2);

            JTextField X2 = new JTextField();
            panelT.add(X2);

            JLabel YT2 = new JLabel();
            YT2.setText("position y2");
            panelT.add(YT2);

            JTextField Y2 = new JTextField();
            panelT.add(Y2);

            JLabel XT3 = new JLabel();
            XT3.setText("position x3");
            panelT.add(XT3);

            JTextField X3 = new JTextField();
            panelT.add(X3);

            JLabel YT3 = new JLabel();
            YT3.setText("position y3");
            panelT.add(YT3);

            JTextField Y3 = new JTextField();
            panelT.add(Y3);

            JButton creat = new JButton("creat");
            panelT.add(creat);

            JButton innercolor = new JButton("Inner color");
            JButton outerline = new JButton("outerline");
            panelT.add(innercolor);
            panelT.add(outerline);
            Triangle newT = new Triangle();
            creat.addActionListener(e1 -> {
                String s1 = X1.getText();
                String s2 = Y1.getText();

                String s3 = X2.getText();
                String s4 = Y2.getText();

                String s5 = X3.getText();
                String s6 = Y3.getText();
                if (validation(s1) && validation(s2) && validation(s3) && validation(s4) && validation(s5) && validation(s6)) {
                    int x1 = Integer.parseInt(s1);
                    int y1 = Integer.parseInt(s2);
                    Point pt1 = new Point(x1, y1);


                    int x2 = Integer.parseInt(s3);
                    int y2 = Integer.parseInt(s4);
                    Point pt2 = new Point(x2, y2);


                    int x3 = Integer.parseInt(s5);
                    int y3 = Integer.parseInt(s6);
                    Point pt3 = new Point(x3, y3);

                    newT.setPosition(pt1);
                    newT.setP2(pt2);
                    newT.setP3(pt3);
                    controlV.addShape(newT);
                    controlV.refresh(drawingBoard.getGraphics());
                    comboBox1.addItem(newT.getID());

                } else
                    JOptionPane.showMessageDialog(null, "please enter valid inputs");
            });

            outerline.addActionListener(e1 -> {
                AtomicReference<Color> newColor = new AtomicReference<>(Color.blue);
                newColor.set(JColorChooser.showDialog(null, "select a color for outline", newColor.get()));
                newT.setColor(newColor.get());
                controlV.refresh(drawingBoard.getGraphics());
            });

            innercolor.addActionListener(e1 -> {
                AtomicReference<Color> newColor = new AtomicReference<>(Color.blue);
                newColor.set(JColorChooser.showDialog(null, "select a color for inner shape", newColor.get()));
                newT.setFillColor(newColor.get());
                controlV.refresh(drawingBoard.getGraphics());
            });
        });

        lineSegmentButton.addActionListener((e) -> {
            JFrame FrameL = new JFrame();
            FrameL.setTitle("create a line");
            FrameL.setVisible(true);
            FrameL.setSize(350, 350);
            JPanel panelL = new JPanel();
            panelL.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            panelL.setLayout(new GridLayout(0, 1));
            FrameL.add(panelL, BorderLayout.CENTER);
            JLabel XT1 = new JLabel();
            XT1.setText("position x1");
            panelL.add(XT1);

            JTextField X1 = new JTextField();
            panelL.add(X1);

            JLabel YT1 = new JLabel();
            YT1.setText("position y1");
            panelL.add(YT1);

            JTextField Y1 = new JTextField();
            panelL.add(Y1);

            JLabel XT2 = new JLabel();
            XT2.setText("position x2");
            panelL.add(XT2);

            JTextField X2 = new JTextField();
            panelL.add(X2);

            JLabel YT2 = new JLabel();
            YT2.setText("position y2");
            panelL.add(YT2);

            JTextField Y2 = new JTextField();
            panelL.add(Y2);

            JButton creat = new JButton("creat");
            panelL.add(creat);

            JButton outerline = new JButton("outerline");
            panelL.add(outerline);

            Line_segment newL = new Line_segment();
            creat.addActionListener(e1 -> {
                String s1 = X1.getText();
                String s2 = Y1.getText();
                String s3 = X2.getText();
                String s4 = Y2.getText();
                if (validation(s1) && validation(s2) && validation(s3) && validation(s4)) {
                    int x1 = Integer.parseInt(s1);
                    int y1 = Integer.parseInt(s2);
                    Point pt1 = new Point(x1, y1);


                    int x2 = Integer.parseInt(s3);
                    int y2 = Integer.parseInt(s4);
                    Point pt2 = new Point(x2, y2);

                    newL.setPosition(pt1);
                    newL.setEndPoint(pt2);

                    controlV.addShape(newL);
                    controlV.refresh(drawingBoard.getGraphics());
                    comboBox1.addItem(newL.getID());

                } else
                    JOptionPane.showMessageDialog(null, "please enter valid inputs");
            });
            outerline.addActionListener(e1 -> {
                AtomicReference<Color> newColor = new AtomicReference<>(Color.blue);
                newColor.set(JColorChooser.showDialog(null, "select a color for outline", newColor.get()));
                newL.setColor(newColor.get());
                controlV.refresh(drawingBoard.getGraphics());
            });
        });


        deleteButton.addActionListener((e) -> {
            int i = 0;
            if (comboBox1.getSelectedItem() == null)
                return;
            String S = comboBox1.getSelectedItem().toString();
            while (i < controlV.getSizeR()) {
                if (controlV.getShapes()[i].getID().equals(S)) {
                    selectShape = controlV.getShapes()[i];
                    break;
                }
                i++;
            }
            controlV.removeShape(selectShape);
            controlV.refresh(drawingBoard.getGraphics());
            comboBox1.removeItem(selectShape.getID());
        });
        colorizeButton.addActionListener((e) -> {
            if (comboBox1.getSelectedItem() == null)
                return;
            String shapeSelected = comboBox1.getSelectedItem().toString();

            JFrame newFrame = new JFrame();
            newFrame.setVisible(true);
            newFrame.setSize(300, 300);
            JPanel panel2 = new JPanel();
            panel2.setBorder(BorderFactory.createEmptyBorder(80, 80, 40, 80));
            panel2.setLayout(new GridLayout(0, 1));
            newFrame.add(panel2, BorderLayout.CENTER);
            JButton outerline = new JButton();
            outerline.setText("outerline");
            panel2.add(outerline);
            JButton fillerShape = new JButton();
            fillerShape.setText("fillerShape");
            panel2.add(fillerShape);
            outerline.addActionListener((e1) -> {
                int i = 0;

                while (i < controlV.getSizeR()) {
                    if (controlV.getShapes()[i].getID().equals(shapeSelected)) {
                        selectShape = controlV.getShapes()[i];
                        break;
                    }
                    i++;
                }
                AtomicReference<Color> newColor = new AtomicReference<>(Color.blue);
                newColor.set(JColorChooser.showDialog(null, "select a color for outline", newColor.get()));
                selectShape.setColor(newColor.get());
                controlV.refresh(drawingBoard.getGraphics());
            });
            fillerShape.addActionListener((e2) -> {
                int i = 0;
                String S = comboBox1.getSelectedItem().toString();
                while (i < controlV.getSizeR()) {
                    if (controlV.getShapes()[i].getID().equals(S)) {
                        selectShape = controlV.getShapes()[i];
                        break;
                    }
                    i++;
                }
                AtomicReference<Color> newColor2 = new AtomicReference<>(Color.blue);
                newColor2.set(JColorChooser.showDialog(null, "select a color for outline", newColor2.get()));
                selectShape.setFillColor(newColor2.get());
                controlV.refresh(drawingBoard.getGraphics());
            });

        });

        copyButton.addActionListener(e -> {
            int i = 0;
            int count = (int) Math.floor(Math.random() * 10);
            if (comboBox1.getSelectedItem() == null)
                return;
            String S = comboBox1.getSelectedItem().toString();
            while (i < controlV.getSizeR()) {
                if (controlV.getShapes()[i].getID().equals(S)) {
                    selectShape = controlV.getShapes()[i];
                    break;
                }
                i++;
            }
            Shapes copyShape;
            try {
                copyShape = selectShape.clone();
            } catch (CloneNotSupportedException ex) {
                throw new RuntimeException(ex);
            }
            copyShape.setID(count);
            comboBox1.addItem(copyShape.getID());
            controlV.addShape(copyShape);
            controlV.refresh(drawingBoard.getGraphics());


        });
    }

    public static void main(String[]args){
        new PaintGUI();

    }

    private void save() {
        JFileChooser jFileChooser = new JFileChooser();

        int result = jFileChooser.showSaveDialog(frameMain);

        if(result == JFileChooser.APPROVE_OPTION) {
            File f = jFileChooser.getSelectedFile();

            try {
                FileWriter writer = new FileWriter(f);

                JSONArray array = new JSONArray();

                for(Shapes shape : controlV.getShapes()) {
                    array.add(shape.shapeToJson());
                }

                writer.write(array.toJSONString());

                writer.close();
                writer.flush();
            } catch (IOException ignored) {}
        }
    }

    private void load() {
        JFileChooser jFileChooser = new JFileChooser();

        int result = jFileChooser.showOpenDialog(frameMain);

        if(result == JFileChooser.APPROVE_OPTION) {
            File f = jFileChooser.getSelectedFile();

            try {
                Scanner scanner = new Scanner(f);

                StringBuilder jsonString = new StringBuilder();

                while (scanner.hasNextLine()) {
                    jsonString.append(scanner.nextLine());
                }

                JSONArray array = (JSONArray) new JSONParser().parse(jsonString.toString());

                comboBox1.removeAll();
                for (Shapes s : controlV.getShapes()) {
                    controlV.removeShape(s);
                }

                for(Object shapeObject : array) {
                    JSONObject shapeJSON = (JSONObject) shapeObject;

                    Shapes shape = Shapes.fromString(shapeJSON);

                    controlV.addShape(shape);

                    comboBox1.addItem(shape.getID());
                }

                controlV.refresh();


            } catch (IOException | ParseException ignored) {}
        }
    }

    public class Click extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent E){
            if(selectShape != null) {
                for (Point p : selectShape.getPoints()) {
                    if(p.distance(E.getPoint()) < 10) {
                        return;
                    }
                }
            }

            selectShape=null;
            for(Shapes s : controlV.getShapes()){
                if(s.contains(E.getPoint()))
                { comboBox1.setSelectedItem(s.getID());
                selectShape=s;
                selectShape.setDraggingPoint(E.getPoint());
                selectShape.resizeRects(drawingBoard.getGraphics(),selectShape.getCornerShapes(selectShape.getPoints()));
                }

            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            resizePoint = null;
        }
    }

    public class Drag extends MouseMotionAdapter{
        @Override
        public void mouseDragged(MouseEvent E){
            if(selectShape == null) return;

            if (resizePoint == null) {
                for (Shapes p : selectShape.getCornerShapes(selectShape.getPoints())) {
                    if (p.contains(E.getPoint())) {
                        resizePoint = p.getPosition();
                        break;
                    }
                }
            }

            if (resizePoint != null) {
                resizePoint = selectShape.resize(E.getPoint(), resizePoint);
                controlV.refresh();
                return;
            }

            selectShape.moveTo(E.getPoint());
            selectShape.setDraggingPoint(E.getPoint());
            controlV.refresh();
        }
    }

    Point resizePoint = null;
}


