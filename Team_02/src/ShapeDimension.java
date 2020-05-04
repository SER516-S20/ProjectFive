import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * @author abhinaw sarang
 * @created 05-04-2020
 * @version 1.0
 * @modified by Suryadeep
 * @created 05-04-2020
 * @version 2.0
 */
public class ShapeDimension {
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static int frameWidth = screenSize.width ;  
	public static int frameHeight = screenSize.height ;
	
	public static int upButtonWidth = (frameWidth - 120) / 8;
	public static int upButtonHeight = (frameHeight) / 10;
	
	public static int leftButtonWidth = (frameWidth) / 8;
	public static int leftButtonHeight = (frameHeight - upButtonHeight - 120)/ 8;
}
