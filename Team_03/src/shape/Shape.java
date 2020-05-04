package shape;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;

/**
 * interface shape which is implemented by circle, triangle and square
 * 
 * @author Nachiappan Lakshmanan
 * @version 1.0
 * @since 01/29/2020
 */
public interface Shape {

	public void setPosition(int x, int y);

	public void setPosition(int x, int y, int x1, int y1);

	public int getCoordinateX();

	public int getCoordinateY();

	public void draw(Graphics g);

	public Map<String, Boolean> getIsLineDrawnMap();

	public Map<String,Map<Line, Integer>> getLinesDrawn();
	public Map<String,Position> getUpdatedDotCordinates();
	
	public List<Shape> getConnectedShapes();
	public void setConnectedShapes(List<Shape> connectedShapes);
	public String getMessage();
	public void setMessage(String message);

}
