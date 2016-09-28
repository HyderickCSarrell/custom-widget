package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

import java.awt.*;
import java.awt.event.*;

public class CustomWidgetTest {
    private CustomWidget widget;
    
    private Point getCenterOfShape(int indexOfShapes) {
		Shape[] shapes = widget.getShapes();
        Rectangle bounds = shapes[indexOfShapes].getBounds();
        return new Point(bounds.x + bounds.width/3, bounds.y + bounds.height/2);
    }
    
    @Before
    public void setUp() {
        widget = new CustomWidget();
    }
    
	@Test
	public void testWidgetIsInitiallyHexagon() {
		assertEquals("Hexagon",widget.returnSelectedShape());
	}
    
    @Test
    public void testClickingCenterOfHexagonSelectsIt() {
        Point center = getCenterOfShape(0);
        MouseEvent event = new MouseEvent(widget, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 
                                          0, center.x, center.y, 1, false);
        
        widget.mouseClicked(event);
        assertEquals("Hexagon",widget.returnSelectedShape());
    }
	
	@Test
    public void testClickingCenterOfOctagonSelectsIt() {
        Point center = getCenterOfShape(1);
        MouseEvent event = new MouseEvent(widget, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 
                                          0, center.x, center.y, 1, false);
        
        widget.mouseClicked(event);
        assertEquals("Octagon",widget.returnSelectedShape());
    }
}
