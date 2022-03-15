import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

enum DrawType {Line, Rectangle, Oval, RoundRectangle, FilledRoundRectangle, FilledRectangle, FilledOval, Brush, Nothing};

public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener {

    private int xStart = 0;
    private int yStart = 0;
    private int xEnd = 0;
    private int yEnd = 0;

    private String shape = null;
    private Color shapeColor = null;
    private DrawType drawType = DrawType.Nothing;
    private Shape inProgress = null;
    private ArrayList<Shape> shapeArray = null;

    public PaintPanel() {
        super();
        this.setSize(500, 500);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        shape = "";
        shapeColor = Color.BLACK;
        shapeArray = new ArrayList<Shape>();
    }

    public void setShape(String s) {
        shape = s;
        switch(shape)
        {
            case "Straight Line":
                drawType = DrawType.Line;
                break;
            case "Rectangle":
                drawType = DrawType.Rectangle;
                break;
            case "Oval":
                drawType = DrawType.Oval;
                break;
            case "Round Rectangle":
                drawType = DrawType.RoundRectangle;
                break;
            case "Filled Round Rectangle":
                drawType = DrawType.FilledRoundRectangle;
                break;
            case "Filled Rectangle":
                drawType = DrawType.FilledRectangle;
                break;
            case "Filled Oval":
                drawType = DrawType.FilledOval;
                break;
            case "Brush":
                drawType = DrawType.Brush;
                break;
            default:
                break;
        }
    }

    public String getShape() {
        return shape;
    }

    public void setShapeColor(Color c) {
        shapeColor = c;
    }

    public Color getShapeColor() {
        return shapeColor;
    }

    public void addShape(Shape s) {
        shapeArray.add(s);
        repaint();
    }

    public void clear() {
        shapeArray.removeAll(shapeArray);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(shapeColor);

        for(int i = 0; i < shapeArray.size(); i++) {
            Shape s = shapeArray.get(i);
            s.draw(g);
        }

        if (inProgress != null)
            inProgress.draw(g);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        xEnd = e.getX();
        yEnd = e.getY();
        if (inProgress != null) {
            if (drawType == DrawType.Brush) {
                inProgress.subsequentPoint(xEnd, yEnd);
                addShape(inProgress);
                inProgress = new Brush(xEnd, yEnd, shapeColor);

            } else {
                inProgress.subsequentPoint(xEnd, yEnd);
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        xStart = e.getX();
        yStart = e.getY();
        switch(drawType)
        {
            case Line:
                inProgress = new StraightLine(xStart, yStart, shapeColor);
                break;
            case Rectangle:
                inProgress = new Rectangle(xStart, yStart, shapeColor);
                break;
            case Oval:
                inProgress = new Oval(xStart, yStart, shapeColor);
                break;
            case RoundRectangle:
                inProgress = new RoundRectangle(xStart, yStart, shapeColor);
                break;
            case FilledRoundRectangle:
                inProgress = new FilledRoundRectangle(xStart, yStart, shapeColor);
                break;
            case FilledRectangle:
                inProgress = new FilledRectangle(xStart, yStart, shapeColor);
                break;
            case FilledOval:
                inProgress = new FilledOval(xStart, yStart, shapeColor);
                break;
            case Brush:
                inProgress = new Brush(xStart, yStart, shapeColor);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        xEnd = e.getX();
        yEnd = e.getY();
        if (inProgress != null) {
            inProgress.subsequentPoint(xEnd, yEnd);
            addShape(inProgress);
            inProgress = null;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}