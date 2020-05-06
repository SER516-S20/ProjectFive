import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

/**
 * @author Yijian Hu
 * @modified by Hongqi Zhang
 */
public class LeftPanel extends JPanel implements ActionListener, MouseMotionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	private final int N = 8;
	private JButton []btns;
	private RightTabbedPane workArea;
	public LeftPanel(RightTabbedPane workArea) {
		this.workArea = workArea;
		initButton();
	}
	
	public void initButton() {
		btns = new JButton[N];
		for(int i = 0; i < btns.length; i++) {
			btns[i] = new JButton();
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
			btns[i].addMouseMotionListener(this);
			btns[i].addMouseListener(this);
			this.add(btns[i]);
		}
		this.setLayout(new GridLayout(N + 1, 1, 0, 10));	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent e) {
		JButton target = (JButton)e.getSource();
		System.out.println(target.getActionCommand());
		target.setTransferHandler(new ComponentTransferHandler(target.getText(), workArea.getCurrentTab()));
		JButton button = (JButton)e.getSource();
		TransferHandler handler = button.getTransferHandler();
		handler.exportAsDrag(button, e, TransferHandler.COPY);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}