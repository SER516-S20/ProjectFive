package listener;

import frame.Frame;
import shape.DefaultShapes;
import shape.Line;
import shape.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Delete.java - a class to delete links between two nodes and delete a node
 * itslef
 *
 * @author Nachiappan Lakshmanan
 * @version 1.0
 *
 */
public class Delete {
	public void performDelete() {
		if (MouseListener.selectedShape != null) {
			Shape shape = MouseListener.selectedShape;
			List<Shape> lstOfShapes = new DefaultShapes()
					.removeDeafultShapesFromCompile(Frame.mapDrawingAreas.get(Frame.currentTab).listOfShapes);
			if (shape instanceof Line) {
				deleteLine(shape, lstOfShapes);
			} else {
				deleteShape(shape, lstOfShapes);
			} 
		}
	}

	private void deleteShape(Shape shape, List<Shape> lstOfShapes) {
		Frame.mapDrawingAreas.get(Frame.currentTab).listOfShapes.remove(shape);
		Map<String, Map<Line, Integer>> mapLinesDrawn = shape.getLinesDrawn();
		List<Line> lstOfConnectedLines = new ArrayList<>();

		for (String each : mapLinesDrawn.keySet()) {
			for (Line line : mapLinesDrawn.get(each).keySet()) {
				lstOfConnectedLines.add(line);
				Frame.mapDrawingAreas.get(Frame.currentTab).listOfShapes.remove(line);
			}
		}
		for (Shape y : lstOfShapes) {
			List<Shape> connected = y.getConnectedShapes();
			if (connected != null) {
				if (connected.contains(shape)) {
					connected.remove(shape);
					Map<String, Map<Line, Integer>> mapLinesDrawnTemp = y.getLinesDrawn();
					for (Line temp : lstOfConnectedLines) {
						if (mapLinesDrawnTemp != null) {
							for (String each : mapLinesDrawnTemp.keySet()) {
								if (mapLinesDrawnTemp.get(each).containsKey(temp)) {
									y.getIsLineDrawnMap().put(each, false);
								}
							}
						}
					}
				}
			}
		}
	}

	private void deleteLine(Shape shape, List<Shape> lstOfShapes) {
		Line line = (Line) shape;
		List<Shape> lstOfShapesInvolved = new ArrayList<>();
		for (int i = 0; i < lstOfShapes.size(); i++) {
			Shape shapeTemp = lstOfShapes.get(i);
			Map<String, Map<Line, Integer>> mapLinesDrawn = shapeTemp.getLinesDrawn();
			if (mapLinesDrawn != null) {
				for (String each : mapLinesDrawn.keySet()) {
					if (mapLinesDrawn.get(each).containsKey(line)) {
						shapeTemp.getIsLineDrawnMap().put(each, false);
						lstOfShapesInvolved.add(shapeTemp);
					}
				}
			}
		}
		for (Shape x : lstOfShapesInvolved) {
			for (Shape y : lstOfShapesInvolved) {
				List<Shape> connected = y.getConnectedShapes();
				if (connected != null) {
					if (connected.contains(x)) {
						connected.remove(x);
					}
				}
			}
		}
		Frame.mapDrawingAreas.get(Frame.currentTab).listOfShapes.remove(shape);
	}
}
