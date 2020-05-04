package controller;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Somesh
 * @since 04-29-2020
 * @Description: WorkSpace creates a scrollable panel and can contain many WorkPanels.
 */
public class WorkSpace implements java.io.Serializable{

	private static WorkSpace dataObj;
	private int workSpaceWidth;
	private int workSpaceHeight;
	private JTabbedPane rightPanel;
	
	public static WorkSpace getInstance() {
			
		if (dataObj == null) {
			dataObj = new WorkSpace();
		}
		return dataObj;
	}
	
	public void createTabbedPane(JFrame mainFrame) {
		
		rightPanel = new JTabbedPane();
		workSpaceWidth = mainFrame.getPreferredSize().width / 6 * 5;
		workSpaceHeight = mainFrame.getPreferredSize().height / 8 * 7;
		
		rightPanel.setName("Tabbed Panel");
		rightPanel.setPreferredSize(new Dimension(workSpaceWidth,workSpaceHeight));
		rightPanel.setBackground(new Color(235, 235, 235));
		
		addTab("Main");
		
		JScrollPane scrollPane = new JScrollPane(rightPanel);
 		mainFrame.add(scrollPane);
	}
	
	public WorkPanel addTab(String name) {
		
		WorkPanel panel = new WorkPanel(workSpaceWidth, workSpaceHeight);
		
		rightPanel.addTab(name,panel);
		
		return panel;
	}
	
	public void changeName(String name, WorkPanel panel) {
		rightPanel.setTitleAt(rightPanel.indexOfComponent(panel), name);
	}
	
	public void deleteTab(WorkPanel panel) {
		rightPanel.remove(panel);
	}

	public JTabbedPane getRightPanel() {
		return rightPanel;
	}

	public void setRightPanel(JTabbedPane rightPanel) {
		this.rightPanel = rightPanel;
	}
	
	public void deleteAllTabs(JFrame frame) {
		rightPanel.removeAll();
		addTab("Main");
		Collector.getInstance().setSymbolSelected(null);
	}
}
