package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CustomWidget extends JPanel implements MouseListener {
    private java.util.List<ShapeObserver> observers;
    
    
    private final Color HEXAGON_SELECTED_COLOR = Color.green;
	private final Color OCTAGON_SELECTED_COLOR = Color.red;
    private final Color DEFAULT_COLOR = Color.white;
    private String selected;
    private Point[] hexagonVertex;
	private Point[] octagonVertex;

    
    public CustomWidget() {
        observers = new ArrayList<>();
        
        selected = "Hexagon";
        hexagonVertex = new Point[6];
		octagonVertex = new Point[8];
        for(int i = 0; i < hexagonVertex.length; i++) { hexagonVertex[i] = new Point(); }
		for(int i = 0; i < octagonVertex.length; i++) { octagonVertex[i] = new Point(); }
        Dimension dim = getPreferredSize();
        calculateVertices(dim.width, dim.height);
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(this);
    }

    
    public void addShapeObserver(ShapeObserver observer) {
        if(!observers.contains(observer)) observers.add(observer);
    }
    public void removeShapeObserver(ShapeObserver observer) {
        observers.remove(observer);
    }
    private void notifyObservers() {
        ShapeEvent event = new ShapeEvent(selected);
        for(ShapeObserver obs : observers) {
            obs.shapeChanged(event);
        }
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    private void calculateVertices(int width, int height) {
        // Square size should be half of the smallest dimension (width or height).
		/*
		Point[] hexSign = new Point[6];
		for(int i = 0; i < hexSign.length; i++) { hexSign[i] = new Point(); }
		double theta = (2*Math.PI)/hexSign.length;
		for(int i = 0; i < hexSign.length; i++) {
			hexSign[i].setLocation(Math.cos(theta*i), Math.sin(theta*i));
		}
		*/
        int side = Math.min(width, height) / 4;
        Point[] hexSign = {new Point(-1, -1), new Point(1, -1),new Point(2,0), new Point(1, 1), new Point(-1, 1), new Point(-2,0)};
        for(int i = 0; i < hexagonVertex.length; i++) {
            hexagonVertex[i].setLocation(width/3 + hexSign[i].x * side/3, 
                                  height/2 + hexSign[i].y * side/2);
        }
		
		Point[] octSign = {new Point(-1, -2), new Point(1, -2),new Point(2,-1), new Point(2, 1), new Point(1, 2), new Point(-1,2), new Point(-2,1), new Point(-2,-1)};
        for(int i = 0; i < octagonVertex.length; i++) {
            octagonVertex[i].setLocation((width - width/3) + octSign[i].x * side/3,
                                  height/2 + octSign[i].y * side/2);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        calculateVertices(getWidth(), getHeight());
        Shape[] shapes = getShapes();
        g2d.setColor(Color.black);
        g2d.draw(shapes[0]);
		g2d.draw(shapes[1]);
        if(selected == "Hexagon") {
            g2d.setColor(HEXAGON_SELECTED_COLOR);
            g2d.fill(shapes[0]);
			g2d.setColor(DEFAULT_COLOR);
			g2d.fill(shapes[1]);
        }
		
		else if(selected == "Octagon") {
            g2d.setColor(OCTAGON_SELECTED_COLOR);
			g2d.fill(shapes[1]);
			g2d.setColor(DEFAULT_COLOR);
			g2d.fill(shapes[0]);
        }
        else {
            g2d.setColor(DEFAULT_COLOR);
            g2d.fill(shapes[0]);
			g2d.fill(shapes[1]);
        }
    }

    public void mouseClicked(MouseEvent event) {
        Shape[] shape = getShapes();
        if(shape[0].contains(event.getX(), event.getY())) {
            selected = "Hexagon";
            notifyObservers();
        }
		
		if(shape[1].contains(event.getX(), event.getY())) {
            selected = "Octagon";
            notifyObservers();
        }
        repaint(shape[0].getBounds());
		repaint(shape[1].getBounds());
    }
    public void mousePressed(MouseEvent event) {}
    public void mouseReleased(MouseEvent event) {}
    public void mouseEntered(MouseEvent event) {}
    public void mouseExited(MouseEvent event) {}
    
    public Shape[] getShapes() {
		Shape[] shapes = new Shape[2];
        int[] x = new int[hexagonVertex.length];
        int[] y = new int[hexagonVertex.length];
        for(int i = 0; i < hexagonVertex.length; i++) {
            x[i] = hexagonVertex[i].x;
            y[i] = hexagonVertex[i].y;
        }
		shapes[0] = new Polygon(x, y, hexagonVertex.length);
		
		x = new int[octagonVertex.length];
        y = new int[octagonVertex.length];
        for(int i = 0; i < octagonVertex.length; i++) {
            x[i] = octagonVertex[i].x;
            y[i] = octagonVertex[i].y;
        }
        shapes[1] = new Polygon(x, y, octagonVertex.length);
        return shapes;
    }
	
    public String returnSelectedShape() { return selected; }



	public static void main(String[] args) {
		JFrame window = new JFrame("Custom Widget");
        window.add(new CustomWidget());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(300, 300);
        window.setVisible(true);
	}
}
