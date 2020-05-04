package view;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.List;
import java.util.Observable;

import model.TabList;

/**
 * This is an abstract class where icons, dots and bars are declared.
 *@author Rishika Bera
 * @version 1.0
 */
public abstract class Icons extends Observable implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String description = "";

	private boolean isFirstConnection = false;
	
	protected List<Dot> dots;
	
	protected List<VerticalBar> bars; 

	public abstract void drawShape(Graphics graphic);

	public abstract boolean containsIcon(Point point);

	public abstract Point getLocation();

	public abstract void setLocation(Point point);

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFirstConnection() {
		return isFirstConnection;
	}

	public void setFirstConnection(boolean isFirstConnection) {
		this.isFirstConnection = isFirstConnection;
	}

	public List<Dot> getDots() {
		return dots;
	}

	public void setDots(List<Dot> dots) {
		this.dots = dots;
	}

	public List<VerticalBar> getBars() {
		return bars;
	}

	public void setBars(List<VerticalBar> bars) {
		this.bars = bars;
	}
	
	public void removeDots() {
		if (dots != null) {
			for (Dot dot : dots) {
				TabList.getInstance().getTab().getWorkspace().remove(dot);
			}
		}
		if (bars != null) {
			for (VerticalBar bar : bars) {
				TabList.getInstance().getTab().getWorkspace().remove(bar);
			}
		}
	}
	
}
