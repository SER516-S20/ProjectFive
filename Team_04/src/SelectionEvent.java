import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * 
 * @author Tarun Snehith Kishore Reddy Karna
 * @since 05-03-2021
 * @version 1.0
 *
 */
public class SelectionEvent implements ActionListener {
	private JButton shapeButton;
	private int shapeNo;
	private SelectedShape select;

	public SelectionEvent(JButton button, int shapeNo) {
		this.shapeButton = button;
		this.shapeButton.addActionListener(this);
		this.shapeNo = shapeNo;
		select = new SelectedShape();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		select.No = shapeNo;
	}

}
