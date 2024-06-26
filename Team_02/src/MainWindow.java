import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Kunal Sharma
 * @created on 01-27-2020
 * @version 1.0
 * @author Kunal Sharma
 * @created on 02-19-2020
 * @version 2.0
 */
public class MainWindow extends JFrame implements ChangeListener {
	static JPanel drawingBoardPanel = new JPanel();
	static JFrame frame;
	static JPanel p1;
	static NewTab obj;
	static JTabbedPane tabbedPane = new JTabbedPane();
	static int currentTabName;
	
	public MainWindow() {
		this.setSize(ShapeDimension.frameWidth, ShapeDimension.frameHeight);
		this.setTitle("Project Five - Team 2");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(true);
		Menu objMenu = new Menu();
		Shapes leftShapes = new Shapes();
		obj = new NewTab();
		obj.createAndShowGUI("start");
		Shapes shapes = new Shapes();
		this.getContentPane().add(leftShapes.CreateLeftShapes(), BorderLayout.WEST);
		this.add(tabbedPane, BorderLayout.CENTER);
		JMenuBar menuBar = objMenu.createMenu();
		this.setJMenuBar(menuBar);
		JToolBar toolBar = shapes.CreateShapes();
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		JScrollPane scrPane = new JScrollPane(tabbedPane);
		add(scrPane);
		scrPane.setVisible(true);
		tabbedPane.addChangeListener(this);
	}

	public static void CloseApplication() {
		try {
			System.exit(0);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (Menu.boolState != true){
			JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
			System.out.println(tabbedPane.getSelectedIndex());
			currentTabName = tabbedPane.getSelectedIndex();
			NewTab.mapRightPanels.get(currentTabName).customRepaint();	
		}
		else {
			Menu.boolState = false;	
		}
	}
}
