package listener;

import frame.Constants;
import frame.Frame;
import frame.Main;
import frame.ShapePanel;
import shape.*;
import java.util.List;

/**
 * This class is used to identify which shape has been selected by the user
 * 
 * @author ashwin srinivasan
 * @version 1.0
 * @since 01/29/2020
 */
public class JButtonActionListener {
	public static String shapeClicked = "None";

	public void addActionListener() {

		ShapePanel.functionBlockBeginButton.addActionListener(e -> {
			shapeClicked = Constants.FUNCTION_BLOCK_BEGIN_BUTTON;
		});
		ShapePanel.functionBlockEndButton.addActionListener(e -> {
			shapeClicked = Constants.FUNCTION_BLOCK_END_BUTTON;
		});
		ShapePanel.ifBlockBeginButton.addActionListener(e -> {
			shapeClicked = Constants.IF_BLOCK_BEGIN_BUTTON;
		});
		ShapePanel.ifBlockEndButton.addActionListener(e -> {
			shapeClicked = Constants.IF_BLOCK_END_BUTTON;
		});
		ShapePanel.forLoopButton.addActionListener(e -> {
			shapeClicked = Constants.FOR_LOOP_BUTTON;
		});
		ShapePanel.barShapeButton.addActionListener(e -> {
			shapeClicked = Constants.BAR_SHAPE_BUTTON;
		});
		ShapePanel.valueHolderButton.addActionListener(e -> {
			shapeClicked = Constants.VALUE_HOLDER_BUTTON;
		});
		ShapePanel.hashBlockButton.addActionListener(e -> {
			shapeClicked = Constants.HASH_BLOCK_BUTTON;
		});
	}

	public void addNewShapesToFrame(int coordinateX, int coordinateY) {
		FactoryShapes factoryShapes = new FactoryShapes();

		if (shapeClicked.equals(Constants.BAR_SHAPE_BUTTON)) {
			BarShape barShape = (BarShape) factoryShapes.createShape("bar");
			barShape.setPosition(coordinateX, coordinateY);
			Frame.mapDrawingAreas.get(Frame.currentTab).addBarShape(barShape);
		} else if (shapeClicked.equals(Constants.FOR_LOOP_BUTTON)) {
			ForLoop forLoop = (ForLoop) factoryShapes.createShape("for");
			forLoop.setPosition(coordinateX, coordinateY);
			Frame.mapDrawingAreas.get(Frame.currentTab).addForLoop(forLoop);
		} else if (shapeClicked.equals(Constants.FUNCTION_BLOCK_BEGIN_BUTTON)) {
			if (!ifAlreadyContainsFunctionBlocks(shapeClicked.toString())) {
				FunctionBlockBegin functionBlockBegin = (FunctionBlockBegin) factoryShapes.createShape("fbegin");
				functionBlockBegin.setPosition(coordinateX, coordinateY);
				Frame.mapDrawingAreas.get(Frame.currentTab).addFunctionBlockBegin(functionBlockBegin);
			}
		} else if (shapeClicked.equals(Constants.FUNCTION_BLOCK_END_BUTTON)) {
			if (!ifAlreadyContainsFunctionBlocks(shapeClicked.toString())) {
				FunctionBlockEnd functionBlockEnd = (FunctionBlockEnd) factoryShapes.createShape("fend");
				functionBlockEnd.setPosition(coordinateX, coordinateY);
				Frame.mapDrawingAreas.get(Frame.currentTab).addFunctionBlockEnd(functionBlockEnd);
			}
		} else if (shapeClicked.equals(Constants.IF_BLOCK_BEGIN_BUTTON)) {
			IfBlockBegin ifBlockBegin = (IfBlockBegin) factoryShapes.createShape("ifbegin");
			ifBlockBegin.setPosition(coordinateX, coordinateY);
			Frame.mapDrawingAreas.get(Frame.currentTab).addIfBlockBegin(ifBlockBegin);
		} else if (shapeClicked.equals(Constants.IF_BLOCK_END_BUTTON)) {
			IfBlockEnd ifBlockBegin = (IfBlockEnd) factoryShapes.createShape("ifend");
			ifBlockBegin.setPosition(coordinateX, coordinateY);
			Frame.mapDrawingAreas.get(Frame.currentTab).addIfBlockEnd(ifBlockBegin);
		} else if (shapeClicked.equals(Constants.VALUE_HOLDER_BUTTON)) {
			ValueHolderBlock ifBlockBegin = (ValueHolderBlock) factoryShapes.createShape("value");
			ifBlockBegin.setPosition(coordinateX, coordinateY);
			Frame.mapDrawingAreas.get(Frame.currentTab).addValueHolderBlock(ifBlockBegin);
		} else if (shapeClicked.equals(Constants.HASH_BLOCK_BUTTON)) {
			HashBlock ifBlockBegin = (HashBlock) factoryShapes.createShape("hash");
			ifBlockBegin.setPosition(coordinateX, coordinateY);
			Frame.mapDrawingAreas.get(Frame.currentTab).addHashBlock(ifBlockBegin);
			if (coordinateX != 0) {
				Frame.jlabel.setText("Created new Tab Please name the tab");
				Main.frame.createNewTab(ifBlockBegin);
			}
		} else {
			Frame.jlabel.setText("Please select a shape");
		}
	}

	public boolean ifAlreadyContainsFunctionBlocks(String shapeString) {
		List<Shape> lstOfShapes = new DefaultShapes()
				.removeDeafultShapesFromCompile(Frame.mapDrawingAreas.get(Frame.currentTab).listOfShapes);
		for (int i = 0; i < lstOfShapes.size(); i++) {
			Shape shape = lstOfShapes.get(i);
			if (shape.toString().contains("FunctionBlockBegin") && shapeString.contains("functionBlockBegin")) {
				return true;
			}
			if (shape.toString().contains("FunctionBlockEnd") && shapeString.contains("functionBlockEnd")) {
				return true;
			}
		}
		return false;

	}

}
