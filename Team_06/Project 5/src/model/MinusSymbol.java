package model;

import javax.swing.JComponent;

/**
 *
 * @author Somesh
 * @since 04-23-2020
 * @Description: This class is a subclass of Symbol and handles drawing - button.
 */
public class MinusSymbol extends Symbol {

	public MinusSymbol(JComponent panel, int x, int y) {
		super("-", panel, x, y);
		
		int width = this.getPreferredSize().width;
		int height = this.getPreferredSize().height;
		
		new Dot(10, height / 2 - 7, this, true);
		new Dot(width - 25, height / 2 - 7, this, false);
	}
	
}
