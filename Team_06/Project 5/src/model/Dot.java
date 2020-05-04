package model;

import java.awt.Color;

import javax.swing.JButton;

import controller.Connector;

/**
 *
 * @author Sheran
 * @since 04-23-2020
 * @Description: This class inherits connector from controller class and is used to paint the dots.
 */
public class Dot extends Connector{

	public Dot(int x, int y, JButton symbol, boolean type) {
		super(x, y, symbol, type, "Dot");
		
		this.setBounds(x, y, 15, 15);
		this.setBackground(new Color(147, 184, 189));
	}

}
