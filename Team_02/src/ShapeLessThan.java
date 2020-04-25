import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 * @author Rohit
 * @created 03-18-2020
 * @version 1.0
 * @author abhinaw sarang
 * @created 03-20-2020
 * @version 2.0
 */
public class ShapeLessThan extends Icon{

	private static final long serialVersionUID = 1L;
	private static String symbol = "<";
	private Dot leftDot;
	private Dot rightUpperDot, rightLowerDot;

	public ShapeLessThan(int x, int y) {
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
			obj2D.fillOval(leftDot.getX(), leftDot.getY(), getDotSize(), getDotSize());
			obj2D.fillOval(rightUpperDot.getX(), rightUpperDot.getY(), getDotSize(), getDotSize());
			obj2D.fillOval(rightLowerDot.getX(), rightLowerDot.getY(), getDotSize(), getDotSize());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void setUpPoints() {
		int x = getCenterX() + getDotMargin() - (getDotSize()/2);
		int y = getCenterY() + (getHeight()/2) - (getDotSize()/2);
		Dot left = new Dot(x, y, true, false);
		setLeftDot(left);
		int x1 = getCenterX() + getWidth() - getDotMargin() - (getDotSize()/2);
		int y1 = getCenterY() + getDotMargin() - (getDotSize()/2);
		int y2 = getCenterY()+ getHeight() - getDotMargin() - (getDotSize()/2);
		Dot rightUpDot = new Dot(x1, y1, false, true);
		setRightUpperDot(rightUpDot);
		Dot rightLoUDot = new Dot(x1, y2, false, true);
		setRightLowerDot(rightLoUDot);

	}

	public Dot getLeftDot() {
		return leftDot;
	}

	public void setLeftDot(Dot leftDot) {
		this.leftDot = leftDot;
	}

	public Dot getRightUpperDot() {
		return rightUpperDot;
	}

	public void setRightUpperDot(Dot rightUpperDot) {
		this.rightUpperDot = rightUpperDot;
	}

	public Dot getRightLowerDot() {
		return rightLowerDot;
	}

	public void setRightLowerDot(Dot rightLowerDot) {
		this.rightLowerDot = rightLowerDot;
	}

	public static String getSymbol() {
		return symbol;
	}

	public static void setSymbol(String text) {
		ShapeLessThan.symbol = text;
	}
}
