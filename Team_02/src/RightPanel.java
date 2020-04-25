import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * @author abhinaw sarang
 * @created 03-18-2020
 * @version 1.0
 * @author Suryadeep
 * @created 03-20-2020
 * @version 2.0
 */
public class RightPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private HashMap<String, List<Icon>> iconLocalMap;
	private ArrayList<Line> localStoreLine;
	private static final Polygon ARROW_HEAD = new Polygon();
	static {
		ARROW_HEAD.addPoint(0, 0);
		ARROW_HEAD.addPoint(-5, -10);
		ARROW_HEAD.addPoint(5, -10);
	}

	public RightPanel() {
		iconLocalMap = new HashMap<String, List<Icon>>();
		localStoreLine = new ArrayList<Line>();
		try {
			add(new RightPanelMouseListener(this));
			setVisible(true);
			Border blackline = BorderFactory.createLineBorder(Color.black);
			setBorder(blackline);
			setSize(1600, 800);
			repaint();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		RightPanelDataProcessor objProcessor = (RightPanelDataProcessor) arg;
		iconLocalMap = (HashMap<String, List<Icon>>) objProcessor.getIconMap().clone();
		localStoreLine = (ArrayList<Line>) ((ArrayList<Line>) (objProcessor.getLineList())).clone();
		repaint();
	}

	public void paint(Graphics objGraphics) {
		super.paintComponent(objGraphics);
		Graphics2D obj2D = (Graphics2D) objGraphics;
		for (String key : iconLocalMap.keySet()) {
			if (key.equalsIgnoreCase("openParanthesis")) {
				System.out.println("Inside open paran repaint");
				for (Icon each : iconLocalMap.get(key)) {
					ShapeOpenParan s1 = (ShapeOpenParan) each;
					try {
						Shape objRectangle = new Rectangle(s1.getMiddlePointX() - 100, s1.getMiddlePointY() - 30, 200,
								60);
						obj2D.draw(objRectangle);
						obj2D.drawString("(", s1.getMiddlePointX(), s1.getMiddlePointY());
						obj2D.fillOval(s1.getRightDot().getX(), s1.getRightDot().getY(), 6, 10);
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
			} else if (key.equalsIgnoreCase("closedParanthesis")) {
				for (Icon each : iconLocalMap.get(key)) {
					ShapeClosedParan s1 = (ShapeClosedParan) each;
					try {
						Shape objRectangle = new Rectangle(s1.getMiddlePointX() - 100, s1.getMiddlePointY() - 30, 200,
								60);
						obj2D.draw(objRectangle);
						obj2D.drawString(")", s1.getMiddlePointX(), s1.getMiddlePointY());
						obj2D.fillOval(s1.getLeftDot().getX(), s1.getLeftDot().getY(), 6, 10);
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
			} else if (key.equalsIgnoreCase("lessThanOperator")) {
				for (Icon each : iconLocalMap.get(key)) {
					ShapeLessThan s1 = (ShapeLessThan) each;
					try {
						Shape objRectangle = new Rectangle(s1.getMiddlePointX() - 100, s1.getMiddlePointY() - 30, 200,
								60);
						obj2D.draw(objRectangle);
						obj2D.drawString("<", s1.getMiddlePointX(), s1.getMiddlePointY());
						obj2D.fillOval(s1.getLeftDot().getX(), s1.getLeftDot().getY(), 6, 10);
						obj2D.fillOval(s1.getRightUpperDot().getX(), s1.getRightUpperDot().getY(), 6, 10);
						obj2D.fillOval(s1.getRightLowerDot().getX(), s1.getRightLowerDot().getY(), 6, 10);
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
			} else if (key.equalsIgnoreCase("greaterThanOperator")) {
				for (Icon each : iconLocalMap.get(key)) {
					ShapeGreaterThan s1 = (ShapeGreaterThan) each;
					try {
						Shape objRectangle = new Rectangle(s1.getMiddlePointX() - 100, s1.getMiddlePointY() - 30, 200,
								60);
						obj2D.draw(objRectangle);
						obj2D.drawString(">", s1.getMiddlePointX(), s1.getMiddlePointY());
						obj2D.fillOval(s1.getRightDot().getX(), s1.getRightDot().getY(), 6, 10);
						obj2D.fillOval(s1.getLeftUpperDot().getX(), s1.getLeftUpperDot().getY(), 6, 10);
						obj2D.fillOval(s1.getLeftLowerDot().getX(), s1.getLeftLowerDot().getY(), 6, 10);
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
			} else if (key.equalsIgnoreCase("atTheRateOperator")) {
				for (Icon each : iconLocalMap.get(key)) {
					ShapeAtTheRate s1 = (ShapeAtTheRate) each;
					try {
						Shape objRectangle = new Rectangle(s1.getMiddlePointX() - 100, s1.getMiddlePointY() - 30, 200,
								60);
						obj2D.draw(objRectangle);
						obj2D.drawString("@", s1.getMiddlePointX(), s1.getMiddlePointY());
						obj2D.fillOval(s1.getRightUpperDot().getX(), s1.getRightUpperDot().getY(), 6, 10);
						obj2D.fillOval(s1.getRightLowerDot().getX(), s1.getRightLowerDot().getY(), 6, 10);
						obj2D.fillOval(s1.getLeftUpperDot().getX(), s1.getLeftUpperDot().getY(), 6, 10);
						obj2D.fillOval(s1.getLeftLowerDot().getX(), s1.getLeftLowerDot().getY(), 6, 10);
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
			} else if (key.equalsIgnoreCase("barOperator")) {
				for (Icon each : iconLocalMap.get(key)) {
					ShapeBar s1 = (ShapeBar) each;
					try {
						Shape objRectangle = new Rectangle(s1.getMiddlePointX() - 100, s1.getMiddlePointY() - 30, 200,
								60);
						obj2D.draw(objRectangle);
						obj2D.drawString("||", s1.getMiddlePointX(), s1.getMiddlePointY());
						obj2D.drawLine(s1.getRightUpperDot().getX(), s1.getRightUpperDot().getY(),
								s1.getRightLowerDot().getX(), s1.getRightLowerDot().getY());
						obj2D.drawLine(s1.getLeftUpperDot().getX(), s1.getLeftUpperDot().getY(),
								s1.getLeftLowerDot().getX(), s1.getLeftLowerDot().getY());
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}

			} else if (key.equalsIgnoreCase("dashOperator")) {
				for (Icon each : iconLocalMap.get(key)) {
					ShapeDash s1 = (ShapeDash) each;
					try {
						Shape objRectangle = new Rectangle(s1.getMiddlePointX() - 100, s1.getMiddlePointY() - 30, 200,
								60);
						obj2D.draw(objRectangle);
						obj2D.drawString("-", s1.getMiddlePointX(), s1.getMiddlePointY());
						obj2D.fillOval(s1.getLeftDot().getX(), s1.getLeftDot().getY(), 6, 10);
						obj2D.fillOval(s1.getRightDot().getX(), s1.getRightDot().getY(), 6, 10);
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
			}
		}
		for (Line eachLine : localStoreLine) {
			try {
				LineArrow line1 = new LineArrow(eachLine.getStartDot().getX(), eachLine.getStartDot().getY(),
						eachLine.getEndDot().getX(), eachLine.getEndDot().getY(), Color.BLACK, 3);
				line1.draw(objGraphics);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public static class LineArrow {
		private final int x;
		private final int y;
		private final int endX;
		private final int endY;
		private final Color color;
		private final int thickness;

		public LineArrow(int x, int y, int x2, int y2, Color color, int thickness) {
			super();
			this.x = x;
			this.y = y;
			this.endX = x2;
			this.endY = y2;
			this.color = color;
			this.thickness = thickness;
		}

		public void draw(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			double angle = Math.atan2(endY - y, endX - x);
			g2.drawLine(x, y, (int) (endX - 10 * Math.cos(angle)), (int) (endY - 10 * Math.sin(angle)));
			AffineTransform tx1 = g2.getTransform();
			AffineTransform tx2 = (AffineTransform) tx1.clone();
			tx2.translate(endX, endY);
			tx2.rotate(angle - Math.PI / 2);
			g2.setTransform(tx2);
			g2.fill(ARROW_HEAD);
			g2.setTransform(tx1);
		}
	}
}
