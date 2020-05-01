package View;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import Model.*;
import Controller.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to create the panel on the right side
 * where shapes will appear when clicked on buttons on left panel.
 * 
 * @author Sayali Tanawade
 * @version 1.0
 */
public class RightPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static List<Shapes> rightPanelShapes = new ArrayList<>();
	private static List<Connections> lines = new ArrayList<>();
	private static Shapes originShape;
	private static Shapes destShape;
	private static int originX, originY, destinationX, destinationY;
	private static boolean isSelected = false;
	private static boolean isMoved = false;

	public static List<Shapes> getRightPanelShapes() {
		return rightPanelShapes;
	}

	public static void setRightPanelShapes(List<Shapes> rightPanelShapes) {
		RightPanel.rightPanelShapes = rightPanelShapes;
	}

	public static List<Connections> getLines() {
		return lines;
	}

	public static void setLines(List<Connections> lines) {
		RightPanel.lines = lines;
	}

	public static Shapes getOriginShape() {
		return originShape;
	}

	public static void setOriginShape(Shapes originShape) {
		RightPanel.originShape = originShape;
	}

	public static Shapes getDestShape() {
		return destShape;
	}

	public static void setOriginX(int originX) {
		RightPanel.originX = originX;
	}

	public static void setOriginY(int originY) {
		RightPanel.originY = originY;
	}

	public static void setDestinationX(int destinationX) {
		RightPanel.destinationX = destinationX;
	}

	public static void setDestinationY(int destinationY) {
		RightPanel.destinationY = destinationY;
	}

	public static boolean isSelected() {
		return isSelected;
	}

	public static void setSelected(boolean isSelected) {
		RightPanel.isSelected = isSelected;
	}

	public static boolean isMoved() {
		return isMoved;
	}

	public static void setMoved(boolean isMoved) {
		RightPanel.isMoved = isMoved;
	}

	public RightPanel() {

		this.setBackground(Color.WHITE);
		RightPanelMouseListener rightPanelMouseListener = new RightPanelMouseListener();
		addMouseListener(rightPanelMouseListener);
		addMouseMotionListener(rightPanelMouseListener);
		
		Dot dot = new Dot();
		addMouseListener(dot);
		addMouseMotionListener(dot);
		
		setVisible(true);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		setBorder(blackline);
		setSize(400, 400);
		
	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		for (Shapes s : rightPanelShapes) {
			s.drawShape(graphics);
		}

		for (Connections l : lines) {
			Line2D shape = new Line2D.Double();
			shape.setLine(l.getSourceX(), l.getSourceY(), l.getDestX(), l.getDestY());
			Graphics2D g2 = (Graphics2D) graphics;
			g2.draw(shape);
		}

		if (isMoved) {
			Line2D shape = new Line2D.Double();
			shape.setLine(originX, originY, destinationX, destinationY);
			Graphics2D g2 = (Graphics2D) graphics;
			g2.draw(shape);
		}
	}

}
