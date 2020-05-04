package frame;

import java.awt.Color;
import java.awt.Font;

/**
 * frame.Constants.java - a class for all the constants value.
 * 
 * @author Chandan Yadav
 * @version 1.0
 * 
 */
public class Constants {

	public static final Color JBUTTON_COLOR = Color.GRAY;

	public static final int BRACKET_HEIGHT = 60;
	public static final int BRACKET_WIDTH = 130;
	
	public static final int ICON_DEVIATION_X = 65;
	public static final int ICON_DEVIATION_Y = 30;
	public static final Color BRACKET_COLOR = Color.LIGHT_GRAY;

	public static final int DOT_HEIGHT = 15;
	public static final int DOT_WIDTH = 15;
	public static final Color DOT_COLOR = Color.DARK_GRAY;
	public static final int DOT_SPAN = 10;

	public static final int BAR_HEIGHT = 20;
	public static final int BAR_WIDTH = 20;
	public static final Color BAR_COLOR = Color.DARK_GRAY;
	public static final int BAR_SPAN = 10;

	public static final String FUNCTION_BLOCK_BEGIN_CHAR = "(";
	public static final String FUNCTION_BLOCK_END_CHAR = ")";
	public static final String IF_BLOCK_BEGIN_CHAR = "<";
	public static final String IF_BLOCK_END_CHAR = ">";
	public static final String HASH_BLOCK_CHAR = "#";
	public static final String FOR_LOOP_CHAR = "@";
	public static final String VALUE_HOLDER_CHAR = "||";
	public static final String BAR_CHAR = "-";

	public static final String FUNCTION_BLOCK_BEGIN_NAME = "FunctionBlockBegin";
	public static final String FUNCTION_BLOCK_END_NAME = "FunctionBlockEnd";
	public static final String IF_BLOCK_BEGIN_NAME = "IfBlockBegin";
	public static final String IF_BLOCK_END_NAME = "IfBlockEnd";
	public static final String LINE_NAME = "Line";
	public static final String DOT_NAME = "Dot";

	public static final String VALID = "VALID";
	public static final String INVALID = "INVALID";
	public static final String TAB = "Tab";
	public static final int FIRST_INDEX = 0;

	public static final String DIALOG_BOX_INPUT = "Please close the dialog box after opening";

	public static final Color CHARACTER_COLOR = Color.BLACK;
	public static final String CHARACTER_FONT = Font.MONOSPACED;
	public static final int CHARACTER_STYLE = Font.BOLD;
	public static final int CHARACTER_SIZE = 30;
	public static final int CHARACTER_DEVIATION = 10;

	public static final String LINE_DRAWN_LABEL = "Line Drawn";

	public static final String TOP_LEFT = "topLeft";
	public static final String BOTTOM_LEFT = "bottomLeft";
	public static final String TOP_RIGHT = "topRight";
	public static final String BOTTOM_RIGHT = "bottomRight";
	public static final String CENTER_RIGHT = "centerRight";
	public static final String CENTER_LEFT = "centerLeft";

	public static final int X_DEVIATION = 65;
	public static final int Y_DEVIATION = 30;

	public static final String ESCAPE = "ESCAPE";
	public static final String ENTER = "Enter";

	public static final String FUNCTION_BLOCK_BEGIN_BUTTON = "functionBlockBeginButton";
	public static final String FUNCTION_BLOCK_END_BUTTON = "functionBlockEndButton";
	public static final String IF_BLOCK_BEGIN_BUTTON = "ifBlockBeginButton";

	public static final String IF_BLOCK_END_BUTTON = "ifBlockEndButton";
	public static final String FOR_LOOP_BUTTON = "forLoopButton";
	public static final String BAR_SHAPE_BUTTON = "barShapeButton";
	public static final String VALUE_HOLDER_BUTTON = "valueHolderButton";
	public static final String HASH_BLOCK_BUTTON = "hashBlockButton";

	public static final String NEW_SPACE = "New";
	public static final String LOAD = "Open";
	public static final String SAVE = "Save";
	public static final String COMPILE = "Compile";
	public static final String TRANSLATE = "Translate";
	
	
	public static final String FBEGIN_COMMENT = "The diagram does not have a function block end ";
	public static final String FEND_COMMENT = "The diagram does not have a function block begin";
	public static final String IFBEGIN_COMMENT = "The diagram does not have a If Block end";
	public static final String IFEND_COMMENT = "The diagram does not have a If Block Begin  ";

}
