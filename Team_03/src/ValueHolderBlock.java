import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ValueHolderBlock.java - a class for creating ValueHolderBlock || at a given
 * position
 * 
 * @author Ashutosh Dey
 * @version 1.0
 * 
 */
public class ValueHolderBlock implements Shape, Serializable {
	private static final long serialVersionUID = 1L;
	private int coordinateX;
	private int coordinateY;
	private Bar centerLeftBar, centerRightBar;
	public Map<String, Map<Line, Integer>> linesDrawn = new HashMap<>();
	public Map<String, Position> mapUpdatedBarCordinates = new HashMap<>();
	public List<Shape> connectedShapes = new ArrayList<>();
	Helper helper = new Helper();

	String message = "none";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Bar getCenterLeftBar() {
		return centerLeftBar;
	}

	public void setCenterLeftBar(Bar centerLeftBar) {
		this.centerLeftBar = centerLeftBar;
	}

	public Bar getCenterRightBar() {
		return centerRightBar;
	}

	public void setCenterRightBar(Bar centerRightBar) {
		this.centerRightBar = centerRightBar;
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

	public void updateBarCoordinates(Bar bar, boolean status, String dotName, Graphics graphics) {
		bar.setPosition(helper.getXCordinate(), helper.getYCordinate());
		bar.draw(graphics);
		Position position = new Position();
		position.setCoordinateX(helper.getXCordinate());
		position.setCoordinateY(helper.getYCordinate());
		mapUpdatedBarCordinates.put(dotName, position);
		bar.setDotName(dotName);
	}

	public void drawBars(Graphics graphics) {
		helper.calculateCenterLeftDotPosition(this.getCoordinateX(), this.getCoordinateY(), Constants.BAR_HEIGHT,
				Constants.BAR_WIDTH);
		centerLeftBar = new Bar();
		updateBarCoordinates(centerLeftBar, false, "centerLeft", graphics);
		helper.calculateCenterRightDotPosition(this.getCoordinateX(), this.getCoordinateY(), Constants.BAR_HEIGHT,
				Constants.BAR_WIDTH);
		centerRightBar = new Bar();
		updateBarCoordinates(centerRightBar, true, "centerRight", graphics);
	}

	@Override
	public void draw(Graphics graphics) {
		Draw draw = new Draw();
		draw.drawRectangle(graphics, coordinateX, coordinateY);
		helper.calculateCharacterPosition(this.getCoordinateX(), this.getCoordinateY());
		draw.drawCharacter(graphics, helper, Constants.VALUE_HOLDER_CHAR);
		drawBars(graphics);
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
		return linesDrawn;
	}

	@Override
	public Map<String, Position> getUpdatedDotCordinates() {
		return mapUpdatedBarCordinates;
	}

	@Override
	public List<Shape> getConnectedShapes() {
		return connectedShapes;
	}

}
