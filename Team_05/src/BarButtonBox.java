import javax.swing.JButton;
/**
 * @author Hongqi Zhang
 */
public class BarButtonBox extends ButtonBox {
	private static final long serialVersionUID = 1L;
	private JButton []dots; 
	public BarButtonBox() {
		super("||", 2);
		drawContent();
	}

	@Override
	public void drawContent() {
		int width = this.getPreferredSize().width;
		int height = this.getPreferredSize().height / 5;
		dots = getBtnDots();
		dots[0].setSize(height, this.getPreferredSize().height / 5 * 4);
		dots[1].setSize(height, this.getPreferredSize().height / 5 * 4);
		dots[0].setLocation(1, this.getPreferredSize().height / 10);
		dots[1].setLocation(width - height - 2, this.getPreferredSize().height / 10);
		for(int i = 0; i < dots.length; i++) {
			this.add(dots[i]);
		}
	}
}
