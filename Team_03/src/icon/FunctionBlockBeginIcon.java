package icon;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JButton;
import frame.Constants;
import shape.FunctionBlockBegin;

/**
 * FunctionBlockBeginIcon.java - a class for loading ( shape Icon inside
 * FunctionBlockBegin Button on the left panel
 * 
 * @author Srinivasan Sundar
 * @version 1.0
 * 
 */
public class FunctionBlockBeginIcon implements Icon {
	JButton functionBlockBeginButton;

	public FunctionBlockBeginIcon(JButton functionBlockBeginButton) {
		this.functionBlockBeginButton = functionBlockBeginButton;
	}

	public int getIconWidth() {
		return 0;
	}

	public int getIconHeight() {
		return 0;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		FunctionBlockBegin openAngleBracket = new FunctionBlockBegin();
		openAngleBracket.setPosition(x - Constants.ICON_DEVIATION_X, y - Constants.ICON_DEVIATION_Y);
		openAngleBracket.draw(g);
	}

}
