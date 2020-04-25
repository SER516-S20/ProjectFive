import java.awt.*;

/**
 * Operator ")"
 *
 * @author Arvind
 * @author Karandeep Singh Grewal
 * @since March 13, 2020
 */
public class OpParClose extends Op {
    final Color BLUE = new Color(0, 122, 255);

    OpParClose() {
        label = ")";
        opLabel.setText(label);
        color = BLUE;
        packOperator();
        addToConnector("I", FactoryConnector.getOp("Dot", this));
    }
}
