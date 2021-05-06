package hezt.assesibilityevaluate.chainextractor.assessibilityinformation;

import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.util.Iterator;

public class MenuInformation extends GenericInformation{
    String id;
    public MenuInformation(String menuLayoutDir,String id){
        try {
            this.element = FileUtil.findNodeFromMenu(menuLayoutDir,id);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        this.id = id;
    }
    public MenuInformation(Element element){
        this.element = element;
    }

    @Override
    public String getText(){
        return null;
    }

    @Override
    public String getContentDescription(){
        String items = "";
        String thisitem = "";
        for(Iterator<Element> it = element.elementIterator(); it.hasNext();){
            Element child = it.next();
            if(child.getQName().equals("item")){
                Attribute attribute = child.attribute("contentDescription");
                if(attribute!=null){
                    items += items.equals("")?attribute.getValue():","+attribute.getValue();
                }
                if(child.attribute("id").equals(this.id)){
                    thisitem += attribute.getValue();
                }
            }
        }
        if(!items.equals(""))
            return items + ";" + thisitem;
        return null;
    }
}
