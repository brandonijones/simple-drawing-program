package com.brandonijones;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.*;

public class PaintFrame extends JFrame {

    private PaintPanel drawingPan = null;

    private JMenu figureMenu = null;
    private JMenuItem figureLine = null;
    private JMenuItem figureRectangle = null;
    private JMenuItem figureOval = null;
    private JMenuItem figureRoundRect = null;
    private JMenuItem figureFilledRR = null;
    private JMenuItem figureFilledRect = null;
    private JMenuItem figureFilledOval = null;
    private JMenuItem figureBrush = null;
    private String[] shapeOptions = null;

    private JMenu colorMenu = null;
    private JMenu foregroundMenu = null;
    private JMenu backgroundMenu = null;
    private JMenuItem[] foregroundOptions = {new JMenuItem("Black"),
            new JMenuItem("White"),
            new JMenuItem("Blue"),
            new JMenuItem("Cyan"),
            new JMenuItem("Green"),
            new JMenuItem("Magenta"),
            new JMenuItem("Red")};
    private JMenuItem[] backgroundOptions = {new JMenuItem("Black"),
            new JMenuItem("White"),
            new JMenuItem("Blue"),
            new JMenuItem("Cyan"),
            new JMenuItem("Green"),
            new JMenuItem("Magenta"),
            new JMenuItem("Red")};
    private JMenuItem colorClear = null;
    private Color foregroundColor = Color.BLACK;

    private JMenu fontMenu = null;
    private JMenu styleMenu = null;
    private JMenuItem stylePlain = null;
    private JMenuItem styleBold = null;
    private JMenuItem styleItalics = null;
    private JMenu sizeMenu = null;
    private JMenuItem sizeTen = null;
    private JMenuItem sizeTwenty = null;
    private JMenuItem sizeSeventyTwo = null;
    private int fstyle = Font.PLAIN;
    private int fsize = 10;
    private int textWidth = 0;
    private int textHeight = 0;
    private Font font = null;
    private AffineTransform affineTransform = null;
    private FontRenderContext frc = null;

    private JMenuBar bar = null;
    private JPanel textPanel = null;
    private JTextField tf = null;


    public PaintFrame() {
        super("Brandon's Simple Paint Program");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Creating Figure Menu
        FigureMenuListener figml = new FigureMenuListener();
        figureMenu = new JMenu("Figure");
        figureLine = new JMenuItem("Straight Line");
        figureRectangle = new JMenuItem("Rectangle");
        figureOval = new JMenuItem("Oval");
        figureRoundRect = new JMenuItem("Round Rectangle");
        figureFilledRR = new JMenuItem("Filled Round Rectangle");
        figureFilledRect = new JMenuItem("Filled Rectangle");
        figureFilledOval = new JMenuItem("Filled Oval");
        figureBrush = new JMenuItem("Brush");
        shapeOptions = new String[]{"line","rectangle", "oval", "roundR", "filledRR", "filledR", "filledO", "brush"};

        figureLine.addActionListener(figml);
        figureRectangle.addActionListener(figml);
        figureOval.addActionListener(figml);
        figureRoundRect.addActionListener(figml);
        figureFilledRR.addActionListener(figml);
        figureFilledRect.addActionListener(figml);
        figureFilledOval.addActionListener(figml);
        figureBrush.addActionListener(figml);

        figureMenu.add(figureLine);
        figureMenu.add(figureRectangle);
        figureMenu.add(figureOval);
        figureMenu.add(figureRoundRect);
        figureMenu.add(figureFilledRR);
        figureMenu.add(figureFilledRect);
        figureMenu.add(figureFilledOval);
        figureMenu.add(figureBrush);

        // Creating the Color Menu
        ForegroundColorListener fcl = new ForegroundColorListener();
        colorMenu = new JMenu("Color");
        foregroundMenu = new JMenu("Foreground");
        for (int f = 0; f < foregroundOptions.length; f++) {
            foregroundMenu.add(foregroundOptions[f]);
            foregroundOptions[f].addActionListener(fcl);
        }

        BackgroundColorListener bcl = new BackgroundColorListener();
        backgroundMenu = new JMenu("Background");
        for (int b = 0; b < backgroundOptions.length; b++) {
            backgroundMenu.add(backgroundOptions[b]);
            backgroundOptions[b].addActionListener(bcl);
        }
        colorClear = new JMenuItem("Clear");
        colorClear.addActionListener(fcl);

        colorMenu.add(foregroundMenu);
        colorMenu.addSeparator();
        colorMenu.add(backgroundMenu);
        colorMenu.addSeparator();
        colorMenu.add(colorClear);

        // Creating the Font Menu
        FontMenuListener fml = new FontMenuListener();
        fontMenu = new JMenu("Font");
        styleMenu = new JMenu("Style");
        stylePlain = new JMenuItem("Plain");
        styleBold = new JMenuItem("Bold");
        styleItalics = new JMenuItem("Italics");
        sizeMenu = new JMenu("Size");
        sizeTen = new JMenuItem("SIZE 10");
        sizeTwenty = new JMenuItem("SIZE 20");
        sizeSeventyTwo = new JMenuItem("SIZE 72");

        stylePlain.addActionListener(fml);
        styleBold.addActionListener(fml);
        styleItalics.addActionListener(fml);
        sizeTen.addActionListener(fml);
        sizeTwenty.addActionListener(fml);
        sizeSeventyTwo.addActionListener(fml);

        fontMenu.add(styleMenu);
        styleMenu.add(stylePlain);
        styleMenu.add(styleBold);
        styleMenu.add(styleItalics);
        fontMenu.add(sizeMenu);
        sizeMenu.add(sizeTen);
        sizeMenu.add(sizeTwenty);
        sizeMenu.add(sizeSeventyTwo);
        fontMenu.setToolTipText("If you change the font size, style, or text, you may have to resize the window to fix alignment.");

        // Creating Menu Bar and Adding Components
        bar = new JMenuBar();
        bar.add(figureMenu);
        bar.add(colorMenu);
        bar.add(fontMenu);
        this.setJMenuBar(bar);

        // Creating and Adding Paint Panel
        drawingPan = new PaintPanel();
        drawingPan.setBackground(Color.WHITE);
        this.add(drawingPan, BorderLayout.CENTER);

        // Creating Text Panel
        textPanel = new JPanel();
        tf = new JTextField("Title Your Masterpiece Here");
        font = new Font(Font.SERIF, fstyle, fsize);
        tf.setFont(font);

        // Allowing for TextField to change size w/ text size
        affineTransform = new AffineTransform();
        frc = new FontRenderContext(affineTransform, true, true);
        textWidth = (int)(font.getStringBounds(tf.getText(), frc).getWidth());
        textHeight = (int)(font.getStringBounds(tf.getText(), frc).getHeight());
        tf.setSize(textWidth + 1, textHeight + 1);

        // Adding TextPanel and TextField to PaintFrame
        tf.setToolTipText("If you change the font size, style, or text, you may have to resize the window to fix alignment.");
        textPanel.add(tf);
        this.add(textPanel, BorderLayout.SOUTH);
    }

