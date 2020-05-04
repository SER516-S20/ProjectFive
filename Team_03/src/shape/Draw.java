package shape;

import frame.Constants;
import frame.Frame;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Draw.java - a helper class for drawing different
 * shapes on the playground
 * 
 * @author Srinivasan Sundar
 * @version 1.0
 * 
 */
public class Draw {

	public void drawCharacter(Graphics graphics, Helper helper, String message) {
		graphics.setColor(Constants.CHARACTER_COLOR);
		Font font = new Font(Constants.CHARACTER_FONT, Constants.CHARACTER_STYLE, Constants.CHARACTER_SIZE);
		graphics.setFont(font);
		graphics.drawString(message, helper.getXCordinate(), helper.getYCordinate());
	}

	public void drawRectangle(Graphics graphics, int coordinateX, int coordinateY) {
		graphics.setColor(Constants.BRACKET_COLOR);
		graphics.drawRect(coordinateX, coordinateY, Constants.BRACKET_WIDTH, Constants.BRACKET_HEIGHT);
		graphics.fillRect(coordinateX, coordinateY, Constants.BRACKET_WIDTH, Constants.BRACKET_HEIGHT);
	}

	public void storeDrawnLines(int index, Dot dot, Line line, List<Shape> selectedShapes) {
		Map<String, Map<Line, Integer>> mapTemp = selectedShapes.get(index).getLinesDrawn();
		if (mapTemp.containsKey(dot.getDotName())) {
			mapTemp.get(dot.getDotName()).put(line, index);
		} else {
			Map<Line, Integer> temp = new HashMap<>();
			temp.put(line, index);
			mapTemp.put(dot.getDotName(), temp);
		}
	}

	public void storeDrawnLines(int index, Bar dot, Line line, List<Shape> selectedShapes) {
		Map<String, Map<Line, Integer>> mapTemp = selectedShapes.get(index).getLinesDrawn();
		if (mapTemp.containsKey(dot.getDotName())) {
			mapTemp.get(dot.getDotName()).put(line, index);
		} else {
			Map<Line, Integer> temp = new HashMap<>();
			temp.put(line, index);
			mapTemp.put(dot.getDotName(), temp);
		}
	}

	public void drawLineFromBarToDot(List<Dot> selectedDots, List<Bar> selectedBars, List<Shape> selectedShapes) {
		Frame.jlabel.setText(Constants.LINE_DRAWN_LABEL);
		Line line = new Line();
		Dot dot = selectedDots.get(0);
		Bar bar = selectedBars.get(0);
		if (selectedShapes.get(0).getIsLineDrawnMap() != null) {
			line.setPosition(dot.getCoordinateX(), dot.getCoordinateY(), bar.getCoordinateX(), bar.getCoordinateY());
			Frame.mapDrawingAreas.get(Frame.currentTab).addLine(line);
			selectedShapes.get(0).getIsLineDrawnMap().put(dot.getDotName(), true);
			storeDrawnLines(0, dot, line, selectedShapes);
			storeDrawnLines(1, bar, line, selectedShapes);
			selectedShapes.get(0).getConnectedShapes().add(selectedShapes.get(1));
		} else {
			line.setPosition(bar.getCoordinateX(), bar.getCoordinateY(), dot.getCoordinateX(), dot.getCoordinateY());
			Frame.mapDrawingAreas.get(Frame.currentTab).addLine(line);
			selectedShapes.get(1).getIsLineDrawnMap().put(dot.getDotName(), true);
			storeDrawnLines(1, dot, line, selectedShapes);
			storeDrawnLines(0, bar, line, selectedShapes);
			selectedShapes.get(1).getConnectedShapes().add(selectedShapes.get(0));
		}
		selectedDots.clear();
		selectedShapes.clear();
		selectedBars.clear();

	}

	public void drawLine(List<Dot> selectedDots, List<Bar> selectedBars, List<Shape> selectedShapes) {
		Frame.jlabel.setText(Constants.LINE_DRAWN_LABEL);
		Line line = new Line();
		Dot dot1 = selectedDots.get(0);
		Dot dot2 = selectedDots.get(1);
		selectedShapes.get(0).getIsLineDrawnMap().put(dot1.getDotName(), true);
		selectedShapes.get(1).getIsLineDrawnMap().put(dot2.getDotName(), true);
		line.setPosition(dot1.getCoordinateX(), dot1.getCoordinateY(), dot2.getCoordinateX(), dot2.getCoordinateY());
		Frame.mapDrawingAreas.get(Frame.currentTab).addLine(line);
		storeDrawnLines(0, dot1, line, selectedShapes);
		storeDrawnLines(1, dot2, line, selectedShapes);
		selectedShapes.get(0).getConnectedShapes().add(selectedShapes.get(1));
		selectedDots.clear();
		selectedShapes.clear();
	}
	
	
	public boolean checkBoundary(int x1, int y1, int x2, int y2, int width, int height) {
		if (x2 > x1 && y2 > y1 && x2 < (x1 + width) && y2 < (y1 + height)) {
			return true;
		}
		return false;
	}
}
