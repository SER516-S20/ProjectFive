import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * ForLoopIcon.java - a class for loading @ shape Icon inside For Loop
 * Button on the left panel
 * 
 * @author Ashwin Srinivasan
 * @version 1.0
 * 
 */
public class ForLoopIcon implements Icon {
	JButton OpenAngleBracketButton;
	Helper helper = new Helper();

	ForLoopIcon(JButton OpenAngleBracketButton) {
		this.OpenAngleBracketButton = OpenAngleBracketButton;
	}

	public int getIconWidth() {
		return 0;
	}

	public int getIconHeight() {
		return 0;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		ForLoop openAngleBracket = new ForLoop();
		openAngleBracket.setPosition(x - Constants.X_DEVIATION, y - Constants.Y_DEVIATION);
		openAngleBracket.draw(g);
	}

}
