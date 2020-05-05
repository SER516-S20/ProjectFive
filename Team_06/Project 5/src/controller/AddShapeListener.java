package controller;

import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

import model.HashSymbol;

/**
 *
 * @author Sheran
 * @since 04-25-2020
 * @Description: This class handles the mouse listeners for the selections made by the user on the toolbar.
 */
public class AddShapeListener {
	
	private Map<String, Class<?>> classNames;
	
	public AddShapeListener(WorkPanel panel) {
		
		this.classNames = Collector.getInstance().getClassNames();

		panel.setTransferHandler(new ValueImportTransferHandler());
		
		panel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			//This method handles the button selected from toolbar.
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					boolean symbolPresent = false;
					String symbolName = Collector.getInstance().getSymbolSelected();
					
					switch(symbolName) {
						case "(": 
							if(panel.isOpenP())
								symbolPresent = true;
							else
								panel.setOpenP(true);
							break;
						
						case ")":
							if(panel.isCloseP())
								symbolPresent = true;
							else
								panel.setCloseP(true);
							break;
						default: symbolPresent = false;
					}
					
					if(!symbolPresent) {
						JButton symbol = (JButton) classNames.get(symbolName).getDeclaredConstructor(JComponent.class, 
								int.class, int.class).newInstance(panel,e.getX(),e.getY());
						panel.setComponentZOrder(symbol, 0);
						panel.repaint();
						Collector.getInstance().setConnectorSelected(null);
						if(symbolName.equals("#")) {
							((HashSymbol) symbol).setTab(WorkSpace.getInstance().addTab("Tab_#"));
							((HashSymbol) symbol).setTabFlag(true);
							((HashSymbol) symbol).setUserInput("Tab_#");							
						}
					}
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException 
						| InvocationTargetException | NoSuchMethodException | SecurityException 
						| NullPointerException e1) {}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
	}
	// This static class is to send the selection of the user through to other packages.
	public static class ValueImportTransferHandler extends TransferHandler {

        public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;

        public ValueImportTransferHandler() {
        }

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(SUPPORTED_DATE_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            boolean accept = false;
            Map<String, Class<?>> classNames = Collector.getInstance().getClassNames();
            if (canImport(support)) {
                try {
                    Transferable t = support.getTransferable();
                    Object value = t.getTransferData(SUPPORTED_DATE_FLAVOR);
                    if (value instanceof String) {
                        Component component = support.getComponent();
                        if (component instanceof WorkPanel) {
                        	
                        	WorkPanel panel = (WorkPanel) component;
                        	boolean symbolPresent = false;
                        	
                        	switch(value.toString()) {
    							case "(": 
    								if(panel.isOpenP())
    									symbolPresent = true;
    								else
    									panel.setOpenP(true);
    								break;
    						
    							case ")":
    								if(panel.isCloseP())
    									symbolPresent = true;
    								else
    									panel.setCloseP(true);
    								break;
    							default: symbolPresent = false;
                        	}
                        	if(!symbolPresent) {
                        		Point mousePosition = MouseInfo.getPointerInfo().getLocation();
                        		Point panelPosition = panel.getLocationOnScreen();
                        		int x = mousePosition.x - panelPosition.x;
                        		int y = mousePosition.y - panelPosition.y;
                        		JButton symbol = (JButton) classNames.get(value.toString()).getDeclaredConstructor(JComponent.class, 
            							int.class, int.class).newInstance(panel,x,y);
                                panel.repaint();
                                Collector.getInstance().setConnectorSelected(null);
                                if(value.toString().equals("#")) {
        							((HashSymbol) symbol).setTab(WorkSpace.getInstance().addTab("Tab_#"));
        							((HashSymbol) symbol).setTabFlag(true);
        							((HashSymbol) symbol).setUserInput("Tab_#");							
        						}
                                accept = true;
        					}
    					}
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            return accept;
        }
    }
}
