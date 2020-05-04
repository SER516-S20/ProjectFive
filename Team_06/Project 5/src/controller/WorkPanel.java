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
 * @Description: WorkPanel is the working area and represents a single tab. 
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
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		
		Lines =  Collector.getInstance().getTabLines(this.panel);
		
		if(Lines != null) {
			for(Connector c1 : Lines.keySet()) {
				for(Connector c2 : Lines.get(c1)) {
				
					int x1 = c1.getX() + c1.getParent().getX() + c1.getWidth()/2;
					int x2 = c2.getX() + c2.getParent().getX() + c2.getWidth()/2;
				
					int y1 = c1.getY() + c1.getParent().getY() + c1.getHeight()/2;
					int y2 = c2.getY() + c2.getParent().getY() + c2.getHeight()/2;
				
					g2D.drawLine(x1, y1, x2, y2);
				}
			}
		}
	}
}



