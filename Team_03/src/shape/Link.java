package shape;

import frame.Constants;
import frame.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Link {

	public void alterLinks(List<Shape> alterConnections, List<Dot> alterConnectionsDots) {
		if (alterConnections.size() == 2) {
			Frame.jlabel.setText("Altering the link between shapes");
			Shape shape1 = alterConnections.get(0);
			Shape shape2 = alterConnections.get(1);
			Map<Line, Integer> linesDrawnMap = shape1.getLinesDrawn().get(alterConnectionsDots.get(0).getDotName());
			Line lineToBeAltered = null;
			for (Line line : linesDrawnMap.keySet()) {
				lineToBeAltered = line;
			}
			List<Shape> lstOfShapes = new DefaultShapes()
					.removeDeafultShapesFromCompile(Frame.mapDrawingAreas.get(Frame.currentTab).listOfShapes);
			Shape newShape = null;
			Dot newShapeDot = null;
			for (int i = 0; i < lstOfShapes.size(); i++) {
				Shape temp = lstOfShapes.get(i);
				Map<String, Map<Line, Integer>> mapLinesDrawn = temp.getLinesDrawn();
				if (mapLinesDrawn != null) {
					for (String each : mapLinesDrawn.keySet()) {
						if (mapLinesDrawn.get(each).containsKey(lineToBeAltered) && temp != shape1) {
							newShape = temp;
							newShapeDot = getCorrespondingDot(newShape, each);
							break;
						}
					}
				}
			}
			if (newShape != null) {
				performDelete(lineToBeAltered);
				List<Dot> selectedDots = new ArrayList<>();
				selectedDots.add(newShapeDot);
				selectedDots.add(alterConnectionsDots.get(1));
				List<Shape> selectedShapes = new ArrayList<>();
				selectedShapes.add(newShape);
				selectedShapes.add(shape2);
				new Draw().drawLine(selectedDots, null, selectedShapes);
			}
			Frame.mapDrawingAreas.get(Frame.currentTab).repaint();
			alterConnections.clear();
			alterConnectionsDots.clear();
		} // if in alterconnections end
	}

	public Dot getCorrespondingDot(Shape shape, String dotName) {
		if (shape instanceof FunctionBlockBegin) {
			return ((FunctionBlockBegin) shape).getCenterRightDot();
		} else if (shape instanceof FunctionBlockEnd) {
			return ((FunctionBlockEnd) shape).getCenterLeftDot();
		} else if (shape instanceof IfBlockBegin) {
			IfBlockBegin ifBlockBegin = (IfBlockBegin) shape;
			if (dotName.equals(Constants.CENTER_LEFT)) {
				return ifBlockBegin.getCenterLeftDot();
			} else if (dotName.equals(Constants.TOP_RIGHT)) {
				return ifBlockBegin.getTopRightDot();
			} else if (dotName.equals(Constants.BOTTOM_RIGHT)) {
				return ifBlockBegin.getBottomRightDot();
			}
		} else if (shape instanceof IfBlockEnd) {
			IfBlockEnd ifBlockEnd = (IfBlockEnd) shape;
			if (dotName.equals(Constants.TOP_LEFT)) {
				return ifBlockEnd.getTopLeftDot();
			} else if (dotName.equals(Constants.BOTTOM_LEFT)) {
				return ifBlockEnd.getBottomLeftDot();
			} else if (dotName.equals(Constants.CENTER_RIGHT)) {
				return ifBlockEnd.getCenterRightDot();
			}
		} else if (shape instanceof ForLoop) {
			ForLoop forLoop = (ForLoop) shape;
			if (dotName.equals(Constants.TOP_LEFT)) {
				return forLoop.getTopLeftDot();
			} else if (dotName.equals(Constants.BOTTOM_LEFT)) {
				return forLoop.getBottomLeftDot();
			} else if (dotName.equals(Constants.TOP_RIGHT)) {
				return forLoop.getTopRightDot();
			} else if (dotName.equals(Constants.BOTTOM_RIGHT)) {
				return forLoop.getBottomRightDot();
			}
		} else if (shape instanceof BarShape) {
			BarShape valueHolderBlock = (BarShape) shape;
			if (dotName.equals(Constants.CENTER_LEFT)) {
				return valueHolderBlock.getCenterLeftDot();
			} else if (dotName.equals(Constants.CENTER_RIGHT)) {
				return valueHolderBlock.getCenterRightDot();
			}
		} else if (shape instanceof HashBlock) {
			HashBlock hashBlock = (HashBlock) shape;
			if (dotName.equals(Constants.CENTER_LEFT)) {
				return hashBlock.getCenterLeftDot();
			} else if (dotName.equals(Constants.CENTER_RIGHT)) {
				return hashBlock.getCenterRightDot();
			}
		}
		return null;
	}

	public void performDelete(Shape lineToBeAltered) {
		Frame.jlabel.setText("Deleting the link to be changed");
		Shape shape = lineToBeAltered;
		List<Shape> lstOfShapes = new DefaultShapes()
				.removeDeafultShapesFromCompile(Frame.mapDrawingAreas.get(Frame.currentTab).listOfShapes);
		if (shape instanceof Line) {
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
}
