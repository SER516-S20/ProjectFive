import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 * @author Sarvansh
 * @created on 03-18-2020
 * @version 1.0
 * @author abhinaw sarang
 * @created 03-20-2020
 * @version 2.0
 */
public class ShapeGreaterThan extends Icon{

	private static final long serialVersionUID = 1L;
	private static String symbol = ">";
	private Dot rightDot;
	private Dot leftUpperDot, leftLowerDot;

	public ShapeGreaterThan(int x, int y) {
		this.setCenterX(x);
		this.setCenterY(y);
		setUpPoints();
		repaint();
	}

	@Override
	public void paintComponent(Graphics objGraphics) {
		try {
			Graphics2D obj2D = (Graphics2D) objGraphics;
			Shape objRectangle = new Rectangle(this.getCenterX(), this.getCenterY(), getWidth(), getHeight());
			obj2D.draw(objRectangle);
			obj2D.drawString(symbol, getMiddlePointX(), getMiddlePointY());		
			obj2D.fillOval(rightDot.getX(), rightDot.getY(), getDotSize(), getDotSize());
			obj2D.fillOval(leftUpperDot.getX(), leftUpperDot.getY(), getDotSize(), getDotSize());
			obj2D.fillOval(leftLowerDot.getX(), leftLowerDot.getY(), getDotSize(), getDotSize());		
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void setUpPoints() {
		int x = getCenterX() + getWidth() - getDotMargin() - (getDotSize()/2);
		int y = getCenterY() + (getHeight()/2) - (getDotSize()/2);
		Dot right = new Dot(x, y, false, true);
		setRightDot(right);
		int x1 = getCenterX() + getDotMargin() - (getDotSize()/2);
		int y1 = getCenterY() + getDotMargin() - (getDotSize()/2);
		int y2 = getCenterY() + getHeight() - getDotMargin() - (getDotSize()/2);
		Dot lefttUpDot = new Dot(x1, y1, true, false);
		setLeftUpperDot(lefttUpDot);
		Dot lefttLoUDot = new Dot(x1, y2, true, false);
		setLeftLowerDot(lefttLoUDot);
	}

	public Dot getRightDot() {
		return rightDot;
	}

	public void setRightDot(Dot rightDot) {
		this.rightDot = rightDot;
	}

	public Dot getLeftUpperDot() {
		return leftUpperDot;
	}

	public void setLeftUpperDot(Dot leftUpperDot) {
		this.leftUpperDot = leftUpperDot;
	}

	public Dot getLeftLowerDot() {
		return leftLowerDot;
	}

	public void setLeftLowerDot(Dot leftLowerDot) {
		this.leftLowerDot = leftLowerDot;
	}

	public static String getSymbol() {
		return symbol;
	}

	public static void setSymbol(String text) {
		ShapeGreaterThan.symbol = text;
	}
}
