package controller;

import javax.swing.JButton;

/**
 *
 * @author Sheran
 * @since 04-26-2020
 * @Description: This class is used to keep track of teh dots and bars and the connections made by the user.
 */
public class Connector extends JButton{
	// This line is true if line is drawn already through the dot or bar and false otherwise.
	private boolean line;
	private boolean io_Type;
	private String type;
	
	public Connector(int x, int y, JButton symbol, boolean io_Type, String type) {
		super();
		this.line = false;
		this.io_Type = io_Type;
		this.type = type;
		
		if(symbol.getParent().getName() == "Right Panel")
			new DrawLine(this);
		symbol.add(this);
	}
	//This method checks if line is drawn already.
	public boolean isLine() {
		return line;
	}
	//This method sets that line is drawn.
	public void setLine(boolean line) {
		this.line = line;
	}

	public boolean isIoType() {
		return io_Type;
	}

	public String getType() {
		return type;
	}
}
