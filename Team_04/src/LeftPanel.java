import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * @author Nikitha,Ashwath
 * @Since 1-26-2020
 * @updated 04-25-2020
 * @version 1.0
 */
public class LeftPanel extends JPanel implements ActionListener, MouseListener{
	final Dimension DIMENSION_PANEL_LEFT = new Dimension(150, 600);
	private JLabel shape1 = new JLabel("(");
	private JLabel shape2 = new JLabel(")");
	private JLabel shape3 = new JLabel("<");
	private JLabel shape4 = new JLabel(">");
	private JLabel shape5 = new JLabel("@");
	private JLabel shape6 = new JLabel("||");
	private JLabel shape7 = new JLabel("-");
	private JLabel shape8 = new JLabel("#");
	public LeftPanel() {
		setPreferredSize(DIMENSION_PANEL_LEFT);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		setBorder(blackline);
		setLayout(new GridLayout(8, 1));
		addLabels(shape1);
		shape1.addMouseListener(this);
		addLabels(shape2);
		shape2.addMouseListener(this);
		addLabels(shape3);
		shape3.addMouseListener(this);
		addLabels(shape4);
		shape4.addMouseListener(this);
		addLabels(shape5);
		shape5.addMouseListener(this);
		addLabels(shape6);
		shape6.addMouseListener(this);
		addLabels(shape7);
		shape7.addMouseListener(this);
		addLabels(shape8);
		shape8.addMouseListener(this);
	}
	public void addLabels(JLabel label) {
        label.setFont(new Font("TimesRoman", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.decode("#800000"));// Maroon
		Border blackline = BorderFactory.createLineBorder(Color.black,3);
		label.setBorder(blackline);
        this.setBackground(Color.decode("#FFD700"));// Gold
        this.add(label);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int index = Frame.tabbedPane.getSelectedIndex();
		ArrayList<DrawingCanvas> canvasArray = Frame.canvasArray;
		DrawingCanvas	canvas = canvasArray.get(index);
		if (e.getSource() == shape1) {
			canvas.addShapes(e.getXOnScreen(), e.getYOnScreen(), 1);
		} else if (e.getSource() == shape2) {
			canvas.addShapes(e.getXOnScreen(), e.getYOnScreen(), 2);
		} else if (e.getSource() == shape3) {
			canvas.addShapes(e.getXOnScreen(), e.getYOnScreen(), 3);
		} else if (e.getSource() == shape4) {
			canvas.addShapes(e.getXOnScreen(), e.getYOnScreen(), 4);
		} else if (e.getSource() == shape5) {
			canvas.addShapes(e.getXOnScreen(), e.getYOnScreen(), 5);
		} else if (e.getSource() == shape6) {
			canvas.addShapes(e.getXOnScreen(), e.getYOnScreen(), 6);
		} else if (e.getSource() == shape7) {
			canvas.addShapes(e.getXOnScreen(), e.getYOnScreen(), 7);
		} else if (e.getSource() == shape8) {
			canvas.addShapes(e.getXOnScreen(), e.getYOnScreen(), 8);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
