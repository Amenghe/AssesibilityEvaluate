package hezt.assesibilityevaluate.chainextractor.model.stg.input;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("TransitionEdge")
public class InTransitionEdge {

    private InAbstractNode srcNode;
    private InAbstractNode tgtNode;
    private EdgeTag edgeTag;

    public InTransitionEdge(InAbstractNode srcNode, InAbstractNode tgtNode, EdgeTag edgeTag) {
        this.srcNode = srcNode;
        this.tgtNode = tgtNode;
        this.edgeTag = edgeTag;
    }

    public InTransitionEdge() {
    }

    public InAbstractNode getSrcNode() {
        return srcNode;
    }

    public void setSrcNode(InAbstractNode srcNode) {
        this.srcNode = srcNode;
    }

    public InAbstractNode getTgtNode() {
        return tgtNode;
    }

    public void setTgtNode(InAbstractNode tgtNode) {
        this.tgtNode = tgtNode;
    }

    public String toString() {
        return srcNode.getName() + " ==> " + tgtNode.getName() + " Tag: " + edgeTag;
    }

    /**
     * Gets the tag assigned to this edge
     * @return The tag assigned to this edge
     */
    public EdgeTag getEdgeTag() {
        return edgeTag;
    }

    /**
     * Sets the tag assigned to this edge
     * @param edgeTag The tag assigned to this edge
     */
    public void setEdgeTag(EdgeTag edgeTag) {
        this.edgeTag = edgeTag;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (!super.equals(other))
            return false;
        if (getClass() != other.getClass())
            return false;

        InTransitionEdge o = (InTransitionEdge) other;
        if(!srcNode.equals(((InTransitionEdge) other).srcNode)){
            return false;
        }else if(!tgtNode.equals(((InTransitionEdge) other).tgtNode)){
            return false;
        }else if(!edgeTag.equals(((InTransitionEdge) other).edgeTag)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((srcNode == null) ? 0 : srcNode.hashCode());
        result = prime * result + ((tgtNode == null) ? 0 : tgtNode.hashCode());
        result = prime * result + ((edgeTag == null) ? 0 : edgeTag.hashCode());
        return result;
    }
}
