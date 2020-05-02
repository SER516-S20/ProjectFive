import java.awt.Panel;
import java.util.HashMap;
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
public class NewTab extends JPanel implements ChangeListener{
	
	static Map<Integer, RightPanel> mapRightPanels = new HashMap<>();
	private static final long serialVersionUID = 1L;
	public static JTabbedPane tabbedPane;
	static int localCounter = 0;
	static int currentTabIndx = 0;
	
	NewTab(String source){
		System.out.println(source);
		if (source.equals("main_window")){
			createAndShowGUI();
		}
		else {
			AddPanel();
		}
		tabbedPane.addChangeListener(this);
	}
	
	public void createAndShowGUI() {

		try {
			tabbedPane = new JTabbedPane();

			// tabbedPane.setLocation(0, 300);
			// tabbedPane.setSize(400,400);
			tabbedPane.setBounds(170,100,1200,700); 
			tabbedPane.addTab("Tab1", makePanel("This is tab 1"));
			MainWindow.frame.getContentPane().add(tabbedPane);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}

	private static JPanel makePanel(String text) {
		RightPanel p = new RightPanel();
		mapRightPanels.put(localCounter, p);
		localCounter += 1;
		System.out.println("Adding panel"+mapRightPanels);
		p.setVisible(true);
		p.setSize(1600,800);
		return p;
	}

	public void AddPanel() {
		try {
			int count = tabbedPane.getTabCount()+1;
			System.out.println("adding tab");
			tabbedPane.addTab("Tab"+count, makePanel("This is tab "+ count ));
			System.out.println("added tab");
			count = tabbedPane.getTabCount();
			tabbedPane.setSelectedIndex(count-1);
		}
		catch(Exception ex)
		{
			System.out.println("Exception while adding panel " +ex);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
		currentTabIndx = tabbedPane.getSelectedIndex();
		mapRightPanels.get(currentTabIndx).customRepaint();
	}
}


