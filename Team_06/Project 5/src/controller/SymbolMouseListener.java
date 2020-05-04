package controller;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import model.Symbol;

/**
 *
 * @author Sheran
 * @since 04-27-2020
 * @Description: This class handles the mouse listeners for within the symbols like drag and drop and drawing lines and so on.
 */
public class SymbolMouseListener {
	
	private int screenX;
	private int screenY;
	private int mouseX;
	private int mouseY;
	private int xLeftBound;
	private int yTopBound;
	private int xRightBound;
	private int yBottomBound;

	public SymbolMouseListener(JComponent panel, Symbol symbol) {
		
		symbol.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			// This method is to draw a symbol, selected by the user, on the right panel on the location clicked by the user.
			@Override
			public void mousePressed(MouseEvent e) {
				screenX = e.getXOnScreen();
				screenY = e.getYOnScreen();
				mouseX = symbol.getX();
				mouseY = symbol.getY();
				xLeftBound = panel.getX() + panel.getParent().getX();
				yTopBound = panel.getY() + panel.getParent().getY();
				xRightBound = panel.getX() + panel.getPreferredSize().width - 200;
				yBottomBound = panel.getY() + panel.getPreferredSize().height - 30;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			// This method is to handle the input data dialog box and deletion of symbol.
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && symbol.getText() != "#") 
				    setResponse(symbol);	
				
				if(e.isShiftDown() && symbol.getText() != "#")
					deleteSymbol(symbol);
			}
		});;
		
		symbol.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {}
			// This method is to handle the drag and drop of symbols on the right panel.
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen() - screenX + mouseX;
				int y = e.getYOnScreen() - screenY + mouseY;
				
				setLocation(x, y, symbol, panel);
			}
		});
	}
	// This method is to add the data entered by the user when they double clicked the symbol.
	private void setResponse(Symbol symbol) {
		String prevResponse = symbol.getUserInput();
	    String response = (String) JOptionPane.showInputDialog(null,
	    		 "Data:",
	    		 "Enter User Input",
	    		 JOptionPane.QUESTION_MESSAGE, null,null, prevResponse);
	    
	    if (response == null)
	    	response = prevResponse;
	    
	    symbol.setUserInput(response);
	}
	// This method is to delete symbols from the right panel.
	private void deleteSymbol(Symbol symbol) {
		WorkPanel temp = (WorkPanel) symbol.getParent();
		Component[] connectors = symbol.getComponents();
		removeLines(connectors);
		
		String symbolName = symbol.getText();
		temp.remove(symbol);
		temp.repaint();
		
		switch(symbolName) {
			case "(": temp.setOpenP(false);
				break;
			case ")": temp.setCloseP(false);
				break;
		}
	}
	// This method is to delete lines from the right panel.
	private void removeLines(Component[] connectors) {
		for(Component i : connectors) {
			Connector connector = (Connector) i;
			Collector.getInstance().removeLine(connector);
		}
	}
	// This method gives the users click location to the other classes in different packages.
	private void setLocation(int x, int y, Symbol symbol, JComponent panel) {
		if(x > xLeftBound && y > yTopBound && x < xRightBound && y < yBottomBound)
			symbol.setLocation(x, y);
		else if(x < xLeftBound && y > yTopBound && y < yBottomBound )
			symbol.setLocation(panel.getX(), y);
		else if(x > xLeftBound && y < yTopBound && x < xRightBound)
			symbol.setLocation(x, panel.getY()-30);
		else if(x > xRightBound && y < yBottomBound && y > yTopBound)
			symbol.setLocation(panel.getPreferredSize().width - 200 , y);
		else if(x < xRightBound && y > yBottomBound && x > xLeftBound)
			symbol.setLocation(x, panel.getPreferredSize().height - 10);
		
		symbol.getParent().setComponentZOrder(symbol, 0);
		
		panel.repaint();
	}
}
