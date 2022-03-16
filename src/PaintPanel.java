import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

enum DrawType {Line, Rectangle, Oval, RoundRectangle, SolidRoundRectangle, SolidRectangle, SolidOval, Brush, Nothing};

public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener {

    private int xStart = 0;
    private int yStart = 0;
    private int xEnd = 0;
    private int yEnd = 0;

    private String shape = null;
    private Color shapeColor = null;
    private DrawType drawType = DrawType.Nothing;
    private Shape inProgress = null;
    private Stack<Shape> shapeHistory = null;
    private Stack<Integer> brushHistory = null;
    private int brushCount = 0;

    public PaintPanel() {
        super();
        this.setSize(500, 500);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        shape = "";
        shapeColor = Color.BLACK;
        shapeHistory = new Stack<>();
        brushHistory = new Stack<>();
    }

    public void setShape(String s) {
        shape = s;
        switch(shape)
        {
            case "Brush":
                drawType = DrawType.Brush;
                break;
            case "Straight Line":
                drawType = DrawType.Line;
                break;
            case "Rectangle":
                drawType = DrawType.Rectangle;
                break;
            case "Solid Rectangle":
                drawType = DrawType.SolidRectangle;
                break;
            case "Round Rectangle":
                drawType = DrawType.RoundRectangle;
                break;
            case "Solid Round Rectangle":
                drawType = DrawType.SolidRoundRectangle;
                break;
            case "Oval":
                drawType = DrawType.Oval;
                break;
            case "Solid Oval":
                drawType = DrawType.SolidOval;
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
        shapeHistory.push(s);
        repaint();
    }

    public void clear() {
        shapeHistory.removeAllElements();
    }

    public void undo() {
        if (shapeHistory.size() > 0) {
            String shapeType = shapeHistory.peek().getClass().getName();

            // Multiple pops need to be performed if the previous shape was a brush
            if (shapeType.equals("Brush")) {
                int strokeNumber = brushHistory.pop();
                for (int i = 0; i <= strokeNumber; i++) {
                    shapeHistory.pop();
                }
            } else {
                shapeHistory.pop();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(shapeColor);

        // Entire canvas gets "repainted"
        for (Shape s : shapeHistory) {
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
                brushCount++;   // increment number of ovals in current brush stroke
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
            case Brush:
                inProgress = new Brush(xStart, yStart, shapeColor);
                break;
            case Line:
                inProgress = new StraightLine(xStart, yStart, shapeColor);
                break;
            case Rectangle:
                inProgress = new Rectangle(xStart, yStart, shapeColor);
                break;
            case SolidRectangle:
                inProgress = new SolidRectangle(xStart, yStart, shapeColor);
                break;
            case RoundRectangle:
                inProgress = new RoundRectangle(xStart, yStart, shapeColor);
                break;
            case SolidRoundRectangle:
                inProgress = new SolidRoundRectangle(xStart, yStart, shapeColor);
                break;
            case Oval:
                inProgress = new Oval(xStart, yStart, shapeColor);
                break;
            case SolidOval:
                inProgress = new SolidOval(xStart, yStart, shapeColor);
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

            // Adds count of ovals in brush stroke to brush history
            if (drawType == DrawType.Brush) {
                brushHistory.push(brushCount);
                brushCount = 0; // reset global brush count
            }
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
