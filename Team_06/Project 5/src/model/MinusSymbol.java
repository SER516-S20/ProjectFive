package model;

import javax.swing.JComponent;

/**
 *
 * @author Somesh
 * @since 03-08-2020
 * @Description: Menu will show different options available to users.
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
