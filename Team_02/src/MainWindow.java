import java.awt.Color;
import javax.swing.*;

/**
 * @author Kunal Sharma
 * @created on 01-27-2020
 * @version 1.0
 * @author Kunal Sharma
 * @created on 02-19-2020
 * @version 2.0
 */
public class MainWindow {
	static JPanel drawingBoardPanel = new JPanel();
	static JFrame frame;
	static JPanel p1;
	static NewTab obj;

	public static void CloseApplication() {
		try {
			System.exit(0);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void callAddButton() {
		new NewTab("button");
	}

	public static void main(String[] args) {
		Menu objMenu = new Menu();
		Shapes shapes = new Shapes();
		Shapes leftShapes = new Shapes();
		frame = new JFrame("Main Window");
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(objMenu.createMenu());
		
		JToolBar topToolBarToolKit = shapes.CreateShapes();
		topToolBarToolKit.setBounds(10, 0, 1350, 100);
		topToolBarToolKit.setBackground(Color.red);
		frame.getContentPane().add(topToolBarToolKit);
		
		JToolBar leftToolBarToolKit = leftShapes.CreateLeftShapes();
		leftToolBarToolKit.setBounds(10, 110,150, 700);
		leftToolBarToolKit.setBackground(Color.blue);
		frame.getContentPane().add(leftToolBarToolKit);
		
		new NewTab("main_window");
		//obj = new NewTab();
		//obj.createAndShowGUI();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
