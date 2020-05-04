package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Connections;
import model.Tab;
import model.TabList;

/**
 * This class captures mouse events on workspace. This class draws icons and
 * lines.
 * 
 * @author Parikshith Kedilaya Mallar
 * @version 4.0
 *
 */
public class Workspace extends JPanel implements MouseListener, MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;

	public Workspace() {
		this.setBackground(Color.WHITE);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY());
		TabList.getInstance().getTab().setPoint(point, "Dragged");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (TabList.getInstance().getTab().isFirstDotClicked()) {
			Point point = new Point(e.getX(), e.getY());
			TabList.getInstance().getTab().setDestPoint(point, "DrawTempLine");
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY());
		Tab tab = TabList.getInstance().getTab();
		if (e.getClickCount() == 2) {
			tab.setPoint(point, "DoubleClicked");
		} else if (SwingUtilities.isRightMouseButton(e)) {
			tab.setPoint(point, "RightClick");
		} else {
			tab.setPoint(point, "Clicked");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point point = new Point(e.getX(), e.getY());
		TabList.getInstance().getTab().setPoint(point, "Pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		TabList.getInstance().getTab().setSelectedIcon(null);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Tab tab = TabList.getInstance().getTab();
		ArrayList<Icons> iconList = tab.getIconList();
		ListIterator<Icons> i = iconList.listIterator();
		// Draws all the icons from the list
		while (i.hasNext()) {
			Icons nextIcon = i.next();
			nextIcon.drawShape(graphics);
		}
		// Draws all the lines from the list
		for (Connections connection : tab.getConnectionList()) {
			drawArrowLine(graphics, (int) connection.getOriginPoint().getX() + 5,
					(int) connection.getOriginPoint().getY() + 5, (int) connection.getDestPoint().getX() + 5,
					(int) connection.getDestPoint().getY() + 5, 10, 5);
		}
		// Draws a line when first dot is clicked and mouse is moved on the workspace
		if (tab.isMoving()) {
			drawArrowLine(graphics, (int) tab.getOriginPoint().getX() + 5, (int) tab.getOriginPoint().getY() + 5,
					(int) tab.getDestPoint().getX(), (int) tab.getDestPoint().getY(), 10, 5);
		}
	}

	/**
	 * Draw an arrow line between two points.
	 * 
	 * @param g  the graphics component.
	 * @param x1 x-position of first point.
	 * @param y1 y-position of first point.
	 * @param x2 x-position of second point.
	 * @param y2 y-position of second point.
	 * @param d  the width of the arrow.
	 * @param h  the height of the arrow.
	 * 
	 *           https://stackoverflow.com/questions/2027613/how-to-draw-a-directed-arrow-line-in-java/27461352#27461352
	 */
	private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = D - d, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;

		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;

		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;

		int[] xpoints = { x2, (int) xm, (int) xn };
		int[] ypoints = { y2, (int) ym, (int) yn };

		g.drawLine(x1, y1, x2, y2);
		g.fillPolygon(xpoints, ypoints, 3);

	}

	public void setCrossHairCursor() {
		setCursorMethod(Cursor.CROSSHAIR_CURSOR);
	}

	public void setDefaultCursor() {
		setCursorMethod(Cursor.DEFAULT_CURSOR);
	}

	public void setMovingCursor() {
		setCursorMethod(Cursor.MOVE_CURSOR);
	}

	private void setCursorMethod(int cursorType) {
		Cursor cursor = new Cursor(cursorType);
		this.setCursor(cursor);
	}

	public String getInputString(String setMessage) {
		return (String) JOptionPane.showInputDialog("Enter description", setMessage);
	}

	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public boolean prompt(String message) {
		int showConfirmDialog = JOptionPane.showConfirmDialog(this, message);
		if (showConfirmDialog == 0) {
			return true;
		}
		return false;
	}
}