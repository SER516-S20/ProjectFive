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
public class ShapeOpenParan extends Icon{

	private static final long serialVersionUID = 1L;
	private static String symbol = "(";
	private Dot rightDot;

	public ShapeOpenParan(int x, int y) {
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
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void setUpPoints() {
		int x = this.getCenterX() + getWidth() - getDotMargin() - (getDotSize()/2);
		int y = this.getCenterY() + (getHeight()/2) - (getDotSize()/2);
		Dot rightDot = new Dot(x, y, false, true);
		System.out.println(this.getCenterX());
		System.out.println(this.getCenterY());
		System.out.println(x);
		System.out.println(y);
		setRightDot(rightDot);
	}

	public Dot getRightDot() {
		return rightDot;
	}

	public void setRightDot(Dot rightDot) {
		this.rightDot = rightDot;
	}

	public static String getSymbol() {
		return symbol;
	}

	public static void setSymbol(String text) {
		ShapeOpenParan.symbol = text;
	}
}
