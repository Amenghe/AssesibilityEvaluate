package hezt.assesibilityevaluate.chainextractor.elementextractor;

import org.dom4j.Element;

public class EditTextExtractor {
    public static Element extractElementFromXmlFile(String xmlFilePath, String elementId){
        return GenericExtractor.extractElementFromXmlFile(xmlFilePath,elementId);
    }
}
