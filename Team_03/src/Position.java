import java.io.Serializable;

/**
 * Position.java - a class for storing x,y cordintes of a shape
 * 
 * @author Chandan Yadav
 * @version 1.0
 * 
 */
public class Position implements Serializable {
	private static final long serialVersionUID = 1L;
	private int coordinateX;
	private int coordinateY;

	public int getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}

	public int getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}

}
