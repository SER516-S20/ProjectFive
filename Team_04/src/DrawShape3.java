import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
/**
 * This class implements the drawing of shapes drag and drop within canvas.
 * 
 * @author Nikitha
 * @created on 03-11-2020
 * @updated on 04-26-2020
 * @version 1.0
 *
 */
public class DrawShape3 extends DrawShape implements MouseListener, MouseMotionListener {
	private int relativeX;
	private int relativeY;

	public DrawShape3(int posX, int posY, DrawingCanvas c) {
		super();
		operator = "<";
		positionX = posX;
		positionY = posY;
		canvas = c;
		this.setBounds(positionX, positionY, 200, 60);
		packOperator();
		addToConnector(new LinkageDot(this,"I"));
		addToConnector(new LinkageDot(this,"O"));
		addToConnector(new LinkageDot(this,"O"));
		addMouseListener(this);
		addMouseMotionListener(this);
	}


	@Override
	public void mousePressed(MouseEvent e) {
		relativeX = positionX - e.getX();
		relativeY = positionY - e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x1 = e.getX();
		int y1 = e.getY();
		setLocation(x1 + relativeX, y1 + relativeY);
		positionX = x1 + relativeX;
		positionY = y1 + relativeY;
		canvas.repaint();
	}

	public void deleteShape()
	{
		int i=0; 
		while(i<canvas.lineArray.size()) {
			if(canvas.lineArray.get(i)[0].shape.equals(this)||
					canvas.lineArray.get(i)[1].shape.equals(this)) {
				canvas.lineArray.get(i)[0].connected=false;
				canvas.lineArray.get(i)[1].connected=false;
				canvas.lineArray.get(i)[0].setBackground(Color.WHITE);
				canvas.lineArray.get(i)[1].setBackground(Color.WHITE);
				canvas.lineArray.remove(i);
			}
			else
				i++;
		}
		canvas.repaint();
		for(i=0; i<canvas.shapeObject.size(); i++) {
			if(canvas.shapeObject.get(i).equals(this))
				canvas.shapeObject.remove(i);
		}
		canvas.remove(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e))
			deleteShape();
		else {
			if(value==null) {
				value = JOptionPane.showInputDialog(null,
						 "What is your value?",
						 "Enter the value",
						 JOptionPane.QUESTION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null,value,"Shape value", 1 );
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}