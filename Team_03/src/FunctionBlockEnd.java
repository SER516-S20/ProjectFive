import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FunctionBlockEnd.java - a class for creating FunctionBlock ) at a given
 * position
 * 
 * @author Srinivasan Sundar
 * @version 1.0
 * 
 */
public class FunctionBlockEnd implements Shape, Serializable {
	private static final long serialVersionUID = 1L;
	private int coordinateX;
	private int coordinateY;
	private Dot centerLeftDot;
	public Map<String, Boolean> isLineDrawn = new HashMap<>();
	public Map<String, Map<Line, Integer>> linesDrawn = new HashMap<>();
	public Map<String, Position> mapUpdatedDotCordinates = new HashMap<>();
	public List<Shape> connectedShapes = new ArrayList<>();

	Helper helper = new Helper();
	String message = "none";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	FunctionBlockEnd() {
		isLineDrawn.put("centerLeft", false);
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

	}

	@Override
	public void draw(Graphics graphics) {
		Draw draw = new Draw();
		draw.drawRectangle(graphics, coordinateX, coordinateY);
		helper.calculateCharacterPosition(this.getCoordinateX(), this.getCoordinateY());
		draw.drawCharacter(graphics, helper, Constants.FUNCTION_BLOCK_END_CHAR);
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
