/**
 * @author ShihYu Chang
 */
public class Connection {
	private int sourceX, sourceY, destX, destY;
	private Integer sourceButton;
	private Integer destButton;
	
	public void setSourceButton(int sourceButton2) {
		sourceButton = sourceButton2;
	}
	
	public Integer getSourceButton() {
		return sourceButton;
	}
	
	public void setDestButton(int destButton2) {
		destButton = destButton2;
	}
	
	public Integer getDestButton() {
		return destButton;
	}
	
	public int getSourceX() {
		return sourceX;
	}

	public void setSourceX(int sourceX) {
		this.sourceX = sourceX;
	}
	
	public int getSourceY() {
		return sourceY;
	}
	
	public void setSourceY(int sourceY) {
		this.sourceY = sourceY;
	}
	
	public int getDestX() {
		return destX;
	}
	
	public void setDestX(int destX) {
		this.destX = destX;
	}
	
	public int getDestY() {
		return destY;
	}
	
	public void setDestY(int destY) {
		this.destY = destY;
	}
}