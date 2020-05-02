import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * this class is to show the app
 * @author Hongqi Zhang
 */
public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String title = "ProjectTwo-Team 5";
	private static final Color lBackground = new Color(255, 255, 240);
	private RightTabbedPane dragArea;
	private LeftPanel btnContainer;
	 
	public Frame() {
		Model model = new Model();
		model.setFrame(this);
		this.setTitle(title);
		this.setMinimumSize(new Dimension(800, 500));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		dragArea = new RightTabbedPane();
		btnContainer = new LeftPanel();
		this.getContentPane().add(createLeftPanel());
		this.getContentPane().add(createRightPanel());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		MenuBar menuBar = new MenuBar(dragArea);
		this.setJMenuBar(menuBar.createMenuBar());
		this.setVisible(true);
	}
	
	private JScrollPane createLeftPanel() {
		btnContainer.setPreferredSize(new  Dimension(200, 600));
		btnContainer.setLocation(0, 0);
		btnContainer.setBackground(lBackground);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(btnContainer);
        scrollPane.setBounds(0, 0, 200, 480);
		return scrollPane;
	}
	
	private RightTabbedPane createRightPanel() {
		dragArea.setLocation(200, 0);
		dragArea.setSize(600, 500);
		dragArea.setBackground(Color.white);		
		return dragArea;
	}
	
	public void contentRepaint() {
		getContentPane().revalidate();
		getContentPane().repaint();	
	}
}
