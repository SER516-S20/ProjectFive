import java.awt.Point;
/**
 * @author Hongqi Zhang
 */
public class Box {
	private static Box instance = null;
	private String text;
	private Point startPos;
	private Point endPos;
	private Connection conn;

	private Box() {
		text = null;
		startPos = null;
		endPos = null;
		conn = null;
	}
	public static Box getInstance() {
		if(instance == null) {
			instance = new Box();
		}
		return instance;
	}
	public Point getStartPos() {
		return startPos;
	}
	public void setStartPos(Point startPos) {
		this.startPos = startPos;
	}
	public Point getEndPos() {
		return endPos;
	}
	public void setEndPos(Point endPos) {
		this.endPos = endPos;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
