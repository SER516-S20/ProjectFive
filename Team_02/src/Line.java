/**
 * @author abhinaw sarang
 * @created 03-15-2020
 * @version 1.0
 */
public class Line {

	private Dot startPoint;
	private Dot endPoint;

	public Line(Dot p1, Dot p2) {
		this.setStartDot(p1);
		this.setEndDot(p2);
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj instanceof Line) {
			Line newLine = (Line) obj;
			if (newLine.getStartDot().equals(this.getStartDot()) && 
					newLine.getEndDot().equals(this.getEndDot())) {
				return true;
			}
		}
		return false;
	}

	public Dot getStartDot() {
		return startPoint;
	}

	public void setStartDot(Dot startDot) {
		this.startPoint = startDot;
	}

	public Dot getEndDot() {
		return endPoint;
	}

	public void setEndDot(Dot endDot) {
		this.endPoint = endDot;
	}
}
