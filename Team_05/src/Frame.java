import java.awt.BorderLayout;
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
 * @modified by ShihYu Chang
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
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dragArea = new RightTabbedPane();
		btnContainer = new LeftPanel(dragArea);
		this.getContentPane().add(new ToolBarContainer(), BorderLayout.NORTH);
		this.getContentPane().add(createLeftPanel(), BorderLayout.WEST);
		this.getContentPane().add(createRightPanel(),BorderLayout.CENTER);
		this.pack();
		this.setLocationRelativeTo(null);
		MenuBar menuBar = new MenuBar(dragArea);
		this.setJMenuBar(menuBar.createMenuBar());
		this.setVisible(true);
	}
	
	private JScrollPane createLeftPanel() {
		btnContainer.setPreferredSize(new  Dimension(200, 600));
		btnContainer.setBackground(lBackground);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(btnContainer);
		scrollPane.setPreferredSize(new Dimension(200,300));
		return scrollPane;
	}
	
	private JScrollPane createRightPanel() {
		dragArea.setPreferredSize(new  Dimension(1000, 1000));
		dragArea.setBackground(Color.white);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(dragArea);
		scrollPane.setPreferredSize(new Dimension(500,600));
		return scrollPane;
	}
	
	public void contentRepaint() {
		getContentPane().revalidate();
		getContentPane().repaint();	
	}
}
