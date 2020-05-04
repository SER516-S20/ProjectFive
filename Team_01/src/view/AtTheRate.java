package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * This class has AtTheRate icon which contains two input dots and two output
 * dots.
 * 
 * @author Chandan
 * @version 1.0
 */
public class AtTheRate extends Icons {
	private Point point, inputPoint1, inputPoint2, outputPoint1, outputPoint2;
	private Shape icon;

	public AtTheRate(Point point) {
		dots = new ArrayList<Dot>();
		this.point = point;
		inputPoint1 = new Point((int) point.getX() + 10, (int) point.getY() + 10);
		inputPoint2 = new Point((int) point.getX() + 10, (int) point.getY() + 30);
		outputPoint1 = new Point((int) point.getX() + 80, (int) point.getY() + 10);
		outputPoint2 = new Point((int) point.getX() + 80, (int) point.getY() + 30);
		dots.add(new Dot(inputPoint1, true, this));
		dots.add(new Dot(outputPoint1, false, this));
		dots.add(new Dot(inputPoint2, true, this));
		dots.add(new Dot(outputPoint2, false, this));
	}

	/**
	 * Draws the icon with two input dots and two output dots.
	 * 
	 * @param graphic
	 */
	@Override
	public void drawShape(Graphics graphic) {
		Graphics2D graphics2 = (Graphics2D) graphic;
		inputPoint1.setLocation(point.getX() + 10, point.getY() + 10);
		inputPoint2.setLocation(point.getX() + 10, point.getY() + 30);
		outputPoint1.setLocation(point.getX() + 80, point.getY() + 10);
		outputPoint2.setLocation(point.getX() + 80, point.getY() + 30);
		for (Dot dot : dots) {
			dot.drawShape();
		}
		graphics2.setFont(new Font("Monospaced", Font.BOLD, 32));
		graphics2.drawString("@", (int) point.getX() + 40, (int) point.getY() + 35);
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
