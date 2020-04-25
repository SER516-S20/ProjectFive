import java.awt.*;

/**
 * Operator "-"
 *
 * @author Arvind
 * @author Karandeep Singh Grewal
 * @since March 13, 2020
 */
public class OpMinus extends Op {
    final Color IRON = new Color(155, 163, 169);

    OpMinus() {
        label = "-";
        opLabel.setText(label);
        color = IRON;
        packOperator();
        addToConnector("I", FactoryConnector.getOp("Dot", this));
        addToConnector("O", FactoryConnector.getOp("Dot", this));

    }

}
