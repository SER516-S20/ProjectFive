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
public class ShapeClosedParan extends Icon{

	private static final long serialVersionUID = 1L;
	private static String symbol = ")";
	private Dot leftDot;

	public ShapeClosedParan(int x, int y) {
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
	}

	public Dot getLeftDot() {
		return leftDot;
	}

	public void setLeftDot(Dot leftDot) {
		this.leftDot = leftDot;
	}

	public static String getSymbol() {
		return symbol;
	}

	public static void setSymbol(String text) {
		ShapeClosedParan.symbol = text;
	}
}
