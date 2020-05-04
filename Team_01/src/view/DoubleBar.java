package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * This class has double bar icon which contains two vertical bars.
 * 
 * @author Raghavan
 * @version 1.0
 */
public class DoubleBar extends Icons {
	private static final long serialVersionUID = 1L;
	private Shape icon;
	private Point point, inputPoint, outputPoint;

	public DoubleBar(Point point) {
		bars = new ArrayList<VerticalBar>();
		this.point = point;
		inputPoint = new Point((int) point.getX() + 10, (int) point.getY() + 10);
		outputPoint = new Point((int) point.getX() + 80, (int) point.getY() + 10);
		bars.add(new VerticalBar(inputPoint, true, this));
		bars.add(new VerticalBar(outputPoint, false, this));

	}

	/**
	 * Draws the icon with two vertical bars inside it.
	 * 
	 * @param graphic
	 */
	@Override
	public void drawShape(Graphics graphic) {
		Graphics2D graphics2 = (Graphics2D) graphic;
		inputPoint.setLocation(point.getX() + 10, point.getY() + 10);
		outputPoint.setLocation(point.getX() + 80, point.getY() + 10);
		for (VerticalBar bar : bars) {
			bar.drawShape();
		}
		graphics2.setFont(new Font("Monospaced", Font.BOLD, 32));
		graphics2.drawString("||", (int) point.getX() + 31, (int) point.getY() + 32);
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
