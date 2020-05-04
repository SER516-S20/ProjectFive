package model;

import java.awt.Color;

import javax.swing.JButton;

import controller.Connector;

/**
 *
 * @author Suyog
 * @since 04-24-2020
 * @Description: Menu will show different options available to users.
 */
public class Bar extends Connector{

	public Bar(int x, int y, JButton symbol, boolean type) {
		super(x, y, symbol, type, "Bar");
		
		this.setBounds(x, y, 15, 60);
		this.setBackground(new Color(147, 184, 189));
	}
	
}