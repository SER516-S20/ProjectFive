/**
 * this class implement the factory design pattern
 * @author Hongqi Zhang
 */
public class ButtonBoxFactory {
	public static ButtonBox buildButtonBox(String btnCommand) {
		ButtonBox button = null;
		switch(btnCommand) {
		case "(":
			button = new StartButtonBox();
			break;
		case ")":
			button = new EndButtonBox();
			break;
		case "<":
			button = new LButtonBox();
			break;
		case ">":
			button = new RButtonBox();
			break;
		case "@":
			button = new LoopButtonBox();
			break;
		case "||":
			button = new BarButtonBox();
			break;
		case "-" :
			button = new DotButtonBox();
			break;
		}
		return button;
	}
}
