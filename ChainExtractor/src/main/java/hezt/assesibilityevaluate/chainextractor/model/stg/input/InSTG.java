package hezt.assesibilityevaluate.chainextractor.model.stg.input;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.List;
import java.util.Set;

@XStreamAlias("ScreenTransitionGraph")
public class InSTG {
    private List<InTransitionEdge> transitionEdges;
    private Set<InScreenNode> screenNodeSet;
    private Set<InServiceNode> serviceNodeSet;
    private Set<InBroadcastReceiverNode> broadcastReceiverNodeSet;

    public InSTG(List<InTransitionEdge> transitionEdges, Set<InScreenNode> screenNodeSet, Set<InServiceNode> serviceNodeSet, Set<InBroadcastReceiverNode> broadcastReceiverNodeSet) {
        this.transitionEdges = transitionEdges;
        this.screenNodeSet = screenNodeSet;
        this.serviceNodeSet = serviceNodeSet;
        this.broadcastReceiverNodeSet = broadcastReceiverNodeSet;
    }

    public InSTG() {

    }

    public List<InTransitionEdge> getTransitionEdges() {
        return transitionEdges;
    }

    public void setTransitionEdges(List<InTransitionEdge> transitionEdges) {
        this.transitionEdges = transitionEdges;
    }

    public Set<InScreenNode> getScreenNodeSet() {
        return screenNodeSet;
    }

    public void setScreenNodeSet(Set<InScreenNode> screenNodeSet) {
        this.screenNodeSet = screenNodeSet;
    }

    public Set<InServiceNode> getServiceNodeSet() {
        return serviceNodeSet;
    }

    public void setServiceNodeSet(Set<InServiceNode> serviceNodeSet) {
        this.serviceNodeSet = serviceNodeSet;
    }

    public Set<InBroadcastReceiverNode> getBroadcastReceiverNodeSet() {
        return broadcastReceiverNodeSet;
    }

    public void setBroadcastReceiverNodeSet(Set<InBroadcastReceiverNode> broadcastReceiverNodeSet) {
        this.broadcastReceiverNodeSet = broadcastReceiverNodeSet;
    }

    /*
     *获取图中的所有链路
     */
    public List<List<InTransitionEdge>> getChains(){
        return null;
    }
}
