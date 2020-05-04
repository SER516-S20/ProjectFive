package controller;

import java.awt.Component;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import model.HashSymbol;
import model.Symbol;

/**
 *
 * @author Suyog
 * @since 04-28-2020
 * @Description: SaveApplication class stores all tab related data including symbol's properties in a .txt file. 
 */
public class SaveApplication implements java.io.Serializable{

	public SaveApplication(JFrame mainFrame) {
		super();
		
		JLabel fileName = new JLabel("no file selected");
		JFileChooser fileChooser = new JFileChooser(); 
		
		int r = fileChooser.showSaveDialog(null);
		
		if (r == JFileChooser.APPROVE_OPTION){ 
			try(FileWriter fw = new FileWriter(fileChooser.getSelectedFile()+".txt")) {
				
				JTabbedPane rp = WorkSpace.getInstance().getRightPanel();
				int index = 0;
				for(Component i : rp.getComponents()) {
					
					WorkPanel tab = (WorkPanel) i;
					String title = rp.getTitleAt(index);
					
					fw.write("Tab" + ";" + index + ";" + title + System.lineSeparator());
					index++;
				}
				
				index = 0;
				for(Component i : rp.getComponents()) {
					WorkPanel tab = (WorkPanel) i;
					
					for(Component j : tab.getComponents()) {
						Symbol symbol = (Symbol) j;
						
						String name = symbol.getText();
						String x = Integer.toString(symbol.getX());
						String y = Integer.toString(symbol.getY());
						String userInput = symbol.getUserInput();
						
						if(name == "#") {
							WorkPanel hashTab = ((HashSymbol) symbol).getTab();
							if(hashTab != null) {
								int hashTabIndex = rp.indexOfComponent(hashTab);
								fw.write("shape" + ";" + name + ";" + x + ";" + y + ";" + userInput + ";" + 
										 index + ";" +hashTabIndex + System.lineSeparator());
							}
							else {
								fw.write("shape" + ";" + name + ";" + x + ";" + y + ";" + userInput + ";" + index + 
										System.lineSeparator());
							}
						}
						else {
							fw.write("shape" + ";" + name + ";" + x + ";" + y + ";" + userInput + ";" + index + 
									System.lineSeparator());	
						}
					}
					index++;
				}
				String lines = getLines();
				fw.write(lines);
				
            	fw.close();
            }
            catch (IOException e1) {
            	e1.printStackTrace();
			}
        } 
        else
            fileName.setText("the user cancelled the operation"); 
	}
	
	private String getLines() {
		
		String lines = "";
		Map<WorkPanel, Map<Connector, ArrayList<Connector>>> tabLines = Collector.getInstance().getTabLines();
		
		for(WorkPanel tab : tabLines.keySet()) {
			Map<Connector, ArrayList<Connector>> line  = tabLines.get(tab);
			for(Connector c1 : line.keySet()) {
				
				int tabIndex = WorkSpace.getInstance().getRightPanel().indexOfComponent(tab);
				int pC1Index = getSymbolIndex((Symbol) c1.getParent(), tab);
				int c1Index = getConnectorIndex(c1,(Symbol) c1.getParent());
				
				lines += "line" + ";" + tabIndex + ";" + pC1Index + ";" + c1Index + ";";
				
				for(Connector c2 : line.get(c1)) {
					int pC2Index = getSymbolIndex((Symbol) c2.getParent(), tab);
					int c2Index = getConnectorIndex(c2,(Symbol) c2.getParent());
					
					lines += pC2Index + "-" + c2Index + ":";
				}
				lines += System.lineSeparator();
			}
		}
		return lines;
	}
	
	private int getSymbolIndex(Symbol symbol, WorkPanel tab) {
		int i = 0;
		for(Component s : tab.getComponents()) {
			if(symbol == (Symbol) s) {
				return i;
			}
			i++;
		}
		return -1;
		
	}
	
	private int getConnectorIndex(Connector c1, Symbol symbol) {
		int i = 0;
		for(Component c2 : symbol.getComponents()) {
			if(c1== (Connector) c2) {
				return i;
			}
			i++;
		}
		return -1;
		
	}
}
