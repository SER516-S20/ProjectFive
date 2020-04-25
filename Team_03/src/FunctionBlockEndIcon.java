import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * FunctionBlockEndIcon.java - a class for loading ) shape Icon inside FunctionBlockEnd
 * Button on the left panel
 * 
 * @author Srinivasan Sundar
 * @version 1.0
 * 
 */
public class FunctionBlockEndIcon implements Icon {
	JButton OpenAngleBracketButton;
	Helper helper = new Helper();

	FunctionBlockEndIcon(JButton OpenAngleBracketButton) {
		this.OpenAngleBracketButton = OpenAngleBracketButton;
	}

	public int getIconWidth() {
		return 0;
	}

	public int getIconHeight() {
		return 0;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		FunctionBlockEnd openAngleBracket = new FunctionBlockEnd();
		openAngleBracket.setPosition(x - 65, y - 30);
		openAngleBracket.draw(g);
	}

}
