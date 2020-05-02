import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * 
 * @author abhinaw sarang
 * @version 1.0
 * @since 05-01-2020
 */
public class Compiler {
	int tabId;
	String message = "";
	public void compilePanel(int key, RightPanel panelToCompile) {
		tabId = key;
		RightPanelDataProcessor rpDataProcessor = panelToCompile.panelMouseListener.dataProcessor;
		if (isShapesValid(rpDataProcessor.getIconMap())) {
			message = "All shapes are valid in tab: " + tabId;
			System.out.println("All shapes are valid !");
			JOptionPane.showMessageDialog(null, message);
		}
		List<Dot> allDots = new ArrayList<Dot>();
		for (Dot d : rpDataProcessor.getDotList()) {
			allDots.add(d);
		}
		for (Dot d : rpDataProcessor.getBarCenterList()) {
			allDots.add(d);
		}
		if (isConnectionsValid(rpDataProcessor.getLineList(), allDots)) {
			System.out.println("All connections are valid !");
			message = "All connections are valid in tab: " + tabId;
			JOptionPane.showMessageDialog(null, message);
		}

	}

	private boolean isShapesValid(HashMap<String, List<Icon>> iconMap) {
		try {
			
			if (iconMap.getOrDefault("openParanthesis", new ArrayList<Icon>()).size() != 1 || 
					iconMap.getOrDefault("closedParanthesis", new ArrayList<Icon>()).size() != 1) {
				System.out.println("Error: Open or close paranthesis not valid !");
				message = "Error: Open or close paranthesis not valid in tab: " + tabId;
				JOptionPane.showMessageDialog(null, message);
				return false;
			}
			if (iconMap.getOrDefault("lessThanOperator", new ArrayList<Icon>()).size() != 
					iconMap.getOrDefault("greaterThanOperator", new ArrayList<Icon>()).size()) {
				System.out.println("Error: Less than and greater than not in pair !");
				message = "Error: Less than and greater than not in pair in tab: " + tabId;
				JOptionPane.showMessageDialog(null, message);
				return false;
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	return true;
	}

	private boolean isConnectionsValid(List<Line> lineList, List<Dot> dotList) {
		if (!dotList.isEmpty() && lineList.isEmpty()) {
			System.out.println("Error: There are not connections !");
			message = "Error: There are not connections in pair in tab: " + tabId;
			JOptionPane.showMessageDialog(null, message);
			return false;
		}
		for (Dot eachDot : dotList) {
			boolean lineConnected = false;
			for (Line eachLine : lineList) {
				if (eachDot.equals(eachLine.getStartDot()) || eachDot.equals(eachLine.getEndDot())) {
					lineConnected = true;
					break;
				}
			}
			if (!lineConnected) {
				System.out.println("Error: Some dots or bars have no connections !");
				message = "Error: Some dots or bars have no connections in tab: " + tabId;
				JOptionPane.showMessageDialog(null, message);
				return false;
			}
		}
		return true;
	}
}
