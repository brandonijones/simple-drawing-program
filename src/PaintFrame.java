import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.*;

public class PaintFrame extends JFrame {

    private PaintPanel drawingPanel = null;

    private int fontStyle = Font.PLAIN;
    private int fontSize = 10;
    private Font font = null;

    private JTextField textField = null;

    public PaintFrame() {
        super("Brandon's Simple Paint Program");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Creating Figure Menu
        ShapeMenuListener shapeMenuListener = new ShapeMenuListener();
        JMenu shapeMenu = new JMenu("Shape");
        JMenuItem shapeBrush = new JMenuItem("Brush");
        JMenuItem shapeLine = new JMenuItem("Straight Line");
        JMenuItem shapeRectangle = new JMenuItem("Rectangle");
        JMenuItem shapeSolidRect = new JMenuItem("Solid Rectangle");
        JMenuItem shapeRoundRect = new JMenuItem("Round Rectangle");
        JMenuItem shapeSolidRR = new JMenuItem("Solid Round Rectangle");
        JMenuItem shapeOval = new JMenuItem("Oval");
        JMenuItem shapeSolidOval = new JMenuItem("Solid Oval");

        // Adding Action Listener to each menu item
        shapeBrush.addActionListener(shapeMenuListener);
        shapeLine.addActionListener(shapeMenuListener);
        shapeRectangle.addActionListener(shapeMenuListener);
        shapeSolidRect.addActionListener(shapeMenuListener);
        shapeRoundRect.addActionListener(shapeMenuListener);
        shapeSolidRR.addActionListener(shapeMenuListener);
        shapeOval.addActionListener(shapeMenuListener);
        shapeSolidOval.addActionListener(shapeMenuListener);

        // Adding each menu item to the menu
        shapeMenu.add(shapeBrush);
        shapeMenu.add(shapeLine);
        shapeMenu.add(shapeRectangle);
        shapeMenu.add(shapeSolidRect);
        shapeMenu.add(shapeRoundRect);
        shapeMenu.add(shapeSolidRR);
        shapeMenu.add(shapeOval);
        shapeMenu.add(shapeSolidOval);

        // Creating the Color Menu
        ForegroundColorListener fcl = new ForegroundColorListener();
        JMenu colorMenu = new JMenu("Color");

        // Foreground color menu
        JMenu foregroundMenu = new JMenu("Foreground");
        JMenuItem[] foregroundOptions = {new JMenuItem("Black"),
                new JMenuItem("White"),
                new JMenuItem("Blue"),
                new JMenuItem("Cyan"),
                new JMenuItem("Green"),
                new JMenuItem("Magenta"),
                new JMenuItem("Red")};

        // Adding Action listener to each menu item
        for (JMenuItem foregroundOption : foregroundOptions) {
            foregroundMenu.add(foregroundOption);
            foregroundOption.addActionListener(fcl);
        }

        // Background color menu
        BackgroundColorListener bcl = new BackgroundColorListener();
        JMenu backgroundMenu = new JMenu("Background");
        JMenuItem[] backgroundOptions = {new JMenuItem("Black"),
                new JMenuItem("White"),
                new JMenuItem("Blue"),
                new JMenuItem("Cyan"),
                new JMenuItem("Green"),
                new JMenuItem("Magenta"),
                new JMenuItem("Red")};

        // Adding Action listener to each menu item
        for (JMenuItem backgroundOption : backgroundOptions) {
            backgroundMenu.add(backgroundOption);
            backgroundOption.addActionListener(bcl);
        }

        // Adding foreground and background menus to color menu
        colorMenu.add(foregroundMenu);
        colorMenu.addSeparator();
        colorMenu.add(backgroundMenu);

        // Creating the Font Menu
        FontMenuListener fml = new FontMenuListener();
        JMenu fontMenu = new JMenu("Font");

        // Font Style menu
        JMenu styleMenu = new JMenu("Style");
        JMenuItem stylePlain = new JMenuItem("Plain");
        JMenuItem styleBold = new JMenuItem("Bold");
        JMenuItem styleItalics = new JMenuItem("Italics");

        // Font Size menu
        JMenu sizeMenu = new JMenu("Size");
        JMenuItem sizeTen = new JMenuItem("SIZE 10");
        JMenuItem sizeTwenty = new JMenuItem("SIZE 20");
        JMenuItem sizeSeventyTwo = new JMenuItem("SIZE 72");

        // Adding action listeners for font style and font size menus
        stylePlain.addActionListener(fml);
        styleBold.addActionListener(fml);
        styleItalics.addActionListener(fml);
        sizeTen.addActionListener(fml);
        sizeTwenty.addActionListener(fml);
        sizeSeventyTwo.addActionListener(fml);

        // Adding Style menu and Size menu to the Font Menu
        fontMenu.add(styleMenu);
        styleMenu.add(stylePlain);
        styleMenu.add(styleBold);
        styleMenu.add(styleItalics);
        fontMenu.add(sizeMenu);
        sizeMenu.add(sizeTen);
        sizeMenu.add(sizeTwenty);
        sizeMenu.add(sizeSeventyTwo);
        fontMenu.setToolTipText("If you change the font size, style, or text, you may have to resize the window to fix alignment.");

        // Undo button
        JMenuItem undo = new JMenuItem("Undo");
        undo.addActionListener(fcl);

        // Clear canvas button for the menu bar
        JMenuItem clearCanvas = new JMenuItem("Clear");
        clearCanvas.addActionListener(fcl);

        // Creating Menu Bar and Adding Components
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(shapeMenu);
        menuBar.add(colorMenu);
        menuBar.add(fontMenu);
        menuBar.add(undo);
        menuBar.add(clearCanvas);
        this.setJMenuBar(menuBar);

        // Creating and Adding Paint Panel
        drawingPanel = new PaintPanel();
        drawingPanel.setBackground(Color.WHITE);
        this.add(drawingPanel, BorderLayout.CENTER);

        // Creating Text Panel
        JPanel textPanel = new JPanel();
        textField = new JTextField("Title Your Masterpiece Here");
        font = new Font(Font.SERIF, fontStyle, fontSize);
        textField.setFont(font);

        // Allowing for TextField to change size w/ text size
        AffineTransform affineTransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affineTransform, true, true);
        int textWidth = (int) (font.getStringBounds(textField.getText(), frc).getWidth());
        int textHeight = (int) (font.getStringBounds(textField.getText(), frc).getHeight());
        textField.setSize(textWidth + 1, textHeight + 1);

