import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 * Dot Connector
 *
 * @author Rohith Varma Gaddam
 * @since April 26, 2020
 */
public class LinkageDot extends Linkage {
	final Dimension DOT_DIMENSION = new Dimension(10, 10);
	static boolean isAlreadyOneClick = false;

	LinkageDot(DrawShape shape, String type) {
		super(shape, type);
		setMaximumSize(DOT_DIMENSION);
	}

	public void drawLine() {
		if (StoreClickPoints.link1 == null && StoreClickPoints.link2 == null && type.equals("O") && !connected) {
			StoreClickPoints.link1 = this;
			this.setBackground(Color.BLACK);
		} else if (StoreClickPoints.link1 != null && StoreClickPoints.link2 == null && type.equals("I") && !connected) {
			if (!shape.equals(StoreClickPoints.link1.shape)) {
				StoreClickPoints.link2 = this;
				this.setBackground(Color.BLACK);
				StoreClickPoints.StorePoints(this);
			}
		} else if (StoreClickPoints.link2 == null && StoreClickPoints.link1 == null && type.equals("I") && !connected) {
			StoreClickPoints.link2 = this;
			this.setBackground(Color.BLACK);
		} else if (StoreClickPoints.link2 != null && StoreClickPoints.link1 == null && type.equals("O") && !connected) {
			if (!shape.equals(StoreClickPoints.link2.shape)) {
				StoreClickPoints.link1 = this;
				this.setBackground(Color.BLACK);
				StoreClickPoints.StorePoints(this);
			}
		}
	}

	public void deleteOrSwitchLine() {
		String[] buttons = { "delete line", "switch line", "Cancel" };

		int rc = JOptionPane.showOptionDialog(null, "select ?", "Confirmation", JOptionPane.WARNING_MESSAGE, 0, null,
				buttons, buttons[2]);
		if (rc != 2 && rc != -1) {
			for (int i = 0; i < shape.canvas.lineArray.size(); i++) {
				if (shape.canvas.lineArray.get(i)[0].equals(this) || shape.canvas.lineArray.get(i)[1].equals(this)) {
					if (rc == 1 && type.equals("O")) {
						shape.canvas.lineArray.get(i)[1].setBackground(Color.WHITE);
						StoreClickPoints.link1 = this;
						StoreClickPoints.link2 = null;
						shape.canvas.lineArray.get(i)[1].connected = false;
					} else if (rc == 1 && type.equals("I")) {
						shape.canvas.lineArray.get(i)[0].setBackground(Color.WHITE);
						StoreClickPoints.link1 = null;
						StoreClickPoints.link2 = this;
						shape.canvas.lineArray.get(i)[0].connected = false;
					} else if (rc == 0) {
						shape.canvas.lineArray.get(i)[0].setBackground(Color.WHITE);
						shape.canvas.lineArray.get(i)[1].setBackground(Color.WHITE);
						shape.canvas.lineArray.get(i)[0].connected = false;
						shape.canvas.lineArray.get(i)[1].connected = false;
					}
					shape.canvas.lineArray.remove(i);
					shape.canvas.repaint();
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (SwingUtilities.isRightMouseButton(arg0) && connected) {
			deleteOrSwitchLine();
		} else {
			drawLine();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
