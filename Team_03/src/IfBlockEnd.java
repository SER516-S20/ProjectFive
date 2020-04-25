import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * IfBlockEnd.java - a class for creating IfBlockEnd > at a given
 * position
 * 
 * @author Ashwin Srinivasan
 * @version 1.0
 * 
 */
public class IfBlockEnd implements Shape, Serializable {
	private static final long serialVersionUID = 1L;
	private int coordinateX;
	private int coordinateY;
	private Dot topLeftDot, bottomLeftDot, centerRightDot;
	public Map<String, Boolean> isLineDrawn = new HashMap<>();
	public Map<String, Map<Line, Integer>> linesDrawn = new HashMap<>();
	public Map<String, Position> mapUpdatedDotCordinates = new HashMap<>();
	public List<Shape> connectedShapes = new ArrayList<>();
	String message = "none";
	Helper helper = new Helper();

	IfBlockEnd() {
		isLineDrawn.put(Constants.CENTER_RIGHT, false);
		isLineDrawn.put(Constants.TOP_LEFT, false);
		isLineDrawn.put(Constants.BOTTOM_LEFT, false);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Dot getTopLeftDot() {
		return topLeftDot;
	}

	public void setTopLeftDot(Dot topLeftDot) {
		this.topLeftDot = topLeftDot;
	}

	public Dot getBottomLeftDot() {
		return bottomLeftDot;
	}

	public void setBottomLeftDot(Dot bottomLeftDot) {
		this.bottomLeftDot = bottomLeftDot;
	}

	public Dot getCenterRightDot() {
		return centerRightDot;
	}

	public void setCenterRightDot(Dot centerRightDot) {
		this.centerRightDot = centerRightDot;
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
		helper.calculateCenterRightDotPosition(this.getCoordinateX(), this.getCoordinateY(), Constants.DOT_HEIGHT,
				Constants.DOT_WIDTH);
		centerRightDot = new Dot();
		updateDotCoordinates(centerRightDot, true, Constants.CENTER_RIGHT, graphics);
		helper.calculateTopLeftDotPosition(this.getCoordinateX(), this.getCoordinateY(), Constants.DOT_HEIGHT,
				Constants.DOT_WIDTH);
		topLeftDot = new Dot();
		updateDotCoordinates(topLeftDot, false, Constants.TOP_LEFT, graphics);
		helper.calculateBottomLeftDotPosition(this.getCoordinateX(), this.getCoordinateY(), Constants.DOT_HEIGHT,
				Constants.DOT_WIDTH);
		bottomLeftDot = new Dot();
		updateDotCoordinates(bottomLeftDot, false, Constants.BOTTOM_LEFT, graphics);
	}

	@Override
	public void draw(Graphics graphics) {
		Draw draw = new Draw();
		draw.drawRectangle(graphics, coordinateX, coordinateY);
		helper.calculateCharacterPosition(this.getCoordinateX(), this.getCoordinateY());
		draw.drawCharacter(graphics, helper, Constants.IF_BLOCK_END_CHAR);
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
