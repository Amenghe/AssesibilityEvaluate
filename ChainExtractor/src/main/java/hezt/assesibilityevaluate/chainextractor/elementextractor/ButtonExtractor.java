package hezt.assesibilityevaluate.chainextractor.elementextractor;

import org.dom4j.Element;

public class ButtonExtractor extends GenericExtractor {
    private static final ButtonExtractor singleton= new ButtonExtractor();
    private ButtonExtractor(){}
    @Override
    public Element extractElementFromXmlFile(String xmlFilePath, String elementId) {
        return super.extractElementFromXmlFile(xmlFilePath, elementId);
    }
    public static ButtonExtractor getSinglton(){
        return singleton;
    }
}
