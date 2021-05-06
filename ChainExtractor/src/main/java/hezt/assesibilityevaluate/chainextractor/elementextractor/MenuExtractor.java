package hezt.assesibilityevaluate.chainextractor.elementextractor;

import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import org.dom4j.DocumentException;
import org.dom4j.Element;

public class MenuExtractor{
    public static Element extractElementFromXmlFile(String menuLayoutDir, String elementId) throws DocumentException {
        return FileUtil.findNodeFromMenu(menuLayoutDir,elementId);
    }
}
