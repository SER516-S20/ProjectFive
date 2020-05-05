package shape;

import frame.Constants;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HashBlock.java - a class for creating new tab
 * position
 * 
 * @author Ashwin Srinivasan
 * @author Chandan Yadav
 * @version 1.0
 * 
 */
public class HashBlock implements Shape, Serializable {
	private static final long serialVersionUID = 1L;
	private int coordinateX;
	private int coordinateY;
	private Dot centerRightDot, centerLeftDot;
	public Map<String, Boolean> isLineDrawn = new HashMap<>();
	public Map<String, Map<Line, Integer>> linesDrawn = new HashMap<>();
	public Map<String, Position> mapUpdatedDotCordinates = new HashMap<>();
	public List<Shape> connectedShapes = new ArrayList<>();
	String message = "none";
	Helper helper = new Helper();

	public HashBlock() {
		isLineDrawn.put(Constants.CENTER_LEFT, false);
		isLineDrawn.put(Constants.CENTER_RIGHT, false);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Dot getCenterLeftDot() {
		return centerLeftDot;
	}

	public void setCenterLeftDot(Dot centerLeftDot) {
		this.centerLeftDot = centerLeftDot;
	}
	
	
	public Dot getCenterRightDot() {
		return centerRightDot;
	}

	public void setCenterRightDot(Dot centerLeftDot) {
		this.centerRightDot = centerLeftDot;
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
		helper.calculateCenterRightDotPosition(this.getCoordinateX(), this.getCoordinateY(), Constants.DOT_HEIGHT,
				Constants.DOT_WIDTH);
		centerRightDot = new Dot();
		updateDotCoordinates(centerRightDot, true, Constants.CENTER_RIGHT, graphics);
	}

	@Override
	public void draw(Graphics graphics) {
		Draw draw = new Draw();
		draw.drawRectangle(graphics, coordinateX, coordinateY);
		helper.calculateCharacterPosition(this.getCoordinateX(), this.getCoordinateY());
		draw.drawCharacter(graphics, helper, Constants.HASH_BLOCK_CHAR);
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

	@Override
	public void setConnectedShapes(List<Shape> connectedShapes) {
		//  Auto-generated method stub
		this.connectedShapes = connectedShapes;
		
	}
}
