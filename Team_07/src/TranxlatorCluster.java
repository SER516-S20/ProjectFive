public class TranxlatorCluster {
    String label;
    Op op;
    TranxlatorNode src, desc;

    TranxlatorCluster(String label, Op op)
    {
        this.label = label;
        this.op = op;
    }
}
