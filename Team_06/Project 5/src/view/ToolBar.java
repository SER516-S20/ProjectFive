package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

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
 * @author Sheran
 * @since 04-21-2020
 * @Description: Toolbar has all the 8 buttons to select and apply on the right panel.
 */
public class ToolBar extends JToolBar{
	// Gives the toolbars height and width.
	private int barWidth;
	private int barHeight;
	// This is the method for the class.
	public ToolBar(JFrame mainFrame) {
		super();
		
		barWidth = mainFrame.getPreferredSize().width;
		barHeight = mainFrame.getPreferredSize().height / 7;
		
		this.setName("Tool Bar");
		this.setLayout(new FlowLayout());
		this.setBackground(new Color(53, 181, 235));
		this.setPreferredSize(new Dimension(barWidth, barHeight));
		
		new OpenParanthesis(this, 0, 0);
		new CloseParanthesis(this, 0, 0);
		new LesserSymbol(this, 0, 0);
		new GreaterSymbol(this, 0, 0);
		new AtSymbol(this, 0, 0);
		new OrSymbol(this, 0, 0);
		new MinusSymbol(this, 0, 0);
		new HashSymbol(this, 0, 0);
		
		JScrollPane scrollPane = new JScrollPane(this);
		
		mainFrame.add(scrollPane,"North");
	}

}
