import javax.swing.JPanel;
import javax.swing.JTabbedPane;
/**
 * @author Kunal Sharma
 * @created 03-18-2020
 * @version 1.0
 */
public class NewTab extends JPanel{

	private static final long serialVersionUID = 1L;
	public 	JTabbedPane tabbedPane;

	public void createAndShowGUI() {
		// Create and set up the window.
		//    final JFrame frame = new JFrame("Split Pane Example");
		// Display the window.
		////   frame.setSize(500, 300);
		//   frame.setVisible(true);
		//  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set grid layout for the frame
		// frame.getContentPane().setLayout(new GridLayout(1, 1));
		try {
			tabbedPane = new JTabbedPane();
			// tabbedPane.setLocation(0, 300);
			// tabbedPane.setSize(400,400);
			tabbedPane.setBounds(0,150,1350,550); 
			tabbedPane.addTab("Tab1", makePanel("This is tab 1"));
			// tabbedPane.addTab("Tab3", makePanel("This is tab 3"));
			// tabbedPane.setBounds(200,200,200,200);  
			// frame.getContentPane().add(tabbedPane);
			// MainWindow.drawingBoardPanel.add(tabbedPane);
			MainWindow.frame.getContentPane().add(tabbedPane);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}

	private static JPanel makePanel(String text) {
		JPanel p = new RightPanel();
		p.setVisible(true);
		//JPanel p = new JPanel();
		//p.add(new Label(text));
		p.setSize(1600,800);
		// p.setLayout(null);
		return p;
	}

	public void AddPanel() {
		try {
			//int componentCount = MainWindow.frame.getContentPane().getComponentCount();
			//JTabbedPane tabbedPane = (JTabbedPane) MainWindow.frame.getContentPane().getComponent(componentCount-1);
			//JTabbedPane tabbedPane = (JTabbedPane) MainWindow.frame.getc
			int count = tabbedPane.getTabCount()+1;
			tabbedPane.addTab("Tab"+count, makePanel("This is tab "+ count ));
			count = tabbedPane.getTabCount();
			//MainWindow.frame.getContentPane().add(tabbedPane);
			tabbedPane.setSelectedIndex(count-1);
			//tabbedPane.setVisible(true);
			//MainWindow.frame.revalidate();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}


