import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * BarShapeIcon.java - a class for loading Bar shape Icon inside Bar shape
 * Button on the left panel
 * 
 * @author Ashutosh Dey
 * @version 1.0
 * 
 */
public class BarShapeIcon implements Icon {
	JButton barShapeButton;
	Helper helper = new Helper();

	BarShapeIcon(JButton barShapeButton) {
		this.barShapeButton = barShapeButton;
	}

	public int getIconWidth() {
		return 0;
	}

	public int getIconHeight() {
		return 0;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		BarShape barShape = new BarShape();
		barShape.setPosition(x - 65, y - 30);
		barShape.draw(g);
	}
}
