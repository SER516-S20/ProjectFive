package model;

import javax.swing.JComponent;

/**
 *
 * @author Somesh
 * @since 04-23-2020
 * @Description: Menu will show different options available to users.
 */
public class GreaterSymbol extends Symbol{

	public GreaterSymbol(JComponent panel, int x, int y) {
		super(">", panel, x, y);
		
		int width = this.getPreferredSize().width;
		int height = this.getPreferredSize().height;
		
		new Dot(10, 10, this, true);
		new Dot(10, height - 25, this, true);
		new Dot(width - 25, height / 2 - 7, this, false);
	}

}
