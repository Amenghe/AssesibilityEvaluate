package hezt.assesibilityevaluate.chainextractor.elementextractor;

import org.dom4j.Element;

public class TextViewExtractor extends GenericExtractor {
    private static final TextViewExtractor singleton= new TextViewExtractor();
    private TextViewExtractor(){}
    @Override
    public Element extractElementFromXmlFile(String xmlFilePath, String elementId) {
        return super.extractElementFromXmlFile(xmlFilePath, elementId);
    }
    public static TextViewExtractor getSinglton(){
        return singleton;
    }
}