    private class FigureMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String caption = e.getActionCommand();
            switch (caption)
            {
                case "Straight Line":
                    drawingPan.setShape(shapeOptions[0]);
                    break;
                case "Rectangle":
                    drawingPan.setShape(shapeOptions[1]);
                    break;
                case "Oval":
                    drawingPan.setShape(shapeOptions[2]);
                    break;
                case "Round Rectangle":
                    drawingPan.setShape(shapeOptions[3]);
                    break;
                case "Filled Round Rectangle":
                    drawingPan.setShape(shapeOptions[4]);
                    break;
                case "Filled Rectangle":
                    drawingPan.setShape(shapeOptions[5]);
                    break;
                case "Filled Oval":
                    drawingPan.setShape(shapeOptions[6]);
                    break;
                case "Brush":
                    drawingPan.setShape(shapeOptions[7]);
                    break;
                default:
                    break;
            }
        }
    }

    private class ForegroundColorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String caption = e.getActionCommand();
            switch(caption)
            {
                case "Black":
                    drawingPan.setShapeColor(Color.BLACK);
                    break;
                case "White":
                    drawingPan.setShapeColor(Color.WHITE);
                    break;
                case "Blue":
                    drawingPan.setShapeColor(Color.BLUE);
                    break;
                case "Cyan":
                    drawingPan.setShapeColor(Color.CYAN);
                    break;
                case "Green":
                    drawingPan.setShapeColor(Color.GREEN);
                    break;
                case "Magenta":
                    drawingPan.setShapeColor(Color.MAGENTA);
                    break;
                case "Red":
                    drawingPan.setShapeColor(Color.RED);
                    break;
                case "Clear":
                    drawingPan.clear();
                default:
                    drawingPan.setForeground(null);
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
                    drawingPan.setBackground(Color.BLACK);
                    break;
                case "White":
                    drawingPan.setBackground(Color.WHITE);
                    break;
                case "Blue":
                    drawingPan.setBackground(Color.BLUE);
                    break;
                case "Cyan":
                    drawingPan.setBackground(Color.CYAN);
                    break;
                case "Green":
                    drawingPan.setBackground(Color.GREEN);
                    break;
                case "Magenta":
                    drawingPan.setBackground(Color.MAGENTA);
                    break;
                case "Red":
                    drawingPan.setBackground(Color.RED);
                    break;
                default:
                    drawingPan.setBackground(null);
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
                    fstyle = Font.PLAIN;
                    font = new Font(Font.SERIF, fstyle, fsize);
                    tf.setFont(font);
                    break;
                case "Bold":
                    fstyle = Font.BOLD;
                    font = new Font(Font.SERIF, fstyle, fsize);
                    tf.setFont(font);
                    break;
                case "Italics":
                    fstyle = Font.ITALIC;
                    font = new Font(Font.SERIF, fstyle, fsize);
                    tf.setFont(font);
                    break;
                case "SIZE 10":
                    fsize = 10;
                    font = new Font(Font.SERIF, fstyle, fsize);
                    tf.setFont(font);
                    break;
                case "SIZE 20":
                    fsize = 20;
                    font = new Font(Font.SERIF, fstyle, fsize);
                    tf.setFont(font);
                    break;
                case "SIZE 72":
                    fsize = 72;
                    font = new Font(Font.SERIF, fstyle, fsize);
                    tf.setFont(font);
                    break;
                default:
                    tf.setFont(new Font(Font.SERIF, Font.PLAIN, 10));
                    break;
            }
        }
    }
}
