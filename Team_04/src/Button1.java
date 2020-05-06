import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * 
 * @author Rohith Varma Gaddam
 * @since 03-10-2020
 * @version 1.0
 *
 */
public class Button1 extends JButton {
	private SelectionEvent event;
	private static Icon label;

	public Button1() {
		super("(");
		setContentAreaFilled(false);
		event = new SelectionEvent(this, 1);
	}

	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			g.setColor(Color.lightGray);
		} else {
			g.setColor(getBackground());
		}
		g.fillRect(20, 10, getWidth()-40, getWidth()-40);
		super.paintComponent(g);
	}

	
}