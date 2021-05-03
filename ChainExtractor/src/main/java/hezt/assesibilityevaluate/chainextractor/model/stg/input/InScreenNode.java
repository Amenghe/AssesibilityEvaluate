package hezt.assesibilityevaluate.chainextractor.model.stg.input;


import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.HashSet;
import java.util.Set;

@XStreamAlias("ScreenNode")
public class InScreenNode extends InAbstractNode {

    private Set<String> fragments;
    private String menu;
    private Set<String> dialogs;

    public InScreenNode(String name,Set<String> fragments,String menu,Set<String> dialogs) {
        super(name);
        this.fragments = fragments;
        this.dialogs = dialogs;
        this.menu = menu;
    }

    public InScreenNode() {
        super();
        this.dialogs = new HashSet<String>();
    }

    public Set<String> getFragments() {
        return fragments;
    }

    public void setFragments(Set<String> fragments) {
        this.fragments = fragments;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Set<String> getDialogs() {
        return dialogs;
    }

    public void setDialogs(Set<String> dialogs) {
        this.dialogs = dialogs;
    }

    @Override
    public String toString(){
        return getName() + " fragments: " + fragments + " menu: " + menu + " dialogs" + dialogs;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((fragments == null) ? 0 : fragments.hashCode());
        result = prime * result + ((menu == null) ? 0 : menu.hashCode());
        result = prime * result + ((dialogs.isEmpty()) ? 0 : dialogs.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;

        InScreenNode other = (InScreenNode) obj;

        if (fragments == null) {
            if (other.fragments != null)
                return false;
        } else if (!fragments.equals(other.fragments)){
            return false;
        } else if (menu == null) {
            if (other.menu != null)
                return false;
        } else if(!menu.equals(other.menu)){
            return false;
        } else if(dialogs == null){
            if(other.dialogs != null){
                return false;
            }else{
                return true;
            }
        }
        return dialogs.equals(other.dialogs);
    }
}
