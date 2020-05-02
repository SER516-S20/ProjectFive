import java.awt.Point;

/**
 * @author Yijian Hu
 * @modified by Kairui Hsu
 */
public class ShapeInfo {
	private int id;
	private String type, title;
	private Point position;
	
	ShapeInfo(int id, String type, String title, Point position) {
		this.id = id;
		this.type = type;
		this.title = title;
		this.position = position;
	}
	
	public int getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}
	
	public String toString()
	{
		return id + " " + type + " " + title + " " + position.toString();
	}
}
