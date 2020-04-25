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

	public static void main(String[] args) {
		Menu objMenu = new Menu();
		Shapes shapes = new Shapes();
		frame = new JFrame("Main Window");
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.add(objMenu.CreateMenu());
		panel.setSize(100, 100);
		panel.setBounds(0, 0, 500, 50);
		JPanel panelToolKit = new JPanel();
		panelToolKit.setBounds(0, 50, 1350, 100);
		panelToolKit.setBackground(Color.red);
		panelToolKit.add(shapes.CreateShapes());
		frame.getContentPane().add(panel);
		frame.getContentPane().add(panelToolKit);
		obj = new NewTab();
		obj.createAndShowGUI();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
