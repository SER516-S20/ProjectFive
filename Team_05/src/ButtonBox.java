import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * @author Hongqi Zhang
 */
public abstract class ButtonBox extends JPanel{
	private static final long serialVersionUID = 1L;
	private final Color LIGHTBLUE = new Color(117, 218, 255);
	private final int labelWidth = 20;
	private final int labelHeight =20;
	private String title, symbol;
	private int id;
	private JLabel description;
	private ValuePane vDialog;
	private JButton []btnDots;
	
	public JButton[] getBtnDots() {
		return btnDots;
	}
	public void setBtnDots(JButton[] btnDots) {
		this.btnDots = btnDots;
	}
	public ButtonBox(String symbol, int num) {
		this.setPreferredSize(new Dimension(120, 60));
		this.setBackground(LIGHTBLUE);
		addDescription(symbol);
		this.setSymbol(symbol);
		generateBtnDot(num);
		this.setTitle(" ");
		this.setLayout(null);
	}
	public void setTitle(String text) {
		this.title = text;
		this.setBorder(BorderFactory.createTitledBorder(title));
	}
	public String getTitle() {
		return this.title;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getSymbol() {
		return this.symbol;
	}
	private void addDescription(String symbol) {
		Dimension size = this.getPreferredSize();
		description = new JLabel(symbol);
		description.setSize(new Dimension(labelWidth, labelHeight));
		description.setLocation((size.width - labelWidth) / 2, (size.height - labelHeight) / 2);
		this.add(description);
	}
	private void generateBtnDot(int num) {
		int height = this.getPreferredSize().height / 5;
		btnDots = new JButton[num];
		for( int i = 0; i < btnDots.length; i++) {
			btnDots[i] = new JButton();
			btnDots[i].setSize(height, height);
			btnDots[i].addMouseListener(new ConnectionController(this));
		}
	}
	public ValuePane createJOptionPane() {
		vDialog =  new ValuePane();
		vDialog.setValue(" ");
		return vDialog;
	}
	/*
	 * this method is used to re-draw the dot button in the Button Box
	 */
	abstract public void drawContent();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
