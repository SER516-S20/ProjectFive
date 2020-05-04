package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * This class holds "Less Than '<' " icon information and draw them
 * 
 * @author Mayank Kataruka
 * @version 1.0
 */

public class LessThan extends Icons {
	private static final long serialVersionUID = 1L;
	private Point point;
	private Shape icon;
	private Point inputPoint, outputPoint1, outputPoint2;

	public LessThan(Point point) {
		dots = new ArrayList<Dot>();
		this.point = point;
		inputPoint = new Point((int) point.getX() + 10, (int) point.getY() + 20);
		outputPoint1 = new Point((int) point.getX() + 80, (int) point.getY() + 10);
		outputPoint2 = new Point((int) point.getX() + 80, (int) point.getY() + 30);
		dots.add(new Dot(inputPoint, true, this));
		dots.add(new Dot(outputPoint1, false, this));
		dots.add(new Dot(outputPoint2, false, this));
	}

	@Override
	public void drawShape(Graphics graphic) {
		Graphics2D graphics2 = (Graphics2D) graphic;
		inputPoint.setLocation(point.getX() + 10, point.getY() + 20);
		outputPoint1.setLocation(point.getX() + 80, point.getY() + 10);
		outputPoint2.setLocation(point.getX() + 80, point.getY() + 30);
		for (Dot dot : dots) {
			dot.drawShape();
		}
		graphics2.setFont(new Font("Monospaced", Font.BOLD, 32));
		graphics2.drawString("<", (int) point.getX() + 40, (int) point.getY() + 35);
		icon = new Rectangle2D.Double(this.point.getX(), this.point.getY(), 100, 50);
		graphics2.draw(icon);
	}

	@Override
	public boolean containsIcon(Point point) {
		return icon.contains(point);
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return point;
	}

	@Override
	public void setLocation(Point point) {
		// TODO Auto-generated method stub
		this.point = point;
	}

}
