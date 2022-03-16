import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.*;

public class PaintFrame extends JFrame {

    private PaintPanel drawingPanel = null;

    private JMenu currentColor = null;

    private int fontStyle = Font.PLAIN;
    private int fontSize = 10;
    private Font font = null;

    private JTextField textField = null;

    private String[] shapeOptions = {"Brush", "Straight Line", "Rectangle", "Solid Rectangle",
            "Round Rectangle", "Solid Round Rectangle", "Oval", "Solid Oval"};
    private String[] colorOptions = {"Black", "White", "Blue", "Cyan", "Green", "Magenta", "Red"};
    private String[] styleOptions = {"Plain", "Bold", "Italics"};
    private String[] sizeOptions = {"SIZE 10", "SIZE 20", "SIZE 72"};

    public PaintFrame() {
        super("Brandon's Simple Paint Program");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Creating Figure Menu
        ShapeMenuListener shapeMenuListener = new ShapeMenuListener();
        JMenu shapeMenu = new JMenu("Shape");
        JMenuItem[] shapes = new JMenuItem[shapeOptions.length];
        addActionListenersAndMenuItems(shapeMenu, shapes, shapeMenuListener, shapeOptions);

        // Creating the Color Menu
        JMenu colorMenu = new JMenu("Color");
        currentColor = new JMenu("\u25A0");
        currentColor.setBackground(Color.BLACK);

        // Foreground color menu
        ForegroundColorListener fcl = new ForegroundColorListener();
        JMenu foregroundMenu = new JMenu("Foreground");
        JMenuItem[] foregroundOptions = new JMenuItem[colorOptions.length];
        addActionListenersAndMenuItems(foregroundMenu, foregroundOptions, fcl,colorOptions);

        // Background color menu
        BackgroundColorListener bcl = new BackgroundColorListener();
        JMenu backgroundMenu = new JMenu("Background");
        JMenuItem[] backgroundOptions = new JMenuItem[colorOptions.length];
        addActionListenersAndMenuItems(backgroundMenu, backgroundOptions, bcl, colorOptions);

        // Adding foreground and background menus to color menu
        colorMenu.add(foregroundMenu);
        colorMenu.addSeparator();
        colorMenu.add(backgroundMenu);

        // Creating the Font Menu
        FontMenuListener fml = new FontMenuListener();
        JMenu fontMenu = new JMenu("Font");

        // Font Style menu
        JMenu styleMenu = new JMenu("Style");
        JMenuItem[] styles = new JMenuItem[styleOptions.length];
        addActionListenersAndMenuItems(styleMenu, styles, fml, styleOptions);

        // Font Size menu
        JMenu sizeMenu = new JMenu("Size");
        JMenuItem[] sizes = new JMenuItem[sizeOptions.length];
        addActionListenersAndMenuItems(sizeMenu, sizes, fml, sizeOptions);

        // Adding Style menu and Size menu to the Font Menu
        fontMenu.add(styleMenu);
        fontMenu.add(sizeMenu);
        fontMenu.setToolTipText("If you change the font size, style, or text, you may have to resize the window to fix alignment.");

        // Undo button
        JMenuItem undo = new JMenuItem("Undo");
        undo.addActionListener(fcl);

        // Clear canvas button for the menu bar
        JMenuItem clearCanvas = new JMenuItem("Clear Canvas");
        clearCanvas.addActionListener(fcl);

        // Creating Menu Bar and Adding Components
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(shapeMenu);
        menuBar.add(colorMenu);
        menuBar.add(currentColor);
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

    public void addActionListenersAndMenuItems(JMenu menu, JMenuItem[] menuItems, ActionListener listener, String[] options) {
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i] = new JMenuItem(options[i]);   // initializes menu item
            menuItems[i].addActionListener(listener);   // adds action listener to menu item
            menu.add(menuItems[i]);                     // adds menu item to menu
        }
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
                case "Clear Canvas":
                    drawingPanel.clear();
                case "Undo":
                    drawingPanel.undo();
                default:
                    drawingPanel.setForeground(null);
                    break;
            }
            currentColor.setForeground(drawingPanel.getShapeColor());   // displays currently selected color
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
