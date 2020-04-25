/**
 * @author abhinaw sarang
 * @created 03-15-2020
 * @version 1.0
 */
public class Dot {

	private int x;
	private int y;
	private boolean isInput = false;
	private boolean isOutput = false;
	private boolean lineConnected = false;

	public Dot(int x, int y, boolean isInput, boolean isOutput) {
		this.setX(x);
		this.setY(y);
		this.setIsInput(isInput);
		this.setIsOutput(isOutput);
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj instanceof Dot) {
			Dot newDot = (Dot) obj;
			if (newDot.getX() >= this.getX() - 20 && newDot.getY() >= this.getY() - 10
					&& newDot.getX() <= this.getX() + 20 && newDot.getY() <= this.getY() + 10) {
				return true;
			}
		}
		return false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Boolean getLineConnected() {
		return lineConnected;
	}

	public void setLineConnected(Boolean lineConnected) {
		this.lineConnected = lineConnected;
	}

	public Boolean getIsInput() {
		return isInput;
	}

	public void setIsInput(Boolean isInput) {
		this.isInput = isInput;
	}

	public Boolean getIsOutput() {
		return isOutput;
	}

	public void setIsOutput(Boolean isOutput) {
		this.isOutput = isOutput;
	}
}
