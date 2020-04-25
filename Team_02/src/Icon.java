import javax.swing.JPanel;

/**
 * @author abhinaw sarang
 * @created 03-15-2020
 * @version 1.0
 */
public class Icon extends JPanel{

	private static final long serialVersionUID = 1L;
	private int centerX;
	private int centerY;
	private String text = null;
	private int height = 60;
	private int width = 200;
	private int dotSize = 6;
	private int dotMargin = 10;

	Icon() {  }

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setUpPoints() {

	}

	public int getMiddlePointX() {
		return centerX+(width/2);
	}

	public int getMiddlePointY() {
		return centerY+(height/2)+3;
	}

	public int getDotSize() {
		return dotSize;
	}

	public void setDotSize(int dotSize) {
		this.dotSize = dotSize;
	}

	public int getDotMargin() {
		return dotMargin;
	}

	public void setDotMargin(int dotMargin) {
		this.dotMargin = dotMargin;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
