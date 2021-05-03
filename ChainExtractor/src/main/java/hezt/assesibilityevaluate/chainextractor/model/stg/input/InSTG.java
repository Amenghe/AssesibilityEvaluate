package hezt.assesibilityevaluate.chainextractor.model.stg.input;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.*;
import org.apache.log4j.Logger;
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
     * STG很可能不完整，因此链路的头节点可能不同
     * 采用邻接表法
     */
    public List<List<InTransitionEdge>> getChains(){
        Logger logger = Logger.getLogger(this.getClass());
        List<List<InTransitionEdge>> chains = new ArrayList<List<InTransitionEdge>>();
        /*
         *找到所有头节点
         */
        Set<InAbstractNode> roots = new HashSet<InAbstractNode>();
        for(InTransitionEdge edge:this.transitionEdges){
            roots.add(edge.getSrcNode());
        }
        for(InTransitionEdge edge:this.transitionEdges){
            roots.remove(edge.getTgtNode());
        }
        if(roots.isEmpty()){
            logger.error("没有找到合适的链路头节点，可能是图中链路全是环路");
            return chains;
        }
        /*
         *构建邻接表
         */
        Map<InAbstractNode,List<InTransitionEdge>> adjacency = new HashMap<InAbstractNode,List<InTransitionEdge>>();
        for(InTransitionEdge edge:this.transitionEdges){
            if(!adjacency.containsKey(edge.getSrcNode())){
                adjacency.put(edge.getSrcNode(),new ArrayList<InTransitionEdge>());
            }
            adjacency.get(edge.getSrcNode()).add(edge);
        }

        Set<InAbstractNode> visited = new HashSet<InAbstractNode>();
        ArrayList<InTransitionEdge> stack = new ArrayList<InTransitionEdge>();
        
        return chains;
    }
}
