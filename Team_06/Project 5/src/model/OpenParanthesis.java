package model;

import javax.swing.JComponent;

/**
 *
 * @author Sheran
 * @since 04-24-2020
 * @Description: This class is a subclass of Symbol and handles drawing Open Parenthesis button.
 */
public class OpenParanthesis extends Symbol{
	
	public OpenParanthesis(JComponent panel, int x, int y) {
		super("(", panel, x, y);
		
		int width = this.getPreferredSize().width;
		int height = this.getPreferredSize().height;
		
		new Dot(width - 25, height / 2 - 7, this, false);
	}

}
