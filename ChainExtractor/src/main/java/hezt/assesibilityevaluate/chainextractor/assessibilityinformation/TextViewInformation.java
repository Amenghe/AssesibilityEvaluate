package hezt.assesibilityevaluate.chainextractor.assessibilityinformation;

import hezt.assesibilityevaluate.chainextractor.elementextractor.TextViewExtractor;
import hezt.assesibilityevaluate.chainextractor.util.App;
import org.dom4j.DocumentException;
import org.dom4j.Element;

public class TextViewInformation extends GenericInformation{
    public TextViewInformation(App app, String id) throws DocumentException {
        super();
        this.element = TextViewExtractor.extractElementFromXmlFile(app,id);
    }
    public TextViewInformation(Element element){
        super(element);
    }


}
