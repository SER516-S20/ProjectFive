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

	Compile() {
		specialShapes = new HashMap<>();
		specialShapes.put(Constants.FUNCTION_BLOCK_END_NAME, Constants.FUNCTION_BLOCK_END_CHAR);
		specialShapes.put(Constants.FUNCTION_BLOCK_BEGIN_NAME, Constants.FUNCTION_BLOCK_BEGIN_CHAR);
		specialShapes.put(Constants.IF_BLOCK_END_NAME, Constants.IF_BLOCK_END_CHAR);
		specialShapes.put(Constants.IF_BLOCK_BEGIN_NAME, Constants.IF_BLOCK_BEGIN_CHAR);
	}

	public void validate() {
		Map<String, String> mapCompiledAnswers = new HashMap<>();
		Map<Integer, DrawingArea> mapDrawingAreas = new HashMap<>();
		mapDrawingAreas = Frame.mapDrawingAreas;
		for (Integer key : mapDrawingAreas.keySet()) {
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
			if (isValid) {
				mapCompiledAnswers.put(Constants.TAB + String.valueOf(key), Constants.VALID);
			} else {
				mapCompiledAnswers.put(Constants.TAB + String.valueOf(key), Constants.INVALID);
			}
		}
		new InputDialog(Main.frame, Constants.DIALOG_BOX_INPUT, true,
				new Point(Constants.FIRST_INDEX, Constants.FIRST_INDEX), mapCompiledAnswers.toString());
	}

	public void depthFirstSearch(int start, Stack<String> stack) {
		for (int i = 0; i < grid[Constants.FIRST_INDEX].length; i++) {
			if (grid[start][i] == 1) {
				if (shapeNames.get(i).contains(Constants.FUNCTION_BLOCK_END_NAME)) {
					end.put(i, true);
					isEnd = true;
				}
				String key = shapeNames.get(i).split("@")[Constants.FIRST_INDEX];
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
			stack.add(specialShapes.get(shapeNames.get(start).split(Constants.FOR_LOOP_CHAR)[Constants.FIRST_INDEX]));
			isEnd = false;
			depthFirstSearch(start, stack);
			if (stack.size() > 0) {
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
		List<Shape> listOfShapes = drawingArea.listOfShapes;
		int increment = 0;
		for (int i = 0; i < listOfShapes.size(); i++) {
			Shape shape = listOfShapes.get(i);
			String shapeName = shape.toString();
			if (!shapeName.contains(Constants.LINE_NAME) && !shapeName.contains(Constants.DOT_NAME)) {
				if (!shapeName.contains(Constants.FUNCTION_BLOCK_END_NAME) && shape.getConnectedShapes().size() == 0) {
					return false;
				} else {
					if (shapeName.contains(Constants.FUNCTION_BLOCK_BEGIN_NAME)) {
						begin.add(increment);
					} else if (shapeName.contains(Constants.FUNCTION_BLOCK_END_NAME)) {
						end.put(increment, false);
					}
					mapShapesToNumbers.put(shapeName, increment);
					shapeNames.add(shapeName);
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
