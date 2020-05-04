package controller;



import javax.swing.JTabbedPane;

import model.Symbol;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Dhananjay
 * @since 04-26-2020
 * @Description:Compile will the check if all the rules are followed.
 */
public class Compile {
	public String checkPanel() {
		
		JTabbedPane rp = WorkSpace.getInstance().getRightPanel();
		Map<WorkPanel, Map<Connector, ArrayList<Connector>>> tabLines = Collector.getInstance().getTabLines();
		int noOfTabS = rp.getComponents().length;
		
		for(int index = 0; index < noOfTabS; index++) {
			
			String title = rp.getTitleAt(index);
			
			WorkPanel tab = (WorkPanel) rp.getComponentAt(index);
			
			if (!tab.isOpenP()) {
				return "Open Paranthesis missing on Tab # "+index;
			}
			if (!tab.isCloseP()) {
				return "Close Paranthesis missing on Tab # "+index;
			}
			
			String msg = checkAllConnections(tab);
			if (!msg.equals("Compiled Successfully")) {
				return msg+ " on Tab # "+index;
			}
			
			
			
			
			String checkTabMsg = checkTab(tab);
			if (!checkTabMsg.equals("Compiled Successfully")) {
				return checkTabMsg+ " on Tab # "+index;
			}
			
		}
		return "Compiled Successfully";
	}
	
	
	public String checkAllConnections(WorkPanel tab) {
		int noOfSymbols = tab.getComponents().length;	
		Graph graph = new Graph(noOfSymbols);	 
		
		boolean allConnected = true;
		int symbolIndex = 0;
		int lessSymbolCount = 0;
		int greatSymbolCount = 0;
		for(symbolIndex = 0; symbolIndex < noOfSymbols; symbolIndex++) {
			Symbol symbol = (Symbol) tab.getComponent(symbolIndex);
			
			if (symbol.getText().equals("<")) {
				lessSymbolCount++;
			}
			if (symbol.getText().equals(">")) {
				greatSymbolCount++;
			}
			
			int noOfConnectors = symbol.getComponents().length;
			int connectorIndex;
			for(connectorIndex = 0; connectorIndex < noOfConnectors; connectorIndex++) {
				Connector connector = (Connector) symbol.getComponent(connectorIndex);
				if (!connector.isLine()) {
					allConnected = false;
					return "Connector # " + connectorIndex +" of symbol # "+symbolIndex+" Not connected";
				}
			}
		}
		if (lessSymbolCount != greatSymbolCount) {
			return "# of open and close not equal";
		}
		return "Compiled Successfully";
	}
	
	
	public String checkTab(WorkPanel tab) {
		Component[] symbols = tab.getComponents();
		int noOfSymbols = tab.getComponents().length;	
		Graph graph = new Graph(noOfSymbols);
		Map<Connector, ArrayList<Connector>> tabLines = Collector.getInstance().getTabLines().get(tab);
		
		for (Map.Entry line : tabLines.entrySet()) {
			
			int fromSymbolIndex = getSymbolId(symbols, ((Connector) line.getKey()).getParent());
			
			ArrayList<Connector> connectorList = (ArrayList<Connector>) line.getValue(); 
			int size = connectorList.size();
			for(int index = 0; index < size ; index++) {
				int toSymbolIndex = getSymbolId(symbols, connectorList.get(index).getParent());
				graph.addEdge(fromSymbolIndex, toSymbolIndex);
			}			
        }
		
		int openParaVertex = getOpenParaVertex(symbols);
		if (openParaVertex == -1) {
			return "Something Went Wrong....";
		}
		
		ArrayList<Integer> atSymbolVertex = getAtSymbolVertex(symbols);
		for(Integer at : atSymbolVertex) {
			if (!graph.checkLoop(at)) {
		    	return "Compile Failed : \n Loop not present at  @";
		    } 
		}
		
		if (!graph.checkConnection(openParaVertex)) {
	    	return "Compile Failed : \nDisconnected circuit present";
	    }	
		
		return "Compiled Successfully";
	}

	private ArrayList<Integer> getAtSymbolVertex(Component[] symbols) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int size = symbols.length;
		for(int index = 0; index < size ; index++) {
			if (((Symbol) (symbols[index])).getText().equals("@")) {
				list.add(index);
			}
		}
		return list;
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
