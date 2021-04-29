package hezt.assesibilityevaluate.chainextractor.model.stg.input;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ServiceNode")
public class InServiceNode extends InAbstractNode {

    public InServiceNode(String name) {
        super(name);
    }

    /* ==================================================
                    Getters and setters
       ==================================================*/
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
