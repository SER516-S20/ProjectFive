package listener;

import frame.Constants;
import frame.DrawingArea;
import frame.Frame;
import frame.Main;
import shape.DefaultShapes;
import shape.Shape;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Compile.java - a class to validate all the connections between shapes in the
 * playground or drawing area of each tab and output the answers in dialog box
 * as valid or invalid
 *
 * @author Nachiappan Lakshmanan
 * @version 1.0
 *
 */
public class Compile {

	Map<String, Integer> mapShapesToNumbers;
	List<String> shapeNames;
	Map<Integer, Boolean> end;
	List<Integer> begin;
	int grid[][];
	Stack<String> stack;
	boolean isEnd;
	Map<String, String> specialShapes;
	static String comments = "";

	public Compile() {
		specialShapes = new HashMap<>();
		specialShapes.put(Constants.FUNCTION_BLOCK_END_NAME, Constants.FUNCTION_BLOCK_END_CHAR);
		specialShapes.put(Constants.FUNCTION_BLOCK_BEGIN_NAME, Constants.FUNCTION_BLOCK_BEGIN_CHAR);
		specialShapes.put(Constants.IF_BLOCK_END_NAME, Constants.IF_BLOCK_END_CHAR);
		specialShapes.put(Constants.IF_BLOCK_BEGIN_NAME, Constants.IF_BLOCK_BEGIN_CHAR);
	}

	public void validate() {
		Map<Integer, DrawingArea> mapDrawingAreas = new HashMap<>();
		Map<String, String> mapComments = new HashMap<>();
		mapDrawingAreas = Frame.mapDrawingAreas;
		String result = Constants.VALID;
		boolean isResultFound = false;
		for (Integer key : mapDrawingAreas.keySet()) {
			comments = "";
			boolean isValid = true;
			if (!buildSparseMatrix(mapDrawingAreas.get(key))) {
				isValid = false;
			} else {
				if (!traverseSparseMatrix()) {
					isValid = false;
				} else {
					for (Integer k : end.keySet()) {
						if (!end.get(k)) {
							isValid = false;
							break;
						}
					}
				}
			}
			mapComments.put("Tab" + String.valueOf(key), comments);
			if (!isValid && !isResultFound) {
				result = Constants.INVALID;
				isResultFound = true;
			}
		}
		new InputDialog(Main.frame, Constants.DIALOG_BOX_INPUT, true,
				new Point(Constants.FIRST_INDEX, Constants.FIRST_INDEX), result + "   " + mapComments.toString());
	}

	public void depthFirstSearch(int start, Stack<String> stack) {
		for (int i = 0; i < grid[Constants.FIRST_INDEX].length; i++) {
			if (grid[start][i] == 1) {
				if (shapeNames.get(i).contains(Constants.FUNCTION_BLOCK_END_NAME)) {
					end.put(i, true);
					isEnd = true;
				}
				String key = shapeNames.get(i).split("@")[Constants.FIRST_INDEX].replace("shape.", "");
				if (specialShapes.containsKey(key)) {
					String peek = stack.peek();
					String toAdd = specialShapes.get(key);
					if (stack.size() > 0 && peek.equals(Constants.FUNCTION_BLOCK_BEGIN_CHAR)
							&& toAdd.equals(Constants.FUNCTION_BLOCK_END_CHAR)
							|| peek.equals(Constants.IF_BLOCK_BEGIN_CHAR)
									&& toAdd.equals(Constants.IF_BLOCK_END_CHAR)) {
						stack.pop();
					} else {
						stack.add(toAdd);
					}
				}
				if (!isEnd) {
					depthFirstSearch(i, stack);
				}
			}
		}
	}

	public boolean traverseSparseMatrix() {

		for (Integer start : begin) {
			stack = new Stack<>();
			stack.add(specialShapes.get(
					shapeNames.get(start).split(Constants.FOR_LOOP_CHAR)[Constants.FIRST_INDEX].replace("shape.", "")));
			System.out.println(shapeNames.get(start).split(Constants.FOR_LOOP_CHAR)[Constants.FIRST_INDEX]);
			isEnd = false;
			depthFirstSearch(start, stack);
			if (stack.size() > 0) {
				for (String each : stack) {
					if (each.equals(Constants.FUNCTION_BLOCK_BEGIN_CHAR)) {
						comments += Constants.FBEGIN_COMMENT;
					} else if (each.equals(Constants.FUNCTION_BLOCK_END_CHAR)) {
						comments += Constants.FEND_COMMENT;
					} else if (each.equals(Constants.IF_BLOCK_BEGIN_CHAR)) {
						comments += Constants.IFBEGIN_COMMENT;
					} else if (each.equals(Constants.IF_BLOCK_END_CHAR)) {
						comments += Constants.IFEND_COMMENT;
					}
				}
				return false;
			}
		}
		return true;
	}

	public boolean buildSparseMatrix(DrawingArea drawingArea) {
		mapShapesToNumbers = new HashMap<>();
		shapeNames = new ArrayList<>();
		begin = new ArrayList<>();
		end = new HashMap<>();
		List<Shape> listOfShapes = new DefaultShapes().removeDeafultShapesFromCompile(drawingArea.listOfShapes);
		int increment = 0;
		for (int i = 0; i < listOfShapes.size(); i++) {
			Shape shape = listOfShapes.get(i);
			String shapeName = shape.toString();
			if (!shapeName.contains(Constants.LINE_NAME) && !shapeName.contains(Constants.DOT_NAME)) {
				if (!shapeName.contains(Constants.FUNCTION_BLOCK_END_NAME) && shape.getConnectedShapes().size() == 0) {
					comments += "The following" + shapeName.split("@")[0]
							+ "is not connected to any other shape please connect it  ";
					return false;
				} else {
					if (shapeName.contains(Constants.FUNCTION_BLOCK_BEGIN_NAME)) {
						begin.add(increment);
					} else if (shapeName.contains(Constants.FUNCTION_BLOCK_END_NAME)) {
						end.put(increment, false);
					}
					mapShapesToNumbers.put(shapeName, increment);
					shapeNames.add(shapeName);
					System.out.println(shapeNames);
					increment = increment + 1;
				}
			}
		}
		grid = new int[mapShapesToNumbers.size()][mapShapesToNumbers.size()];
		for (int i = 0; i < listOfShapes.size(); i++) {
			Shape shape = listOfShapes.get(i);
			if (mapShapesToNumbers.containsKey(shape.toString())) {
				List<Shape> connectedShapes = shape.getConnectedShapes();
				int coordinateX = mapShapesToNumbers.get(shape.toString());
				for (int j = 0; j < connectedShapes.size(); j++) {
					if (mapShapesToNumbers.containsKey(connectedShapes.get(j).toString())) {
						int coordinateY = mapShapesToNumbers.get(connectedShapes.get(j).toString());
						grid[coordinateX][coordinateY] = 1;
					}
				}
			}
		}
		return true;
	}

}
