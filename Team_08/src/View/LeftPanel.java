package View;

import java.awt.Graphics;

import java.util.ArrayList;
import java.util.List;

import Controller.*;
import Model.*;
import javax.swing.JPanel;

/**
 * This class is used to create the panel on the left side
 * which has different shapes.
 * 
 * @author Sayali Tanawade
 * @version 1.0
 */
public class LeftPanel extends JPanel {
	public static List<Shapes> leftPanelShapes = new ArrayList<>();
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		this.addMouseListener(new LeftPanelMouseListener());
	}
	
}
