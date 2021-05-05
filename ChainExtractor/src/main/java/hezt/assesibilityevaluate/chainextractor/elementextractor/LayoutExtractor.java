package hezt.assesibilityevaluate.chainextractor.elementextractor;

import org.dom4j.Element;

public class LayoutExtractor extends GenericExtractor {
    private static final LayoutExtractor singleton= new LayoutExtractor();
    @Override
    public Element extractElementFromXmlFile(String xmlFilePath, String elementId) {
        return super.extractElementFromXmlFile(xmlFilePath, elementId);
    }
    private LayoutExtractor(){}
    public static LayoutExtractor getSinglton(){
        return singleton;
    }
}
