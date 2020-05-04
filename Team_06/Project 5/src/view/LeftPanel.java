package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.AtSymbol;
import model.CloseParanthesis;
import model.GreaterSymbol;
import model.HashSymbol;
import model.LesserSymbol;
import model.MinusSymbol;
import model.OpenParanthesis;
import model.OrSymbol;

/**
 *
 * @author Suyog
 * @since 04-22-2020
 * @Description: Left Panel will have different shapes that are available to draw work panel tabs.
 */
public class LeftPanel extends JPanel{

	int panelWidth;
	int panelHeight;
	
	public LeftPanel(JFrame mainFrame) {
		super();
		
		panelWidth = mainFrame.getPreferredSize().width / 6;
		panelHeight = mainFrame.getPreferredSize().height / 8 * 7;
		
		this.setName("Left Panel");
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(panelWidth, panelHeight));
		this.setBackground(new Color(192, 219, 223));
		
		new OpenParanthesis(this, 0, 0);
		new CloseParanthesis(this, 0, 0);
		new LesserSymbol(this, 0, 0);
		new GreaterSymbol(this, 0, 0);
		new AtSymbol(this, 0, 0);
		new OrSymbol(this, 0, 0);
		new MinusSymbol(this, 0, 0);
		new HashSymbol(this, 0, 0);
		
		JScrollPane scrollPane = new JScrollPane(this);
		
		mainFrame.add(scrollPane, BorderLayout.LINE_START);
	}
}
