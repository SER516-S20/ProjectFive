import javax.swing.JButton;
/**
 * @author Hongqi Zhang
 */
public class LButtonBox extends ButtonBox {
	private static final long serialVersionUID = 1L;
	private JButton []dots; 
	public LButtonBox() {
		super("<", 3);
		drawContent();
	}
	
	@Override
	public void drawContent() {
		int width = this.getPreferredSize().width;
		int height = this.getPreferredSize().height / 5;
		dots = getBtnDots();
		dots[0].setLocation(1, (this.getPreferredSize().height - height) / 2);
		dots[1].setLocation(width - height - 2, (this.getPreferredSize().height / 2 - height) / 2);
		dots[2].setLocation(width - height - 2, (this.getPreferredSize().height / 2 + height * 4) / 2);
		for(int i = 0; i < dots.length; i++) {
			this.add(dots[i]);
		}
	}
}
