import javax.swing.JButton;

/**
 * @author Hongqi Zhang
 */
public class EndButtonBox extends ButtonBox {
	private static final long serialVersionUID = 1L;
	private JButton []dots; 
	
	public EndButtonBox() {
		super(")", 1);
		drawContent();
	}
	
	@Override
	public void drawContent() {
		int height = this.getPreferredSize().height / 5;
		dots = getBtnDots();
		dots[0].setLocation(1, (this.getPreferredSize().height - height) / 2);
		for(int i = 0; i < dots.length; i++) {
			this.add(dots[i]);
		}
	}
}