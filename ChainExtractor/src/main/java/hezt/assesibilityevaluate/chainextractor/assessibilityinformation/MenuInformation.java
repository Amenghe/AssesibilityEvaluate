package hezt.assesibilityevaluate.chainextractor.assessibilityinformation;

import hezt.assesibilityevaluate.chainextractor.elementextractor.MenuExtractor;
import hezt.assesibilityevaluate.chainextractor.util.App;
import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.util.Iterator;

public class MenuInformation extends GenericInformation{
    String id;
    public MenuInformation(App app, String id){
        try {
            this.element = MenuExtractor.extractElementFromXmlFile(app,id);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        this.id = id;
    }
    public MenuInformation(Element element){
        this.element = element;
    }


}
