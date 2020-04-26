import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IfBlockBegin.java - a class for creating IfBlock < at a given
 * position
 * 
 * @author Ashwin Srinivasan
 * @version 1.0
 * 
 */
public class IfBlockBegin implements Shape, Serializable {
	private static final long serialVersionUID = 1L;
	private int coordinateX;
	private int coordinateY;
	private Dot topRightDot, bottomRightDot, centerLeftDot;
	public Map<String, Boolean> isLineDrawn = new HashMap<>();
	public Map<String, Map<Line, Integer>> linesDrawn = new HashMap<>();
	public Map<String, Position> mapUpdatedDotCordinates = new HashMap<>();
	public List<Shape> connectedShapes = new ArrayList<>();
	String message = "none";
	Helper helper = new Helper();

	IfBlockBegin() {
		isLineDrawn.put(Constants.CENTER_LEFT, false);
		isLineDrawn.put(Constants.TOP_RIGHT, false);
		isLineDrawn.put(Constants.BOTTOM_RIGHT, false);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Dot getTopRightDot() {
		return topRightDot;
	}

	public void setTopRightDot(Dot topRightDot) {
		this.topRightDot = topRightDot;
	}

	public Dot getBottomRightDot() {
		return bottomRightDot;
	}

	public void setBottomRightDot(Dot bottomRigthDot) {
		this.bottomRightDot = bottomRigthDot;
	}

	public Dot getCenterLeftDot() {
		return centerLeftDot;
	}

	public void setCenterLeftDot(Dot centerLeftDot) {
		this.centerLeftDot = centerLeftDot;
	}

	public int getCoordinateX() {
		return coordinateX;
	}

	public int getCoordinateY() {
		return coordinateY;
	}

	@Override
	public void setPosition(int x, int y) {
		this.coordinateX = x;
		this.coordinateY = y;
	}

	public void updateDotCoordinates(Dot dot, boolean status, String dotName, Graphics graphics) {
		dot.setPosition(helper.getXCordinate(), helper.getYCordinate());
		dot.draw(graphics);
		Position position = new Position();
		position.setCoordinateX(helper.getXCordinate());
		position.setCoordinateY(helper.getYCordinate());
		mapUpdatedDotCordinates.put(dotName, position);
		dot.setStart(status);
		dot.setDotName(dotName);
	}

	public void drawDots(Graphics graphics) {
		helper.calculateCenterLeftDotPosition(this.getCoordinateX(), this.getCoordinateY(), Constants.DOT_HEIGHT,
				Constants.DOT_WIDTH);
		centerLeftDot = new Dot();
		updateDotCoordinates(centerLeftDot, false, Constants.CENTER_LEFT, graphics);
		helper.calculateTopRightDotPosition(this.getCoordinateX(), this.getCoordinateY(), Constants.DOT_HEIGHT,
				Constants.DOT_WIDTH);
		topRightDot = new Dot();
		updateDotCoordinates(topRightDot, true, Constants.TOP_RIGHT, graphics);
		helper.calculateBottomRightDotPosition(this.getCoordinateX(), this.getCoordinateY(), Constants.DOT_HEIGHT,
				Constants.DOT_WIDTH);
		bottomRightDot = new Dot();
		updateDotCoordinates(bottomRightDot, true, Constants.BOTTOM_RIGHT, graphics);
	}

	@Override
	public void draw(Graphics graphics) {
		Draw draw = new Draw();
		draw.drawRectangle(graphics, coordinateX, coordinateY);
		helper.calculateCharacterPosition(this.getCoordinateX(), this.getCoordinateY());
		draw.drawCharacter(graphics, helper, Constants.IF_BLOCK_BEGIN_CHAR);
		drawDots(graphics);
	}

	@Override
	public void setPosition(int x, int y, int x1, int y1) {
	}

	@Override
	public Map<String, Boolean> getIsLineDrawnMap() {
		return isLineDrawn;
	}

	@Override
	public Map<String, Map<Line, Integer>> getLinesDrawn() {
		return linesDrawn;
	}

	@Override
	public Map<String, Position> getUpdatedDotCordinates() {
		return mapUpdatedDotCordinates;
	}

	@Override
	public List<Shape> getConnectedShapes() {
		return connectedShapes;
	}

}
