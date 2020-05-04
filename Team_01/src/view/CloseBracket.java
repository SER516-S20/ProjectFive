package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * This class has CloseBracket icon which contains one input dot.
 * 
 * @author Chandan
 * @version 1.0
 */
public class CloseBracket extends Icons {
	private static final long serialVersionUID = 1L;
	private Point point;
	private Shape icon;
	private Point inputPoint;

	public CloseBracket(Point point) {
		dots = new ArrayList<Dot>();
		this.point = point;
		inputPoint = new Point((int) point.getX() + 10, (int) point.getY() + 20);
		dots.add(new Dot(inputPoint, true, this));
	}

	/**
	 * Draws the icon with one input dot.
	 * 
	 * @param graphic
	 */
	@Override
	public void drawShape(Graphics graphic) {
		Graphics2D graphics2 = (Graphics2D) graphic;
		inputPoint.setLocation(point.getX() + 10, point.getY() + 20);
		for (Dot dot : dots) {
			dot.drawShape();
		}
		graphics2.setFont(new Font("Monospaced", Font.BOLD, 32));
		graphics2.drawString(")", (int) point.getX() + 45, (int) point.getY() + 35);
		icon = new Rectangle2D.Double(this.point.getX(), this.point.getY(), 100, 50);
		graphics2.draw(icon);
	}

	@Override
	public boolean containsIcon(Point point) {
		return icon.contains(point);
	}

	@Override
	public Point getLocation() {
		return point;
	}

	@Override
	public void setLocation(Point point) {
		this.point = point;
	}
}
