package frame;

import icon.BarShapeIcon;
import icon.FunctionBlockBeginIcon;
import icon.FunctionBlockEndIcon;
import icon.HashBlockIcon;
import icon.IfBlockBeginIcon;
import icon.IfBlockEndIcon;
import icon.ValueHolderBlockIcon;
import listener.JButtonActionListener;
import shape.ForLoopIcon;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ShapePanel.java - a class for shapes in the left panel
 * 
 * @author Chandan Yadav
 * @since 01/26/2020
 * @version 1.0
 */
public class ShapePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int BUTTON_WIDTH = 150;
	private static final int BUTTON_HEIGHT = 100;
	public static JButton functionBlockBeginButton;
	public static JButton functionBlockEndButton;
	public static JButton ifBlockBeginButton;
	public static JButton ifBlockEndButton;
	public static JButton forLoopButton;
	public static JButton barShapeButton;
	public static JButton valueHolderButton;
	public static JButton hashBlockButton;

	ShapePanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(functionBlockBegin());
		this.add(functionBlockEnd());
		this.add(ifBlockBegin());
		this.add(ifBlockEnd());
		this.add(forLoop());
		this.add(valueHolder());
		this.add(barShape());
		this.add(hashBlock());
		new JButtonActionListener().addActionListener();

	}

	private JButton createJButton() {
		JButton jButton = new JButton();
		jButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		jButton.setBackground(Constants.JBUTTON_COLOR);
		jButton.setOpaque(true);
		jButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		return jButton;
	}

	public JButton functionBlockBegin() {
		functionBlockBeginButton = createJButton();
		functionBlockBeginButton.setIcon(new FunctionBlockBeginIcon(functionBlockBeginButton));
		return functionBlockBeginButton;
	}

	public JButton functionBlockEnd() {
		functionBlockEndButton = createJButton();
		functionBlockEndButton.setIcon(new FunctionBlockEndIcon(functionBlockEndButton));
		return functionBlockEndButton;
	}

	public JButton ifBlockBegin() {
		ifBlockBeginButton = createJButton();
		ifBlockBeginButton.setIcon(new IfBlockBeginIcon(ifBlockBeginButton));
		return ifBlockBeginButton;
	}

	public JButton ifBlockEnd() {
		ifBlockEndButton = createJButton();
		ifBlockEndButton.setIcon(new IfBlockEndIcon(ifBlockEndButton));
		return ifBlockEndButton;
	}

	public JButton forLoop() {
		forLoopButton = createJButton();
		forLoopButton.setIcon(new ForLoopIcon(forLoopButton));
		return forLoopButton;
	}

	public JButton barShape() {
		barShapeButton = createJButton();
		barShapeButton.setIcon(new BarShapeIcon(barShapeButton));
		return barShapeButton;
	}

	public JButton valueHolder() {
		valueHolderButton = createJButton();
		valueHolderButton.setIcon(new ValueHolderBlockIcon(valueHolderButton));
		return valueHolderButton;
	}

	public JButton hashBlock() {
		hashBlockButton = createJButton();
		hashBlockButton.setIcon(new HashBlockIcon(hashBlockButton));
		return hashBlockButton;
	}

}
