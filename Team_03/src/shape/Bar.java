package shape;

import frame.Constants;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Bar.java - a class for creating vertical Bar shape at a particular position
 * 
 * @author Srinivasan Sundar
 * @version 1.0
 * 
 */
public class Bar implements Shape, Serializable {
	private static final long serialVersionUID = 1L;
	private int coordinateX;
	private int coordinateY;

	private String dotName;

	public String getDotName() {
		return dotName;
	}

	public void setDotName(String dotName) {
		this.dotName = dotName;
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

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(Constants.DOT_COLOR);
		graphics.drawRect(coordinateX, coordinateY, Constants.BAR_WIDTH, Constants.BAR_HEIGHT);
		graphics.fillRect(coordinateX, coordinateY, Constants.BAR_WIDTH, Constants.BAR_HEIGHT);
	}

	@Override
	public void setPosition(int x, int y, int x1, int y1) {
		// Empty function
	}

	@Override
	public Map<String, Boolean> getIsLineDrawnMap() {
		return null;
	}

	@Override
	public Map<String, Map<Line, Integer>> getLinesDrawn() {
		return null;
	}

	@Override
	public Map<String, Position> getUpdatedDotCordinates() {
		return null;
	}

	@Override
	public List<Shape> getConnectedShapes() {
		return null;
	}

	@Override
	public String getMessage() {
		return null;
	}

	@Override
	public void setMessage(String message) {
		// Empty function
	}

	@Override
	public void setConnectedShapes(List<Shape> connectedShapes) {
		// Auto-generated method stub

	}

}
