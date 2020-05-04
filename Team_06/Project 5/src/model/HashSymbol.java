package model;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import controller.Connector;
import controller.Collector;
import controller.WorkPanel;
import controller.WorkSpace;

/**
 *
 * @author Dhananjay
 * @since 04-24-2020
 * @Description: This class is a subclass of Symbol and handles drawing # symbol button.
 */
public class HashSymbol extends Symbol {
	
	private WorkPanel tab;
	private boolean tabFlag;
	
	public HashSymbol(JComponent panel, int x, int y) {
		super("#", panel, x, y);
		
		this.tabFlag = false;
		
		int width = this.getPreferredSize().width;
		int height = this.getPreferredSize().height;
		
		new Dot(10, height / 2 - 7, this, true);
		new Dot(width - 25, height / 2 - 7, this, false);
		
		HashSymbol hashSymbol = this;
		
		String name = panel.getName();
		
		if(name == "Right Panel") {
			this.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {}
				
				@Override
				public void mousePressed(MouseEvent e) {}
				
				@Override
				public void mouseExited(MouseEvent e) {}
				
				@Override
				public void mouseEntered(MouseEvent e) {}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
					    String prevResponse = hashSymbol.getUserInput();
					    String response = (String) JOptionPane.showInputDialog(null,
					    		 "Name:",
					    		 "Enter User Input",
					    		 JOptionPane.QUESTION_MESSAGE, null,null, prevResponse);
					    
					    if (response == null)
					    	response = prevResponse;
					    
					    hashSymbol.setUserInput(response);
					    
					    if(!tabFlag) {
					    	 tab = WorkSpace.getInstance().addTab(response);
					    	 tabFlag = true;
					    }
					    else {
					    	 WorkSpace.getInstance().changeName(response, tab);
					    }
					}
					
					if(e.isShiftDown()) {
						WorkPanel workpanel = (WorkPanel) hashSymbol.getParent();
						WorkSpace.getInstance().deleteTab(tab);
						deleteSymbol(hashSymbol);	
					}
				}
			});
		}
	}
	
	private void deleteSymbol(Symbol symbol) {
		WorkPanel temp = (WorkPanel) symbol.getParent();
		Component[] connectors = symbol.getComponents();
		removeLines(connectors);

		temp.remove(symbol);
		temp.repaint();
		
	}

	
	private void removeLines(Component[] connectors) {
		for(Component i : connectors) {
			Connector connector = (Connector) i;
			Collector.getInstance().removeLine(connector);
		}
	}

	public WorkPanel getTab() {
		return tab;
	}

	public void setTab(WorkPanel tab) {
		this.tab = tab;
	}

	public boolean isTabFlag() {
		return tabFlag;
	}

	public void setTabFlag(boolean tabFlag) {
		this.tabFlag = tabFlag;
	}
}
