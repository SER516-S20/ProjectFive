import java.util.List;

/**
 * @author abhinaw sarang
 * @modified on 03-17-2020
 * @version 1.0
 */
public class Rules {

	public static boolean isValidLine(Line newLine, List<Line> existingLines, List<Dot> barCenters ) {
		if (!newLine.getStartDot().getIsOutput()) {
			return false;
		}
		if (!newLine.getEndDot().getIsInput()) {
			return false;
		}
		for (Line eachLine: existingLines) {
			if (newLine.equals(eachLine)) {
				return false;
			}
			if (!barCenters.contains(newLine.getStartDot())
					&& eachLine.getStartDot().equals(newLine.getStartDot())) {
				return false;
			}
			if (!barCenters.contains(newLine.getEndDot())
					&& eachLine.getEndDot().equals(newLine.getEndDot())) {
				return false;
			}
		}
		return true;
	}
}
