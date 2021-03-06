package hezt.assesibilityevaluate.chainextractor.elementextractor;

import hezt.assesibilityevaluate.chainextractor.util.App;
import hezt.assesibilityevaluate.chainextractor.util.FileUtil;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

public class MenuExtractor{
    public static Element extractElementFromXmlFile(App app, String elementId) throws DocumentException {
        Logger logger = Logger.getLogger(MenuExtractor.class);
        elementId = app.getIdFromPublicXml(elementId);
        if(elementId == null)return null;

        try {
            logger.info("找到id为"+elementId+"的菜单项元素");
            return FileUtil.findNodeFromMenu(app.getMenuLayoutDir(),elementId);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        logger.error("没有找到id为"+elementId+"的菜单项元素");
        return null;
    }
}
