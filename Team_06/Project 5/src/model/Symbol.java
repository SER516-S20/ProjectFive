package model;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

import controller.DragAndDropListener;
import controller.SymbolActionListener;
import controller.SymbolMouseListener;

/**
 *
 * @author Rahul
 * @since 03-12-2020
 * @Description: The purpose of this class is to draw symbols for all its child classes .
 */
public class Symbol extends JButton{
	
	private int componentWidth =  180;
	private int componentHeight = 80;
	private int x;
	private int y;
	
	protected String userInput;
	
	public Symbol(String text, JComponent panel, int x, int y) {
		super(text);
		
		this.x = x;
		this.y = y;
		
		this.setLayout(null);
		this.setBackground(new Color(233, 206, 231));
		this.setPreferredSize(new Dimension(componentWidth,componentHeight));
		this.setMinimumSize(new Dimension(componentWidth,componentHeight));
		this.setBounds(x, y, componentWidth, componentHeight);
		this.setTransferHandler(new TransferHandler(text));	
		
		String name = panel.getName();
		
		if(name == "Right Panel") {
			new SymbolMouseListener(panel, this);
			this.setOpaque(false);
			this.setBorder(BorderFactory.createLineBorder(Color.black));
		}
		else if(name == "Left Panel")
			new DragAndDropListener(this);
		else if(name == "Tool Bar")
			new SymbolActionListener(this);
		panel.add(this);
	}

	public String getUserInput() {
		return userInput;
	}

	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}

}
