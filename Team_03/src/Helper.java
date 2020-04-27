import java.io.Serializable;
import javax.swing.JButton;

/**
 * Helper.java a class to help in calculating the positions of different dots
 * 
 * @author Nachiappan Lakshmanan
 * @version 1.0
 * 
 */
public class Helper implements Serializable {
	private static final long serialVersionUID = 1L;
	private int xCordinate;
	private int yCordinate;

	public int getXCordinate() {
		return xCordinate;
	}

	public void setXCordinate(int xCordinate) {
		this.xCordinate = xCordinate;
	}

	public int getYCordinate() {
		return yCordinate;
	}

	public void setYCordinate(int yCordinate) {
		this.yCordinate = yCordinate;
	}

	public void calculateIconPosition(JButton jButton, int iconHeight, int iconWidth) {
		setXCordinate(jButton.getX() + ((jButton.getWidth() / 2) - (iconWidth / 2)));
		setYCordinate(jButton.getY() + (jButton.getHeight() / 2) - (iconHeight / 2));
	}

	public void calculateCenterRightDotPosition(int coordinateX, int coordinateY, int dotHeight, int dotWidth) {
		setYCordinate(coordinateY + (Constants.BRACKET_HEIGHT / 2) - (dotHeight / 2));
		setXCordinate(coordinateX + (Constants.BRACKET_WIDTH) - (Constants.DOT_SPAN + dotWidth));
	}

	public void calculateCenterLeftDotPosition(int coordinateX, int coordinateY, int dotHeight, int dotWidth) {
		setYCordinate(coordinateY + (Constants.BRACKET_HEIGHT / 2) - (dotHeight / 2));
		setXCordinate(coordinateX + Constants.DOT_SPAN);
	}

	public void calculateTopRightDotPosition(int coordinateX, int coordinateY, int dotHeight, int dotWidth) {
		setYCordinate(coordinateY + Constants.DOT_SPAN);
		setXCordinate(coordinateX + (Constants.BRACKET_WIDTH) - (Constants.DOT_SPAN + dotWidth));
	}

	public void calculateBottomRightDotPosition(int coordinateX, int coordinateY, int dotHeight, int dotWidth) {
		setYCordinate(coordinateY + Constants.BRACKET_HEIGHT - (Constants.DOT_SPAN * 2));
		setXCordinate(coordinateX + (Constants.BRACKET_WIDTH) - (Constants.DOT_SPAN + dotWidth));
	}

	public void calculateTopLeftDotPosition(int coordinateX, int coordinateY, int dotHeight, int dotWidth) {
		setYCordinate(coordinateY + Constants.DOT_SPAN);
		setXCordinate(coordinateX + Constants.DOT_SPAN);
	}

	public void calculateBottomLeftDotPosition(int coordinateX, int coordinateY, int dotHeight, int dotWidth) {
		setYCordinate(coordinateY + Constants.BRACKET_HEIGHT - (Constants.DOT_SPAN * 2));
		setXCordinate(coordinateX + Constants.DOT_SPAN);
	}

	public void calculateCharacterPosition(int coordinateX, int coordinateY) {
		setXCordinate(coordinateX + (Constants.BRACKET_WIDTH / 2) - Constants.CHARACTER_DEVIATION);
		setYCordinate(coordinateY + (Constants.BRACKET_HEIGHT / 2) + Constants.CHARACTER_DEVIATION);
	}
}
