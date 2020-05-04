package icon;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JButton;
import frame.Constants;
import shape.FunctionBlockEnd;

/**
 * FunctionBlockEndIcon.java - a class for loading ) shape Icon inside
 * FunctionBlockEnd Button on the left panel
 * 
 * @author Srinivasan Sundar
 * @version 1.0
 * 
 */
public class FunctionBlockEndIcon implements Icon {
	JButton functionBlockEndButton;

	public FunctionBlockEndIcon(JButton functionBlockEndButton) {
		this.functionBlockEndButton = functionBlockEndButton;
	}

	public int getIconWidth() {
		return 0;
	}

	public int getIconHeight() {
		return 0;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		FunctionBlockEnd openAngleBracket = new FunctionBlockEnd();
		openAngleBracket.setPosition(x - Constants.ICON_DEVIATION_X, y - Constants.ICON_DEVIATION_Y);
		openAngleBracket.draw(g);
	}

}
