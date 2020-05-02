import javax.swing.JButton;
/**
 * @author Hongqi Zhang
 */
public class StartButtonBox extends ButtonBox {
	private static final long serialVersionUID = 1L;
	private JButton []dots; 
	public StartButtonBox() {
		super("(", 1);
		drawContent();
	}

	@Override
	public void drawContent() {
		int width = this.getPreferredSize().width;
		int height = this.getPreferredSize().height / 5;
		dots = getBtnDots();
		dots[0].setLocation(width - height - 2, (this.getPreferredSize().height - height) / 2);
		for(int i = 0; i < dots.length; i++) {
			this.add(dots[i]);
		}
	}
}