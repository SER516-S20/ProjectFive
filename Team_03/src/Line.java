import java.awt.Graphics;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * InputDialog.java - a class for connecting two different shapes using aline
 * 
 * @author Chandan Yadav
 * @version 1.0
 * 
 */
public class Line implements Shape,Serializable {

	private static final long serialVersionUID = 1L;
	private int coordinateX1;
	private int coordinateY1;
	private int coordinateX2;
	private int coordinateY2;

	public int getCoordinateX2() {
		return coordinateX2;
	}

	public void setCoordinateX2(int coordinateX2) {
		this.coordinateX2 = coordinateX2;
	}

	public int getCoordinateY2() {
		return coordinateY2;
	}

	public void setCoordinateY2(int coordinateY2) {
		this.coordinateY2 = coordinateY2;
	}

	public int getCoordinateX1() {
		return coordinateX1;
	}

	public int getCoordinateY1() {
		return coordinateY1;
	}

	@Override
	public void setPosition(int x1, int y1) {
		this.coordinateX1 = x1;
		this.coordinateY1 = y1;
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.drawLine(coordinateX1, coordinateY1, coordinateX2, coordinateY2);
	}

	@Override
	public void setPosition(int x1, int y1, int x2, int y2) {
		this.coordinateX1 = x1;
		this.coordinateY1 = y1;
		this.coordinateX2 = x2;
		this.coordinateY2 = y2;
	}

	@Override
	public int getCoordinateX() {
		return 0;
	}

	@Override
	public int getCoordinateY() {
		return 0;
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
	}



}
