package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import model.TabList;

/**
 * This class creates a Dot button and adds an action listener to it .
 * 
 * @author Chandan
 * @version 4.0
 */
public class Dot extends JButton implements Serializable, MouseListener {

	private Point point;
	private boolean isInput;

	public Dot(Point point, boolean isInput, Icons icon) {
		this.point = point;
		this.isInput = isInput;
		drawShape();
		setContentAreaFilled(false);
		TabList.getInstance().getTab().getWorkspace().add(this);
		addActionListener(icon);
	}

	public boolean isInput() {
		return isInput;
	}

	public void setInput(boolean isInput) {
		this.isInput = isInput;
	}

	public void drawShape() {
		this.setBounds((int) point.getX(), (int) point.getY(), 10, 10);
	}

	public Point getLocation() {
		return point;
	}

	public void setLocation(Point point) {
		this.point = point;
	}

	public void addActionListener(Icons icon) {
		this.addActionListener(new DotBarActionListener(icon, point, isInput));
		this.addMouseListener(this);
	}

	private static final long serialVersionUID = 1L;
	Shape shape;

	/**
	 * Draws the dot JButton .
	 * 
	 * @param graphic
	 */
	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			g.setColor(getBackground());
		} else {
			g.setColor(Color.BLACK);
		}

		g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

		super.paintComponent(g);
	}

	@Override
	protected void paintBorder(Graphics g) {
		g.setColor(getForeground());
		g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
	}

	@Override
	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}

		return shape.contains(x, y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			TabList.getInstance().getTab().setPoint(
					SwingUtilities.convertPoint(this, e.getPoint(), TabList.getInstance().getTab().getWorkspace()),
					"DotClicked");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
