import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JButton;


/**
 * FunctionBlockBeginIcon.java - a class for loading ( shape Icon inside FunctionBlockBegin
 * Button on the left panel
 * 
 * @author Srinivasan Sundar
 * @version 1.0
 * 
 */
public class FunctionBlockBeginIcon implements Icon {
	JButton OpenAngleBracketButton;
	Helper helper = new Helper();

	FunctionBlockBeginIcon(JButton OpenAngleBracketButton) {
		this.OpenAngleBracketButton = OpenAngleBracketButton;
	}

	public int getIconWidth() {
		return 0;
	}

	public int getIconHeight() {
		return 0;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		FunctionBlockBegin openAngleBracket = new FunctionBlockBegin();
		openAngleBracket.setPosition(x - 65, y - 30);
		openAngleBracket.draw(g);
	}

}
