import java.awt.*;

public abstract class Shape {
    private int xStart, yStart;
    private Color color;
    public abstract void draw(Graphics g);
    public abstract void subsequentPoint(int x, int y);

    public Shape(int x, int y, Color c) {
        xStart = x;
        yStart = y;
        color = c;
    }

    public void setColor(Graphics g) {
        g.setColor(color);
    }

    public int getX() {
        return xStart;
    }

    public int getY() {
        return yStart;
    }
}

class StraightLine extends Shape {
    private int xEnd, yEnd;

    public StraightLine(int x, int y, Color c) {
        super(x,y,c);
        xEnd = x;
        yEnd = y;
    }

    @Override
    public void draw(Graphics g) {
        setColor(g);
        g.drawLine(getX(), getY(), xEnd, yEnd);
    }

    @Override
    public void subsequentPoint(int x, int y) {
        xEnd = x;
        yEnd = y;
    }
}

class Rectangle extends Shape {
    private int xEnd, yEnd;

    public Rectangle(int x, int y, Color c) {
        super(x,y,c);
        xEnd = x;
        yEnd = y;
    }

    @Override
    public void draw(Graphics g) {
        setColor(g);
        int width = Math.abs(xEnd - getX());
        int height = Math.abs(yEnd - getY());
        int x = Math.min(getX(), xEnd);
        int y = Math.min(getY(), yEnd);
        g.drawRect(x, y, width, height);
    }

    @Override
    public void subsequentPoint(int x, int y) {
        xEnd = x;
        yEnd = y;
    }
}

class Oval extends Shape {
    private int xEnd, yEnd;

    public Oval(int x, int y, Color c) {
        super(x,y,c);
        xEnd = x;
        yEnd = y;
    }

    @Override
    public void draw(Graphics g) {
        setColor(g);
        int width = Math.abs(xEnd - getX());
        int height = Math.abs(yEnd - getY());
        int x = Math.min(getX(), xEnd);
        int y = Math.min(getY(), yEnd);
        g.drawOval(x, y, width, height);
    }

    @Override
    public void subsequentPoint(int x, int y) {
        xEnd = x;
        yEnd = y;
    }
}

class RoundRectangle extends Shape {
    private int xEnd, yEnd;

    public RoundRectangle(int x, int y, Color c) {
        super(x,y,c);
        xEnd = x;
        yEnd = y;
    }

    @Override
    public void draw(Graphics g) {
        setColor(g);
        int width = Math.abs(xEnd - getX());
        int height = Math.abs(yEnd - getY());
        int x = Math.min(getX(), xEnd);
        int y = Math.min(getY(), yEnd);
        g.drawRoundRect(x, y, width, height, 25, 25);
    }

    @Override
    public void subsequentPoint(int x, int y) {
        xEnd = x;
        yEnd = y;
    }
}

class FilledRoundRectangle extends Shape {
    private int xEnd, yEnd;

    public FilledRoundRectangle(int x, int y, Color c) {
        super(x,y,c);
        xEnd = x;
        yEnd = y;
    }

    @Override
    public void draw(Graphics g) {
        setColor(g);
        int width = Math.abs(xEnd - getX());
        int height = Math.abs(yEnd - getY());
        int x = Math.min(getX(), xEnd);
        int y = Math.min(getY(), yEnd);
        g.fillRoundRect(x, y, width, height, 25, 25);

    }

    @Override
    public void subsequentPoint(int x, int y) {
        xEnd = x;
        yEnd = y;
    }
}

class FilledRectangle extends Shape {
    private int xEnd, yEnd;

    public FilledRectangle(int x, int y, Color c) {
        super(x,y,c);
        xEnd = x;
        yEnd = y;
    }

    @Override
    public void draw(Graphics g) {
        setColor(g);
        int width = Math.abs(xEnd - getX());
        int height = Math.abs(yEnd - getY());
        int x = Math.min(getX(), xEnd);
        int y = Math.min(getY(), yEnd);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void subsequentPoint(int x, int y) {
        xEnd = x;
        yEnd = y;
    }
}

class FilledOval extends Shape {
    private int xEnd, yEnd;

    public FilledOval(int x, int y, Color c) {
        super(x,y,c);
        xEnd = x;
        yEnd = y;
    }

    @Override
    public void draw(Graphics g) {
        setColor(g);
        int width = Math.abs(xEnd - getX());
        int height = Math.abs(yEnd - getY());
        int x = Math.min(getX(), xEnd);
        int y = Math.min(getY(), yEnd);
        g.fillOval(x, y, width, height);
    }

    @Override
    public void subsequentPoint(int x, int y) {
        xEnd = x;
        yEnd = y;
    }
}

class Brush extends Shape {
    private int xEnd, yEnd;

    public Brush(int x, int y, Color c) {
        super(x,y,c);
        xEnd = x;
        yEnd = y;
    }

    @Override
    public void draw(Graphics g) {
        setColor(g);
        g.fillOval(xEnd, yEnd, 10, 10);
    }

    @Override
    public void subsequentPoint(int x, int y) {
        xEnd = getX();
        yEnd = getY();
    }
}
