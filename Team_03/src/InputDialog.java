import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * InputDialog.java - a class for InputDialog pop up box
 * on double click value holder button and validate at a given
 * position
 * 
 * @author Ashutosh Dey
 * @version 1.0
 * 
 */
public class InputDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	static JTextField dialogText;

	public InputDialog(JFrame parent, String title, boolean modality,String text, Point displayPoint) {
		super(parent, title, modality);
		setLocation(displayPoint.x, displayPoint.y);
		JPanel dialogPanel = new JPanel();
		JButton buttonEnter = new JButton(Constants.ENTER);
		dialogText = new JTextField(text);
		buttonEnter.addActionListener(new ActionListenerEnter());
		dialogPanel.add(dialogText);
		dialogPanel.add(buttonEnter);
		getContentPane().add(dialogPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public InputDialog(JFrame parent, String title, boolean modality, Point displayPoint, String text) {
		super(parent, title, modality);
		setLocation(displayPoint.x, displayPoint.y);
		JPanel dialogPanel = new JPanel();
		dialogText = new JTextField();
		dialogText.setSize(50, 50);
		dialogPanel.add(dialogText);
		dialogText.setText(text);
		getContentPane().add(dialogPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public JRootPane createRootPane() {
		JRootPane rootPane = new JRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(Constants.ESCAPE);
		Action action = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		};
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(stroke, Constants.ESCAPE);
		rootPane.getActionMap().put(Constants.ESCAPE, action);
		return rootPane;
	}

}
