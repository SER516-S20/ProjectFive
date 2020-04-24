import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author srinivasan sundar
 * @since 01/26/2020
 * @version 1.0
 */
public class ShapePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int BUTTON_WIDTH = 150;
	private static final int BUTTON_HEIGHT = 100;
	static JButton functionBlockBeginButton, functionBlockEndButton, ifBlockBeginButton, ifBlockEndButton,
			forLoopButton, barShapeButton, valueHolderButton;

	ShapePanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(functionBlockBegin());
		this.add(functionBlockEnd());
		this.add(ifBlockBegin());
		this.add(ifBlockEnd());
		this.add(forLoop());
		this.add(valueHolder());
		this.add(barShape());
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

}
