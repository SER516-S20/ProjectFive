/**
 * Factory to create a new Operator
 *
 * @author Karandeep Singh Grewal
 * @since March 10, 2020
 */
public class FactoryOp {
    static Op op = null;

    public static Op getOp(String opName) {
        switch (opName) {
            case "(":
                op = new OpParOpen();
                break;
            case ")":
                op = new OpParClose();
                break;
            case "<":
                op = new OpLesserThan();
                break;
            case ">":
                op = new OpGreaterThan();
                break;
            case "-":
                op = new OpMinus();
                break;
            case "@":
                op = new OpAt();
                break;
            case "||":
                op = new OpVerticalBars();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + opName);
        }
        return op;
    }
}
