package hezt.assesibilityevaluate.chainextractor.elementextractor;

import hezt.assesibilityevaluate.chainextractor.util.App;
import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import org.dom4j.DocumentException;
import org.dom4j.Element;

public class ButtonExtractor {
    public static Element extractElementFromXmlFile(App app, String elementId) throws DocumentException {
        return GenericExtractor.extractElementFromXmlFile(app,elementId);
    }

}
