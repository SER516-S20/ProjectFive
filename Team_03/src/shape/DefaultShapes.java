package shape;

import frame.Constants;
import frame.Frame;
import listener.JButtonActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultShapes {
	static Map<Integer, String> mapDefaultShapeCordinates = new HashMap<>();

	String shapes[] = new String[] { Constants.FUNCTION_BLOCK_BEGIN_BUTTON, Constants.FUNCTION_BLOCK_END_BUTTON,
			Constants.IF_BLOCK_BEGIN_BUTTON, Constants.IF_BLOCK_END_BUTTON, Constants.FOR_LOOP_BUTTON,
			Constants.BAR_SHAPE_BUTTON, Constants.VALUE_HOLDER_BUTTON, Constants.HASH_BLOCK_BUTTON };


	public void addDefaultShapes() {
		JButtonActionListener jButtonActionListener = new JButtonActionListener();
		int coordinateX = 0;
		int coordinateY = 0;
	for (int i = 0; i < shapes.length; i++) {
			JButtonActionListener.shapeClicked = shapes[i];
			jButtonActionListener.addNewShapesToFrame(coordinateX, coordinateY);
			mapDefaultShapeCordinates.put(coordinateY, shapes[i]);
			coordinateY += 70;
		}
	}

	public List<Shape> removeDeafultShapesFromCompile(List<Shape> shapes) {
		List<Shape> refinedList = new ArrayList<>();
		for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i).getCoordinateX() > 100 || shapes.get(i).toString().contains("Line")) {
				refinedList.add(shapes.get(i));
			}
		}
		return refinedList;
	}

	public void checkForDeafultShapes() {
		JButtonActionListener jButtonActionListener = new JButtonActionListener();
		List<Shape> lstOfShapes = Frame.mapDrawingAreas.get(Frame.currentTab).listOfShapes;
		List<Integer> existingShapesYCordinates = new ArrayList<>();
		for (int i = 0; i < lstOfShapes.size(); i++) {
			Shape shape = lstOfShapes.get(i);
			if (shape.getCoordinateX() == 0  && !shape.toString().contains("Line")) {
				existingShapesYCordinates.add(shape.getCoordinateY());
			}
		}
		for (Integer y : mapDefaultShapeCordinates.keySet()) {

			if (!existingShapesYCordinates.contains(y)) {
				JButtonActionListener.shapeClicked = mapDefaultShapeCordinates.get(y);
				jButtonActionListener.addNewShapesToFrame(0, y);
			
			}
		}
	}

}