        // Adding TextPanel and TextField to PaintFrame
        textField.setToolTipText("If you change the font size, style, or text, you may have to resize the window to fix alignment.");
        textPanel.add(textField);
        this.add(textPanel, BorderLayout.SOUTH);
    }

    private class ShapeMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String caption = e.getActionCommand();
            drawingPanel.setShape(caption);
        }
    }

    private class ForegroundColorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String caption = e.getActionCommand();
            switch(caption)
            {
                case "Black":
                    drawingPanel.setShapeColor(Color.BLACK);
                    break;
                case "White":
                    drawingPanel.setShapeColor(Color.WHITE);
                    break;
                case "Blue":
                    drawingPanel.setShapeColor(Color.BLUE);
                    break;
                case "Cyan":
                    drawingPanel.setShapeColor(Color.CYAN);
                    break;
                case "Green":
                    drawingPanel.setShapeColor(Color.GREEN);
                    break;
                case "Magenta":
                    drawingPanel.setShapeColor(Color.MAGENTA);
                    break;
                case "Red":
                    drawingPanel.setShapeColor(Color.RED);
                    break;
                case "Clear":
                    drawingPanel.clear();
                case "Undo":
                    drawingPanel.undo();
                default:
                    drawingPanel.setForeground(null);
                    break;
            }
        }
    }

    private class BackgroundColorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String caption = e.getActionCommand();
            switch(caption)
            {
                case "Black":
                    drawingPanel.setBackground(Color.BLACK);
                    break;
                case "White":
                    drawingPanel.setBackground(Color.WHITE);
                    break;
                case "Blue":
                    drawingPanel.setBackground(Color.BLUE);
                    break;
                case "Cyan":
                    drawingPanel.setBackground(Color.CYAN);
                    break;
                case "Green":
                    drawingPanel.setBackground(Color.GREEN);
                    break;
                case "Magenta":
                    drawingPanel.setBackground(Color.MAGENTA);
                    break;
                case "Red":
                    drawingPanel.setBackground(Color.RED);
                    break;
                default:
                    drawingPanel.setBackground(null);
                    break;
            }
        }
    }

    private class FontMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String caption = e.getActionCommand();
            switch (caption)
            {
                case "Plain":
                    fontStyle = Font.PLAIN;
                    font = new Font(Font.SERIF, fontStyle, fontSize);
                    textField.setFont(font);
                    break;
                case "Bold":
                    fontStyle = Font.BOLD;
                    font = new Font(Font.SERIF, fontStyle, fontSize);
                    textField.setFont(font);
                    break;
                case "Italics":
                    fontStyle = Font.ITALIC;
                    font = new Font(Font.SERIF, fontStyle, fontSize);
                    textField.setFont(font);
                    break;
                case "SIZE 10":
                    fontSize = 10;
                    font = new Font(Font.SERIF, fontStyle, fontSize);
                    textField.setFont(font);
                    break;
                case "SIZE 20":
                    fontSize = 20;
                    font = new Font(Font.SERIF, fontStyle, fontSize);
                    textField.setFont(font);
                    break;
                case "SIZE 72":
                    fontSize = 72;
                    font = new Font(Font.SERIF, fontStyle, fontSize);
                    textField.setFont(font);
                    break;
                default:
                    textField.setFont(new Font(Font.SERIF, Font.PLAIN, 10));
                    break;
            }
        }
    }
}
