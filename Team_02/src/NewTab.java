import java.awt.Panel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Kunal Sharma
 * @created 03-18-2020
 * @version 1.0
 * 
 * @author Suryadeep
 * @created 04-27-2020
 * @version 2.0
 */
public class NewTab extends JPanel {
	static List<String> nameList = new ArrayList<>();
	static Map<Integer, RightPanel> mapRightPanels = new HashMap<>();
	private static final long serialVersionUID = 1L;
	static int localCounter = 0;
	//static int currentTabIndx = 0;


	public void createAndShowGUI(String name) {

		try {
			MainWindow.tabbedPane.addTab(name, makePanel(name));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private static JPanel makePanel(String text) {
		RightPanel p = new RightPanel();
		mapRightPanels.put(localCounter, p);
		nameList.add(text);
		localCounter += 1;
		System.out.println("Adding panel" + mapRightPanels);
		p.setVisible(true);
		return p;
	}

	public void AddPanel(String tabName) {
		try {
			int count = MainWindow.tabbedPane.getTabCount() + 1;
			System.out.println("adding tab");
			MainWindow.tabbedPane.addTab(tabName, makePanel(tabName));
			System.out.println("added tab");
			count = MainWindow.tabbedPane.getTabCount();
			MainWindow.tabbedPane.setSelectedIndex(count - 1);
		} catch (Exception ex) {
			System.out.println("Exception while adding panel " + ex);
		}
	}
}
