package model;

import javax.swing.JComponent;

/**
 *
 * @author Sheran
 * @since 04-22-2020
 * @Description: This class is a subclass of Symbol and handles drawing Closed Parenthesis button.
 */
public class CloseParanthesis extends Symbol{

	public CloseParanthesis(JComponent panel, int x, int y) {
		super(")", panel, x, y);
		
		int width = this.getPreferredSize().width;
		int height = this.getPreferredSize().height;
		
		new Dot(10, height / 2 - 7, this, true);
	}

}
