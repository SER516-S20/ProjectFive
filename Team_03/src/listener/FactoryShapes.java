package listener;

import shape.BarShape;
import shape.ForLoop;
import shape.FunctionBlockBegin;
import shape.FunctionBlockEnd;
import shape.HashBlock;
import shape.IfBlockBegin;
import shape.IfBlockEnd;
import shape.Shape;
import shape.ValueHolderBlock;

/**
 * FactoryShapes.java a factory class for creating shapes
 * 
 * @author Ashutosh Dey
 * @version 1.0
 * @since 01/29/2020
 */
public class FactoryShapes {
	public Shape createShape(String shapeName) {
		Shape shape = null;
		if (shapeName.equals("bar")) {
			shape = new BarShape();
		} else if (shapeName.equals("for")) {
			shape = new ForLoop();
		} else if (shapeName.equals("fbegin")) {
			shape = new FunctionBlockBegin();
		} else if (shapeName.equals("fend")) {
			shape = new FunctionBlockEnd();
		} else if (shapeName.equals("ifbegin")) {
			shape = new IfBlockBegin();
		} else if (shapeName.equals("ifend")) {
			shape = new IfBlockEnd();
		} else if (shapeName.equals("hash")) {
			shape = new HashBlock();
		} else if (shapeName.equals("value")) {
			shape = new ValueHolderBlock();
		}
		return shape;
	}
}
