package hezt.assesibilityevaluate.chainextractor.elementextractor;

import org.dom4j.Element;

public class ButtonExtractor {
    public static Element extractElementFromXmlFile(String xmlFilePath, String elementId) {
        return GenericExtractor.extractElementFromXmlFile(xmlFilePath, elementId);
    }

}
