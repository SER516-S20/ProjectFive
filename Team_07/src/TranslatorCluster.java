/**
 *  * @author Karandeep Singh Grewal
 *  * @author Aravind
 *  * @since May 3, 2020
 */
public class TranslatorCluster {
    String label;
    Op op;
    TranslatorNode src, desc;

    TranslatorCluster(String label, Op op)
    {
        this.label = label;
        this.op = op;
    }
}
