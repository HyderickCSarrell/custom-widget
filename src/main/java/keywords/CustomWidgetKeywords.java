package keywords;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.swing.context.Context;

import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.ContainerOperator;
import org.netbeans.jemmy.ComponentChooser;
import java.awt.Component;
import java.awt.Shape;
import java.awt.Rectangle;
import edu.jsu.mcis.CustomWidget;

@RobotKeywords
public class CustomWidgetKeywords {
    
    @RobotKeyword("Clicks inside the Hexagon Shape.\n")
    @ArgumentNames({})
    public void clickHexagon() {
        ContainerOperator context = (ContainerOperator) Context.getContext();
        ComponentChooser chooser = new CustomWidgetChooser();
        JComponentOperator operator = new JComponentOperator(context, chooser);
        CustomWidget w = (CustomWidget)operator.getSource();
        Rectangle bounds = w.getShapes()[0].getBounds();
        operator.clickMouse(bounds.x + bounds.width/2, bounds.y + bounds.height/2, 1);
    }
    
	@RobotKeyword("Clicks inside the Octagon Shape.\n")
    @ArgumentNames({})
    public void clickOctagon() {
        ContainerOperator context = (ContainerOperator) Context.getContext();
        ComponentChooser chooser = new CustomWidgetChooser();
        JComponentOperator operator = new JComponentOperator(context, chooser);
        CustomWidget w = (CustomWidget)operator.getSource();
        Rectangle bounds = w.getShapes()[1].getBounds();
        operator.clickMouse(bounds.x + bounds.width/2, bounds.y + bounds.height/2, 1);
    }
	
    @RobotKeyword("Clicks outside the Shapes.\n")
    @ArgumentNames({})
    public void clickOutside() {
        ContainerOperator context = (ContainerOperator) Context.getContext();
        ComponentChooser chooser = new CustomWidgetChooser();
        JComponentOperator operator = new JComponentOperator(context, chooser);
        CustomWidget w = (CustomWidget)operator.getSource();
        Rectangle hexBounds = w.getShapes()[0].getBounds();
		Rectangle octBounds = w.getShapes()[0].getBounds();
		int xBound = octBounds.x - hexBounds.x;
		int yBound = octBounds.y - hexBounds.y;
        operator.clickMouse(xBound + 10, yBound + 10, 1);
    }
        
    class CustomWidgetChooser implements ComponentChooser {
        public CustomWidgetChooser() {}
        public boolean checkComponent(Component comp) {
            return (comp instanceof CustomWidget);
        }
        public String getDescription() {
            return "Any CustomWidget";
        }
    }
}