import java.awt.Graphics;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Dot.java - a class for creating Dot at a particular position for
 * Connecting two different shapes
 * 
 * @author Chandan Yadav
 * @version 1.0
 * 
 */
public class Dot implements Shape,Serializable {

	private static final long serialVersionUID = 1L;
	private int coordinateX;
	private int coordinateY;

	private boolean isStart;
	private String dotName;

	public String getDotName() {
		return dotName;
	}

	public void setDotName(String dotName) {
		this.dotName = dotName;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
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
		graphics.drawRect(coordinateX, coordinateY, Constants.DOT_WIDTH, Constants.DOT_HEIGHT);
		graphics.fillRect(coordinateX, coordinateY, Constants.DOT_WIDTH, Constants.DOT_HEIGHT);

	}

	@Override
	public void setPosition(int x, int y, int x1, int y1) {
		//Empty Function
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
		//Empty Function
	}

	
}
