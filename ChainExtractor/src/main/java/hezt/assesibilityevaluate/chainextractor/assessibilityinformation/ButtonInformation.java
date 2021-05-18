package hezt.assesibilityevaluate.chainextractor.assessibilityinformation;

import hezt.assesibilityevaluate.chainextractor.elementextractor.ButtonExtractor;

import hezt.assesibilityevaluate.chainextractor.util.App;
import org.dom4j.DocumentException;
import org.dom4j.Element;

public class ButtonInformation extends GenericInformation {
    public ButtonInformation(App app, String id) throws DocumentException {
        super();
        this.element = ButtonExtractor.extractElementFromXmlFile(app,id);
    }
    public ButtonInformation(Element element){
        super(element);
    }
}
