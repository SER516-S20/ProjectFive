import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * IfBlockEndIcon.java - a class for creating IfBlockEndIcon > at a given
 * position
 * 
 * @author Ashwin Srinivasan
 * @version 1.0
 * 
 */
public class IfBlockEndIcon implements Icon {
	JButton closeAngleBracketButton;
	Helper helper = new Helper();

	IfBlockEndIcon(JButton closeAngleBracketButton) {
		this.closeAngleBracketButton = closeAngleBracketButton;
	}

	public int getIconWidth() {
		return 0;
	}

	public int getIconHeight() {
		return 0;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		IfBlockEnd lessThanBracket = new IfBlockEnd();
		lessThanBracket.setPosition(x - Constants.X_DEVIATION, y - Constants.Y_DEVIATION);
		lessThanBracket.draw(g);
	}
}
