public class TranxlatorNode {
    String label;
    String id;
    TranxlatorCluster cluster;
    TranxlatorNode(String label, String id){
        this.label=label;
        this.id=id;
    }

    public TranxlatorCluster getCluster() {
        return cluster;
    }

    public void setCluster(TranxlatorCluster cluster) {
        this.cluster = cluster;
    }
}
