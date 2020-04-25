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
public class ShapeBar extends Icon{

	private static final long serialVersionUID = 1L;
	private static String symbol = "||";
	private Dot leftUpperDot,leftLowerDot;
	private Dot rightUpperDot, rightLowerDot;

	public ShapeBar(int x, int y) {
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
			obj2D.drawLine(rightUpperDot.getX(), rightUpperDot.getY(), rightLowerDot.getX(), rightLowerDot.getY());
			obj2D.drawLine(leftUpperDot.getX(), leftUpperDot.getY(), leftLowerDot.getX(), leftLowerDot.getY());		
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void setUpPoints() {	
		int x11 = getCenterX() + getWidth() - getDotMargin() - (getDotSize()/2);
		int y11 = getCenterY() + getDotMargin() - (getDotSize()/2);
		int y21 = getCenterY() + getHeight() - getDotMargin() - (getDotSize()/2);	
		Dot rightUpDot = new Dot(x11, y11, false, true);
		setRightUpperDot(rightUpDot);		
		Dot rightLoUDot = new Dot(x11, y21, false, true);
		setRightLowerDot(rightLoUDot);	
		int x1 = getCenterX() + getDotMargin() - (getDotSize()/2);
		int y1 = getCenterY() + getDotMargin() - (getDotSize()/2);
		int y2 = getCenterY() + getHeight() - getDotMargin() - (getDotSize()/2);	
		Dot lefttUpDot = new Dot(x1, y1, true, false);
		setLeftUpperDot(lefttUpDot);
		Dot lefttLoUDot = new Dot(x1, y2, true, false);
		setLeftLowerDot(lefttLoUDot);	
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
		ShapeBar.symbol = text;
	}

}
