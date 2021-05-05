package hezt.assesibilityevaluate.chainextractor.model.stg.input;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import com.thoughtworks.xstream.security.AnyTypePermission;
import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import org.apache.log4j.Logger;
@XStreamAlias("ScreenTransitionGraph")
public class InSTG {
    private List<InTransitionEdge> transitionEdges;
    private Set<InScreenNode> screenNodeSet;
    private Set<InServiceNode> serviceNodeSet;
    private Set<InBroadcastReceiverNode> broadcastReceiverNodeSet;

    public static InSTG getInstance(String stgPath){
        Logger logger = Logger.getLogger(InSTG.class);
        XStream xStream = new XStream();
        xStream.setClassLoader(InSTG.class.getClassLoader());
        //XStream.setupDefaultSecurity(xStream);
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.autodetectAnnotations(true);
        xStream.addPermission(AnyTypePermission.ANY);
        InSTG stg = null;
        try {
            FileUtil.helpBuilderStg(xStream);
            stg = (InSTG) xStream.fromXML(new URL("file:/"+stgPath));
        } catch (MalformedURLException e) {
            logger.error("STG构建失败");
            e.printStackTrace();
        }
        logger.info("STG构建成功");
        return stg;
    }

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
        List<List<InTransitionEdge>> chains = new LinkedList<List<InTransitionEdge>>();
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
        /*
         *广度优先遍历获取链路
         */
        for(InAbstractNode root:roots) {
            Set<InAbstractNode> visited = new HashSet<InAbstractNode>();
            LinkedList<InTransitionEdge> queue = new LinkedList<InTransitionEdge>();
            LinkedList<LinkedList<InTransitionEdge>> chainsQueue = new LinkedList<LinkedList<InTransitionEdge>>();
            visited.add(root);
            for(InTransitionEdge edge:adjacency.get(root)){
                queue.add(edge);
                LinkedList<InTransitionEdge> chain = new LinkedList<InTransitionEdge>();
                chain.add(edge);
                chainsQueue.addLast(chain);
            }
            while(!queue.isEmpty()){
                InTransitionEdge curEdge = queue.removeFirst();
                LinkedList<InTransitionEdge> curChain = chainsQueue.removeFirst();
                List<InTransitionEdge> succeedEdges = adjacency.get(curEdge.getTgtNode());
                //当前边的后继节点无出边
                if(succeedEdges == null){
                    chains.add(curChain);
                }
                //当前边的后继节点已被访问过
                else if(visited.contains(curEdge.getTgtNode())){
                    chains.add(curChain);
                }else{
                    for(InTransitionEdge succeedEdge:succeedEdges){
                        LinkedList<InTransitionEdge> newChain = new LinkedList<InTransitionEdge>(curChain);
                        //新边入队
                        queue.addLast(succeedEdge);
                        //newChain.addAll(curChain);
                        newChain.addLast(succeedEdge);
                        //生成一条新链路
                        chainsQueue.addLast(newChain);
                    }
                }
                visited.add(curEdge.getTgtNode());
            }
        }
        return chains;
    }
}
