package icon;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JButton;
import shape.ValueHolderBlock;

/**
 * ValueHolderBlockIcon.java - a class for creating ValueHolderBlock at a given
 * position
 * 
 * @author Ashutosh Dey
 * @version 1.0
 * 
 */

public class ValueHolderBlockIcon implements Icon {
	JButton closeAngleBracketButton;

	public ValueHolderBlockIcon(JButton closeAngleBracketButton) {
		this.closeAngleBracketButton = closeAngleBracketButton;
	}

	public int getIconWidth() {
		return 0;
	}

	public int getIconHeight() {
		return 0;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		ValueHolderBlock lessThanBracket = new ValueHolderBlock();
		lessThanBracket.setPosition(x - 65, y - 30);
		lessThanBracket.draw(g);
	}
}
