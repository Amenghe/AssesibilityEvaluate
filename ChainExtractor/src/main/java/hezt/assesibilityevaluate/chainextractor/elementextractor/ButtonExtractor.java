package hezt.assesibilityevaluate.chainextractor.elementextractor;

import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import org.dom4j.DocumentException;
import org.dom4j.Element;

public class ButtonExtractor {
    public static Element extractElementFromXmlFile(String xmlFilePath, String elementId) throws DocumentException {
        return FileUtil.findNodeFromMenu(xmlFilePath, elementId);
    }

}
