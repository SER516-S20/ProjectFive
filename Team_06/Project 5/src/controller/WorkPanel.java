package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JPanel;

/**
 *
 * @author Somesh
 * @since 04-28-2020
 * @Description: Menu will show different options available to users.
 */
public class WorkPanel extends JPanel{
	
	private boolean isOpenP;
	private boolean isCloseP;
	
	private int panelWidth;
	private int panelHeight;
	
	private Map<Connector, ArrayList<Connector>> Lines;
	
	private WorkPanel panel;
	
	public WorkPanel(int width, int height) {
		
		this.isOpenP = false;
		this.isCloseP = false;
		this.panelWidth = width;
		this.panelHeight = height-100;
		
		this.setName("Right Panel");
		this.setLayout(null);
		this.setPreferredSize(new Dimension(panelWidth,panelHeight));
		this.setBackground(new Color(235, 235, 235));
		this.panel = this;
		
		new AddShapeListener(this);
	}

	public boolean isOpenP() {
		return isOpenP;
	}
	
	public void setOpenP(boolean isOpenP) {
		this.isOpenP = isOpenP;
	}

	public boolean isCloseP() {
		return isCloseP;
	}

	public void setCloseP(boolean isCloseP) {
		this.isCloseP = isCloseP;
	}
}



