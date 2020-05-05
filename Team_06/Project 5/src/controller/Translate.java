package controller;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTabbedPane;

import model.Symbol;

/**
 *
 * @author Dhananjay
 * @since 04-26-2020
 * @Description: Translate will generate a code from the drawn diagram.
 */
public class Translate {

	public String translateCode() {
		
		JTabbedPane rp = WorkSpace.getInstance().getRightPanel();
		Map<WorkPanel, Map<Connector, ArrayList<Connector>>> tabLines = Collector.getInstance().getTabLines();
		int noOfTabS = rp.getComponents().length;
		String code = "digraph G { \n";
		String mainCode = "";
		String from = "start";
		String to = "end";
		for(int index = 0; index < noOfTabS; index++) { 
			String title = rp.getTitleAt(index);
			
			if (index % 2 == 0){
				code += "	subgraph cluster_"+title+"_"+index+"{\n"+
						"		style=filled;\r\n" + 
						"		color=lightgrey;\r\n" + 
						"		node [style=filled,color=white];\r\n" + 
						"		label = \"process "+title+"_"+index+"\";\r\n";
			}else {
				code += "	subgraph cluster_"+title+"_"+index+"{\n"+
						"		color=blue;\r\n" + 
						"		node [style=filled];\r\n" + 
						"		label = \"process "+title+"_"+index+"\";\r\n";
			}
			
			
			
			
			
			
			
			WorkPanel tab = (WorkPanel) rp.getComponentAt(index);
			Component[] symbols = tab.getComponents();
			
			int openParaVertex = getOpenParaVertex(symbols); 
			mainCode += "	"+from + " -> "+title +"_"+index + "_"+openParaVertex+ " ;\n";
			int closeParaVertex = getCloseParaVertex(symbols); 
			mainCode += "	"+title +"_"+index + "_"+closeParaVertex+ " -> "+to+" ;\n";
			Map<Connector, ArrayList<Connector>> currentTabLines = tabLines.get(tab);
			
			for (Map.Entry line : currentTabLines.entrySet()) {
				
				int fromSymbolIndex = getSymbolId(symbols, ((Connector) line.getKey()).getParent());
				ArrayList<Connector> connectorList = (ArrayList<Connector>) line.getValue(); 
				
				if(((Symbol) symbols[fromSymbolIndex]).getText().equals("#")) {
					int toSymbolIndex = getSymbolId(symbols, connectorList.get(0).getParent());
					to = title +"_"+index + "_"+toSymbolIndex;
					continue;
				}				
				
				int size = connectorList.size();
				for(int i = 0; i < size ; i++) {
					int toSymbolIndex = getSymbolId(symbols, connectorList.get(i).getParent());
					if(((Symbol) symbols[toSymbolIndex]).getText().equals("#")) {
						from = title +"_"+index + "_"+fromSymbolIndex;
					}else {
						code += "		"+title +"_"+index + "_"+fromSymbolIndex + " -> "+title +"_"+index + "_"+toSymbolIndex+";\r\n";
					}
					
				}			
	        }
			code += "	}\n";
			
		}
		
		code += mainCode;
		code += "	start [shape=Mdiamond];\r\n" + 
				"	end [shape=Msquare];\n}";
		
		return code;
	}
	
	private int getOpenParaVertex(Component[] symbols) {
		int size = symbols.length;
		for(int index = 0; index < size ; index++) {
			if (((Symbol) (symbols[index])).getText().equals("(")) {
				return index;
			}
		}
		return -1;
	}
	
	private int getCloseParaVertex(Component[] symbols) {
		int size = symbols.length;
		for(int index = 0; index < size ; index++) {
			if (((Symbol) (symbols[index])).getText().equals(")")) {
				return index;
			}
		}
		return -1;
	}


	private int getSymbolId(Component[] symbols, Component key) {
		int size = symbols.length;
		for(int index = 0; index < size ; index++) {
			if (key.equals(symbols[index])) {
				return index;
			}
		}
		return -1;
	}

}
