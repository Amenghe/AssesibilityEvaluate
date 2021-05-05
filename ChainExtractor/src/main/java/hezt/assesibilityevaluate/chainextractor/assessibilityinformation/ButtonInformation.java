package hezt.assesibilityevaluate.chainextractor.assessibilityinformation;

import hezt.assesibilityevaluate.chainextractor.elementextractor.ButtonExtractor;

import org.dom4j.Element;

public class ButtonInformation extends GenericInformation {
    public ButtonInformation(String xmlFilePath,String id){
        super();
        this.element = ButtonExtractor.getSinglton().extractElementFromXmlFile(xmlFilePath,id);
    }
    public ButtonInformation(Element element){
        super(element);
    }
}
