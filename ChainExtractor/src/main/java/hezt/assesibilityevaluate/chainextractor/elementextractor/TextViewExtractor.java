package hezt.assesibilityevaluate.chainextractor.elementextractor;

import hezt.assesibilityevaluate.chainextractor.util.App;
import org.dom4j.DocumentException;
import org.dom4j.Element;

public class TextViewExtractor{
    public static Element extractElementFromXmlFile(App app, String elementId) throws DocumentException {
        return GenericExtractor.extractElementFromXmlFile(app,elementId);
    }
}
