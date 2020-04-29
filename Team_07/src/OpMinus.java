import java.awt.*;

/**
 * Operator "-"
 *
 * @author Arvind
 * @author Karandeep Singh Grewal
 * @since March 13, 2020
 */
public class OpMinus extends Op {
    final Color TEAL = new Color(90, 200, 250);

    OpMinus() {
        label = "-";
        opLabel.setText(label);
        color = TEAL;
        packOperator();
        addToConnector("I", FactoryConnector.getOp("Dot", this));
        addToConnector("O", FactoryConnector.getOp("Dot", this));

    }

}
