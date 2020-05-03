import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ToolBarContainer extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton []btns;
	private final int N = 8;
	private JToolBar btnHolder;
	public ToolBarContainer() {
		btns = new JButton[N];
		btnHolder = new JToolBar();
		btnHolder.setFloatable(false);
		btnHolder.setPreferredSize(new Dimension(800,25));
		for(int i = 0; i < btns.length; i++) {
			btns[i] = new JButton();
			btns[i].addActionListener(this);
		}
		btns[0].setText("(");
		btns[1].setText(")");
		btns[2].setText("<");
		btns[3].setText(">");
		btns[4].setText("@");
		btns[5].setText("||");
		btns[6].setText("-");
		btns[7].setText("#");
		for(int i = 0; i < btns.length; i++) {
			btns[i].setPreferredSize(new Dimension(btnHolder.getPreferredSize().width / 8,
												btnHolder.getPreferredSize().height));
			btnHolder.add(btns[i]);
		}
		//btnHolder.setBackground(Color.green);
		this.add(btnHolder);
		//this.setBackground(Color.RED);
		//this.setPreferredSize(new Dimension(800,50));
	}
	
	public void resizeToolBar(int width, int height) {
		btnHolder.setPreferredSize(new Dimension(width / 8, height));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String text  = e.getActionCommand();
		Box instance = Box.getInstance();
		instance.setText(text);
		
	}
}
