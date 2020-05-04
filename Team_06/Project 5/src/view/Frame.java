package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controller.Collector;
import controller.WorkSpace;

/**
 * 
 * @author Somesh
 * @since 04-22-2020
 * @Description: Frame is the main application window which is basically divided into
 * left and right panel.
 */

public class Frame extends JFrame {
	
	private int frameWidth;
	private int frameHeight;
	public Frame(String title) {
		super(title);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameWidth = screenSize.width;
		frameHeight = screenSize.height / 10 * (17/2);
		
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(frameWidth / 2, frameHeight / 2));
		this.setPreferredSize(new Dimension(frameWidth, frameHeight));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Collector.getInstance().initializeClassNames();
		
		new MenuBar(this);
		new ToolBar(this);
		new LeftPanel(this);
		
		WorkSpace.getInstance().createTabbedPane(this);
		
		this.setVisible(true);

	}
	
	public static void main(String args[]) {
		new Frame("Project 5");
	}
}
