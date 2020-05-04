package listener;

import frame.Constants;
import frame.DrawingArea;
import frame.Frame;
import frame.Main;
import shape.*;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.event.MouseInputAdapter;

/**
 * This class is used to drag a particular shape and create a shape on mouse
 * click
 * 
 * @author Ashutosh Dey
 * @version 1.0
 * @since 03/20/2020
 */
public class MouseListener extends MouseInputAdapter {
	static Shape selectedShape;
	Dot alterConnection = null;
	public boolean activateDrag = false;
	DrawingArea drawingArea;
	List<Dot> selectedDots = new ArrayList<Dot>();
	List<Bar> selectedBars = new ArrayList<Bar>();
	List<Shape> selectedShapes = new ArrayList<>();
	Draw draw = new Draw();
	List<Shape> alterConnections = new ArrayList<>();
	List<Dot> alterConnectionsDots = new ArrayList<>();

	JButtonActionListener jButtonActionListener = new JButtonActionListener();

	public void mousePressed(MouseEvent e) {

		Point startPoint = e.getPoint();
		int coordinateX = Math.min(startPoint.x, e.getX());
		int coordinateY = Math.min(startPoint.y, e.getY());

		if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
			if (!isShapeClicked(coordinateX, coordinateY)) {
				if (new DeleteDialog().isLineClicked(coordinateX, coordinateY)) {
					Frame.jlabel.setText("Line clicked to be deleted");
				}
			}
			Frame.jlabel.setText("Please close the pop up");
			new DeleteDialog(Main.frame, "Please close the pop up", true, new Point(coordinateX, coordinateY + 200));

		} else if (e.getClickCount() == 2) {
			showPopUp(coordinateX, coordinateY);
		} else {
			activateDrag = true;
			if (isShapeClicked(coordinateX, coordinateY) && selectedShape != null
					&& selectedShape.getCoordinateX() == 0) {

				activateDrag = true;
				if (selectedShape instanceof HashBlock) {
					Frame.jlabel.setText("Created new Tab Please name the tab");
					Main.frame.createNewTab(selectedShape);
				}
				if (selectedShape.toString().contains("FunctionBlockBegin")
						|| selectedShape.toString().contains("FunctionBlockEnd")) {
					if (ifAlreadyContainsFunctionBlocks(selectedShape.toString())) {
						activateDrag = false;
					}
				}

			} else if (isShapeClicked(coordinateX, coordinateY)) {
				alterConnection = null;
				if (isDotClicked(coordinateX, coordinateY) || isBarClicked(coordinateX, coordinateY)) {

					if (alterConnection != null) {
						alterConnectionsDots.add(alterConnection);
						alterConnections.add(selectedShape);
						new Link().alterLinks(alterConnections, alterConnectionsDots);

					}
					activateDrag = false;

				}
			} else {

				jButtonActionListener.addNewShapesToFrame(coordinateX, coordinateY);
			}
		}
	}

	public boolean ifAlreadyContainsFunctionBlocks(String shapeString) {
		List<Shape> lstOfShapes = new DefaultShapes()
				.removeDeafultShapesFromCompile(Frame.mapDrawingAreas.get(Frame.currentTab).listOfShapes);
		for (int i = 0; i < lstOfShapes.size(); i++) {
			Shape shape = lstOfShapes.get(i);
			if (shape.toString().contains("FunctionBlockBegin") && shapeString.contains("FunctionBlockBegin")) {
				return true;
			}
			if (shape.toString().contains("FunctionBlockEnd") && shapeString.contains("FunctionBlockEnd")) {
				return true;
			}
		}
		return false;

	}

	public void showPopUp(int coordinateX, int coordinateY) {
		if (isShapeClicked(coordinateX, coordinateY)) {

			Frame.jlabel.setText("Please close the dialog to continue");
			new InputDialog(Main.frame, "Plese enter the text and click enter and close", true,
					selectedShape.getMessage(), new Point(coordinateX, coordinateY));

		}
	}

	public boolean isBarClicked(int coordinateX, int coordinateY) {
		Bar[] listOfBars = null;
		if (selectedShape instanceof ValueHolderBlock) {
			ValueHolderBlock lessThanBracket = (ValueHolderBlock) selectedShape;
			listOfBars = new Bar[] { lessThanBracket.getCenterLeftBar(), lessThanBracket.getCenterRightBar() };
		}
		if (listOfBars != null) {
			for (int i = 0; i < listOfBars.length; i++) {
				Bar bar = listOfBars[i];
				if (draw.checkBoundary(bar.getCoordinateX(), bar.getCoordinateY(), coordinateX, coordinateY,
						Constants.BAR_WIDTH, Constants.BAR_HEIGHT)) {
					if (selectedDots.size() == 1 && selectedBars.size() == 0) {
						selectedBars.add(bar);
						selectedShapes.add(selectedShape);

						draw.drawLineFromBarToDot(selectedDots, selectedBars, selectedShapes);
					} else if (selectedDots.size() == 0 && selectedBars.size() == 0) {
						selectedBars.add(bar);

						selectedShapes.add(selectedShape);
						Frame.jlabel.setText("Please choose a dot");
					}

					return true;

				}
			}
		}
		return false;
	}

	public Dot[] getDotsList(Dot[] listOfDots) {
		if (selectedShape instanceof FunctionBlockBegin) {
			FunctionBlockBegin lessThanBracket = (FunctionBlockBegin) selectedShape;
			listOfDots = new Dot[] { lessThanBracket.getCenterRightDot() };
		} else if (selectedShape instanceof FunctionBlockEnd) {
			FunctionBlockEnd lessThanBracket = (FunctionBlockEnd) selectedShape;
			listOfDots = new Dot[] { lessThanBracket.getCenterLeftDot() };
		} else if (selectedShape instanceof IfBlockBegin) {
			IfBlockBegin lessThanBracket = (IfBlockBegin) selectedShape;
			listOfDots = new Dot[] { lessThanBracket.getBottomRightDot(), lessThanBracket.getCenterLeftDot(),
					lessThanBracket.getTopRightDot() };
		} else if (selectedShape instanceof IfBlockEnd) {
			IfBlockEnd lessThanBracket = (IfBlockEnd) selectedShape;
			listOfDots = new Dot[] { lessThanBracket.getBottomLeftDot(), lessThanBracket.getCenterRightDot(),
					lessThanBracket.getTopLeftDot() };
		} else if (selectedShape instanceof ForLoop) {
			ForLoop lessThanBracket = (ForLoop) selectedShape;
			listOfDots = new Dot[] { lessThanBracket.getBottomLeftDot(), lessThanBracket.getBottomRightDot(),
					lessThanBracket.getTopLeftDot(), lessThanBracket.getTopRightDot() };
		} else if (selectedShape instanceof BarShape) {
			BarShape lessThanBracket = (BarShape) selectedShape;
			listOfDots = new Dot[] { lessThanBracket.getCenterLeftDot(), lessThanBracket.getCenterRightDot() };
		} else if (selectedShape instanceof HashBlock) {
			HashBlock lessThanBracket = (HashBlock) selectedShape;
			listOfDots = new Dot[] { lessThanBracket.getCenterLeftDot(), lessThanBracket.getCenterRightDot() };
		}

		return listOfDots;

	}

	public boolean isDotClicked(int coordinateX, int coordinateY) {
		Dot[] listOfDots = null;
		listOfDots = getDotsList(listOfDots);
		if (listOfDots != null) {
			for (int i = 0; i < listOfDots.length; i++) {
				Dot dot = listOfDots[i];
				if (draw.checkBoundary(dot.getCoordinateX(), dot.getCoordinateY(), coordinateX, coordinateY,
						Constants.DOT_WIDTH, Constants.DOT_HEIGHT)
						&& !selectedShape.getIsLineDrawnMap().get(dot.getDotName())) {
					if (alterConnections.size() == 1) {
						alterConnection = dot;
						Frame.jlabel
								.setText("Dot ready to be changed please drag (inval) " + alterConnection.getDotName());
					} else if (selectedDots.size() == 1 && !dot.isStart()
							&& !selectedShapes.get(0).toString().equals(selectedShape.toString())) {
						selectedDots.add(dot);
						selectedShapes.add(selectedShape);
						draw.drawLine(selectedDots, selectedBars, selectedShapes);
					} else if (selectedDots.size() == 0 && dot.isStart() && selectedBars.size() == 0) {
						selectedDots.add(dot);
						selectedShapes.add(selectedShape);
						Frame.jlabel.setText("Please choose another dot or a bar");
					} else if (selectedDots.size() == 0 && selectedBars.size() == 1 && !dot.isStart()) {
						selectedDots.add(dot);
						selectedShapes.add(selectedShape);
						draw.drawLineFromBarToDot(selectedDots, selectedBars, selectedShapes);
					} else {
						Frame.jlabel.setText("Invalid dot");
					}
					return true;
				} else if (draw.checkBoundary(dot.getCoordinateX(), dot.getCoordinateY(), coordinateX, coordinateY,
						Constants.DOT_WIDTH, Constants.DOT_HEIGHT)) {
					alterConnection = dot;
					Frame.jlabel.setText("Dot ready to be changed please drag " + alterConnection.getDotName());
					return true;
				}
			}
		}
		return false;
	}

	public boolean isShapeClicked(int coordinateX, int coordinateY) {
		drawingArea = Frame.mapDrawingAreas.get(Frame.currentTab);
		List<Shape> listOfShapes = drawingArea.listOfShapes;
		int size = listOfShapes.size();
		for (int i = 0; i < size; i++) {
			Shape object = listOfShapes.get(i);
			if (!object.toString().contains("Line")
					&& draw.checkBoundary(object.getCoordinateX(), object.getCoordinateY(), coordinateX, coordinateY,
							Constants.BRACKET_WIDTH, Constants.BRACKET_HEIGHT)) {
				selectedShape = object;
				return true;
			}
		}
		return false;
	}

	public void mouseDragged(MouseEvent event) {
		int coordinateX = event.getX();
		int coordinateY = event.getY();
		if (activateDrag && selectedShape != null) {
			Map<String, Map<Line, Integer>> mapLinesToCordinates = selectedShape.getLinesDrawn();
			selectedShape.setPosition(coordinateX, coordinateY);
			if (mapLinesToCordinates != null) {
				for (String dotName : mapLinesToCordinates.keySet()) {
					Map<Line, Integer> mapLinesDrawn = mapLinesToCordinates.get(dotName);
					Position position = selectedShape.getUpdatedDotCordinates().get(dotName);
					if (position != null) {
						int updatedCoordinateX = position.getCoordinateX();
						int updatedCoordinateY = position.getCoordinateY();
						for (Line line : mapLinesDrawn.keySet()) {
							if (mapLinesDrawn.get(line) == 0) {
								line.setPosition(updatedCoordinateX, updatedCoordinateY, line.getCoordinateX2(),
										line.getCoordinateY2());
							} else {
								line.setPosition(line.getCoordinateX1(), line.getCoordinateY1(), updatedCoordinateX,
										updatedCoordinateY);
							}
						}
					}
				}
			}
			drawingArea.repaintOnDrag();
		}
		new DefaultShapes().checkForDeafultShapes();
	}

}
