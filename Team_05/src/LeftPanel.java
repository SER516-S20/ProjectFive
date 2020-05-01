import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Yijian Hu
 * @modified by Hongqi Zhang
 */
public class LeftPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private final int N = 7;
	private JButton []btns;
	
	public LeftPanel() {
		initButton();
	}
	
	public void initButton() {
		btns = new JButton[N];
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
		for(int i = 0; i < btns.length; i++) {
			this.add(btns[i]);
		}
		this.setLayout(new GridLayout(N + 1, 1, 0, 10));	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text  = e.getActionCommand();
		Box instance = Box.getInstance();
		instance.setText(text);
	}
}