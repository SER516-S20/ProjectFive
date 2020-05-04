package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

/**
 *
 * @author Somesh
 * @since 05-01-2020
 * @Description: The Drawline class adds listeners to connectors and when 2 connectors are clicked
 * it draws a line using paint component.
 */
public class DrawLine {

	public DrawLine(Connector c2) {
		
		c2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Collector data = Collector.getInstance();

				Connector c1 = data.getConnectorSelected();
				if(c1 != null && c1.getParent() != c2.getParent() && c1.isIoType() != c2.isIoType()) {
					
					if(c2.getType() == "Dot"  && c2.isLine()) {
						c2.setLine(false);
						data.removeLine(c2);
					}
					
					if(c1.getType() == "Dot" && c1.isLine()) {
						c1.setLine(false);
						data.removeLine(c1);
					}
					
					if(c1.isIoType()) {
						data.addTabLines((WorkPanel) c2.getParent().getParent(), c2, c1);
					}
					else {
						data.addTabLines((WorkPanel) c2.getParent().getParent(), c1, c2);
					}
					
					data.setConnectorSelected(null);
					c2.getParent().getParent().repaint();
					c2.setLine(true);
					c1.setLine(true);
				}
				else {
					if(c1 != null && c1.isIoType() == c2.isIoType() && c1 != c2 && 
							c1.getParent() != c2.getParent()) {
						JOptionPane.showMessageDialog(c2.getParent().getParent(), 
								"Two dots of same type(in or out) cannot be connected. "
								+ "Please select a different dot", "Error",0);
						data.setConnectorSelected(null);
					}
					else {
						data.setConnectorSelected(c2);
					}
				}
			}
		});
		
		// The listener is used to handle deleting line when mouse is clicked along with shift down.
		c2.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(c2.isLine() && e.isShiftDown()) {
					Collector.getInstance().removeLine(c2);
					c2.getParent().getParent().repaint();
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
	}
	
}
