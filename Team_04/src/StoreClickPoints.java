import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
/**
 * 
 * @author Rohith Varma Gaddam
 * @since 03-14-2020
 * @updated on 04-1-2020
 *
 */
public class StoreClickPoints {
	public static int numPoints = 0;
	private static Point point1;
	private static Point point2;
	static Linkage link1=null;
	static Linkage link2=null;
	private static int index1;
	private static int index2;

	public static void StorePoints(Linkage link) {
		Linkage[] links = {link1,link2};
		System.out.print(link1.type);
		link.shape.canvas.lineArray.add(links);
		link.shape.canvas.repaint();
		link1.connected = true;
		link2.connected = true;
		link1 = null;
		link2 = null;
	}
	
}