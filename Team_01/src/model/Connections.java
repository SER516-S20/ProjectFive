package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import view.Icons;

/**
 * This class has line information that requires source and destination end
 * point
 * 
 * @author Parikshith Kedilaya Mallar
 * @version 2.0
 */

public class Connections implements Serializable {

	private static final long serialVersionUID = 1L;
	private Point originPoint, destPoint;
	private List<Icons> icons;

	public Connections() {
		icons = new ArrayList<Icons>();
		icons.add(null);
		icons.add(null);
	}

	public Icons getOriginIcon() {
		return icons.get(0);
	}

	public void setOriginIcon(Icons originShape) {
		this.icons.set(0, originShape);
	}

	public Icons getDestIcon() {
		return icons.get(1);
	}

	public void setDestIcon(Icons destShape) {
		this.icons.set(1, destShape);
	}

	public Point getOriginPoint() {
		return originPoint;
	}

	public void setOriginPoint(Point originPoint) {
		this.originPoint = originPoint;
	}

	public Point getDestPoint() {
		return destPoint;
	}

	public void setDestPoint(Point destPoint) {
		this.destPoint = destPoint;
	}

}
