package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.UIManager;

import model.HashSymbol;

/**
 *
 * @author Suyog
 * @since 04-26-2020
 * @Description: Collector is used to contain tab related data including each symbol's properties
 */
public class Collector {
	
	private static Collector dataObj;
	
	private String symbolSelected;
	
	private Connector connectorSelected;
	
	private Map<String, Class<?>> classNames;
	private Map<HashSymbol, WorkPanel> hashTabMap;
	private Map<WorkPanel, Map<Connector, ArrayList<Connector>>> tabLines;
	
	public static Collector getInstance() {
		
		if (dataObj == null) {
			dataObj = new Collector();
		}
		return dataObj;
	}
	
	public void initializeClassNames() {
		
		this.classNames = new HashMap<String, Class<?>>();
		this.hashTabMap = new HashMap<HashSymbol, WorkPanel>();
		this.tabLines = new HashMap<WorkPanel, Map<Connector,ArrayList<Connector>>>();
		this.connectorSelected = null;

		try {
			this.classNames.put("(", Class.forName("model.OpenParanthesis"));
			this.classNames.put(")", Class.forName("model.CloseParanthesis"));
			this.classNames.put("<", Class.forName("model.LesserSymbol"));
			this.classNames.put(">", Class.forName("model.GreaterSymbol"));
			this.classNames.put("@", Class.forName("model.AtSymbol"));
			this.classNames.put("||", Class.forName("model.OrSymbol"));
			this.classNames.put("-", Class.forName("model.MinusSymbol"));
			this.classNames.put("#", Class.forName("model.HashSymbol"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getSymbolSelected() {
		return symbolSelected;
	}

	public void setSymbolSelected(String symbolSelected) {
		this.symbolSelected = symbolSelected;
	}

	public Map<String, Class<?>> getClassNames() {
		return classNames;
	}
	
	public void addHashTab(HashSymbol hash, WorkPanel tab) {
		hashTabMap.put(hash, tab);
	}
	
	public void deleteHashTab(HashSymbol hash) {
		WorkSpace.getInstance().deleteTab(hashTabMap.get(hash));
		hashTabMap.remove(hash);
	}

	public Map<Connector, ArrayList<Connector>> getTabLines(WorkPanel w) {
		return tabLines.get(w);
	}

	public void addTabLines(WorkPanel w, Connector c1, Connector c2) {
		
		ArrayList<Connector> a = new ArrayList<Connector>();
		if(this.tabLines.containsKey(w)) {	
			if(c1.getType() == "Dot") {
				a.add(c2);
				this.tabLines.get(w).put(c1,a);
			}
			else {
				if(tabLines.get(w).containsKey(c1)) {
					this.tabLines.get(w).get(c1).add(c2);
				}		
				else {
					a.add(c2);
					this.tabLines.get(w).put(c1,a);
				}
			}
		}
		else {
			Map<Connector, ArrayList<Connector>> map = new HashMap<Connector, ArrayList<Connector>>();
			a.add(c2);
			map.put(c1, a);
			this.tabLines.put(w,map);
		}
		w.repaint();
	}

	public Connector getConnectorSelected() {
		return connectorSelected;
	}

	public void setConnectorSelected(Connector connectorSelected) {
		if(this.connectorSelected != null) {
			this.connectorSelected.setBorder(UIManager.getBorder("Button.border"));
		}
		this.connectorSelected = connectorSelected;
		if(this.connectorSelected != null) {
			this.connectorSelected.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
	}
	
	public void removeLine(Connector c) {
		WorkPanel workPanel = (WorkPanel) c.getParent().getParent();
		Map<Connector, ArrayList<Connector>> map = this.tabLines.get(workPanel);
		if (map == null){
			return; 
		}
		 
		if(map.containsKey(c)) {
			for(Connector c2 : map.get(c)) {
				if(c2.getType() == "Dot") {
					c2.setLine(false);
				}
				else {
					checkBarFalse(c2);
				}
			}
			map.remove(c);
			c.setLine(false);
		}
		else {
			Iterator<Connector> iterator = map.keySet().iterator(); 
			while(iterator.hasNext()){
				Connector c1 = iterator.next();
				if(map.get(c1) != null) {
					Iterator<Connector> iterator1 = map.get(c1).iterator(); 
					while(iterator1.hasNext()) {
						Connector c2 = iterator1.next();
						if(c == c2) {
							iterator1.remove();
						}
						if(map.get(c1).isEmpty()) {
							iterator.remove();
							c1.setLine(false);
						}
					}
				}
				
			}
		}
	}

	private void checkBarFalse(Connector c2) {
		WorkPanel workPanel = (WorkPanel) c2.getParent().getParent();
		Map<Connector, ArrayList<Connector>> map = this.tabLines.get(workPanel);
		if (map == null){
			return; 
		}
		Map<Connector, ArrayList<Connector>> Lines = Collector.getInstance().getTabLines(workPanel);
		int count = 0;
		for(Connector c1 : Lines.keySet()) {
			if(c1.equals(c2)) {
				count += Lines.get(c1).size();
			}else {
				for(Connector c : Lines.get(c1)) {		
					if(c.equals(c2)) {
						count ++;
					}
				}
			}
			
		}
		
		if (count <= 1) {
			c2.setLine(false);
		}else {
			c2.setLine(true);
		}
		
	}

	public Map<WorkPanel, Map<Connector, ArrayList<Connector>>> getTabLines() {
		return tabLines;
	}

	public void setTabLines(Map<WorkPanel, Map<Connector, ArrayList<Connector>>> tabLines) {
		this.tabLines = tabLines;
	}
	
	public void initialize() {
		this.hashTabMap.clear();
		this.tabLines.clear();
	}
}
